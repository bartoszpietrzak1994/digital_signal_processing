package utils.convolution;

import exception.SignalParametersException;
import model.signal.SignalType;
import model.signal.base.Signal;
import org.apache.commons.math.complex.Complex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import utils.calculator.SignalValuesCalculator;
import utils.signal.SignalTypeResolver;

import java.util.List;

@Component
public class ConvolutedSignalCreator
{
    @Autowired
    private SignalTypeResolver signalTypeResolver;

    @Autowired
    private SignalValuesCalculator signalValuesCalculator;

    public Signal create(Signal orgSignal, List<Complex> convolutedValues) throws SignalParametersException
    {
        List<Complex> convolutedSignalSamples = signalValuesCalculator.extendSampleList(orgSignal.getInitialTime(),
                orgSignal.getSamples(), convolutedValues.size());
        Complex convolutedSignalDuration = signalValuesCalculator.getSignalDurationBySamples(convolutedSignalSamples);

        Signal signal = signalTypeResolver.resolveSignalByType(SignalType.UNKNOWN.name());
        signal.setInitialTime(orgSignal.getInitialTime());
        signal.setDuration(convolutedSignalDuration);
        signal.setEndTime(orgSignal.getInitialTime().add(convolutedSignalDuration));
        signal.setSamples(convolutedSignalSamples);
        signal.setValues(convolutedValues);
        signal.setSamplingRate(orgSignal.getSamplingRate());
        signal.setPeriod(orgSignal.getPeriod());

        return signal;
    }


}
