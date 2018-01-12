package service;

import exception.SignalParametersException;
import exception.SignalRepositoryException;
import model.filter.FilterType;
import model.filter.HighPassFilter;
import model.filter.LowPassFilter;
import model.signal.SignalType;
import model.signal.base.Signal;
import model.window.HammingWindowFunction;
import org.apache.commons.math.complex.Complex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.SignalRepository;
import service.client.ConvolutionService;
import service.client.FilterService;
import service.response.ResolveSignalResponse;
import utils.calculator.SignalValuesCalculator;
import utils.convolution.ConvolutedSignalCreator;
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

    @Autowired
    private ConvolutedSignalCreator convolutedSignalCreator;

    @Override
    public ResolveSignalResponse performFilterOnSignal(String orgSignalId, FilterType filterType, int m, boolean
            additionalWindowFunction) throws SignalRepositoryException, SignalParametersException
    {
        Signal orgSignal = signalRepository.findOne(orgSignalId);

        double k = orgSignal.getSamplingRate().getReal() / (1.0 / orgSignal.getPeriod().getReal());

        List<Complex> filterValues = new ArrayList<>();
        Complex value;
        for (int n = 0; n < m; n++)
        {
            value = lowPassFilter.calculate(n, m, k);

            if (additionalWindowFunction)
            {
                value.multiply(hammingWindowFunction.calculate(n, m));
            }

            if (FilterType.HIGH_PASS.equals(filterType))
            {
                value = highPassFilter.calculate(value, n);
            }

            filterValues.add(value);
        }

        List<Complex> convolutedValues = convolutionService.calculateConvolution(orgSignal.getValues(), filterValues);

        Signal filteredSignal = convolutedSignalCreator.create(orgSignal, convolutedValues);
        String signalId = signalRepository.add(filteredSignal);

        ResolveSignalResponse resolveSignalResponse = new ResolveSignalResponse();

        resolveSignalResponse.setSignalParametersResponse(resolveSignalResponse.new SignalParametersResponse
                (signalId, filteredSignal.getSamplingRate(), filteredSignal.getInitialTime(), filteredSignal
                        .getDuration(), filteredSignal.getSignalType().name()));

        return resolveSignalResponse;
    }
}
