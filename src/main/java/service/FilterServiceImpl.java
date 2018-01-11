package service;

import exception.SignalParametersException;
import exception.SignalRepositoryException;
import model.filter.FilterType;
import model.filter.HighPassFilter;
import model.filter.LowPassFilter;
import model.signal.base.Signal;
import model.window.HammingWindowFunction;
import org.apache.commons.math.complex.Complex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.SignalRepository;
import service.client.ConvolutionService;
import service.client.FilterService;
import service.response.ResolveSignalResponse;
import service.response.SignalsCalculationResponse;
import utils.calculator.SignalValuesCalculator;
import utils.signal.SignalTypeResolver;

import java.util.ArrayList;
import java.util.List;

@Component
public class FilterServiceImpl implements FilterService
{
    @Autowired
    private SignalRepository signalRepository;

    @Autowired
    private LowPassFilter lowPassFilter;

    @Autowired
    private HighPassFilter highPassFilter;

    @Autowired
    private HammingWindowFunction hammingWindowFunction;

    @Autowired
    private ConvolutionService convolutionService;

    @Autowired
    private SignalTypeResolver signalTypeResolver;

    @Autowired
    private SignalValuesCalculator signalValuesCalculator;

    @Override
    public ResolveSignalResponse performFilterOnSignal(String orgSignalId, FilterType filterType, int m) throws
            SignalRepositoryException, SignalParametersException
    {
        Signal orgSignal = signalRepository.findOne(orgSignalId);

        double k = orgSignal.getSamplingRate().getReal() / (1.0 / orgSignal.getPeriod().getReal());

        List<Complex> filterValues = new ArrayList<>();
        for (int n = 0; n < m; n++)
        {
            filterValues.add(lowPassFilter.calculate(n, m, k).multiply(hammingWindowFunction.calculate(n, m)));
        }

        if (FilterType.HIGH_PASS.equals(filterType))
        {
            for (int n = 0; n < filterValues.size(); n++)
            {
                filterValues.set(n, highPassFilter.calculate(filterValues.get(n).getReal()));
            }
        }

        List<Complex> convolutedValues = convolutionService.calculateConvolution(orgSignal.getValues(), filterValues);

        Signal filteredSignal = signalTypeResolver.resolveSignalByType(orgSignal.getSignalType().name());
        filteredSignal.setSamples(signalValuesCalculator.extendSampleList(orgSignal.getInitialTime(), orgSignal
                .getSamples(), convolutedValues.size()));
        filteredSignal.setValues(convolutedValues);
        filteredSignal.setInitialTime(orgSignal.getInitialTime());
        filteredSignal.setSamplingRate(orgSignal.getSamplingRate());
        filteredSignal.setDuration(signalValuesCalculator.getSignalDurationBySamples(filteredSignal.getSamples()));
        filteredSignal.setEndTime(filteredSignal.getInitialTime().add(filteredSignal.getDuration()));

        String signalId = signalRepository.add(filteredSignal);

        ResolveSignalResponse resolveSignalResponse = new ResolveSignalResponse();

        resolveSignalResponse.setSignalParametersResponse(resolveSignalResponse.new SignalParametersResponse
                (signalId, filteredSignal.getSamplingRate(), filteredSignal.getInitialTime(), filteredSignal
                        .getDuration(), filteredSignal.getSignalType().name()));

        return resolveSignalResponse;
    }
}
