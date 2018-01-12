package utils.operation;

import exception.SignalParametersException;
import model.behaviour.SignalOperation;
import model.signal.base.Signal;
import org.apache.commons.math.complex.Complex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.client.ConvolutionService;
import utils.convolution.ConvolutedSignalCreator;

import java.util.List;

@Component
public class SignalsConvolutionOperation extends SignalsOperationsCalculator
{
    @Autowired
    private ConvolutionService convolutionService;

    @Autowired
    private ConvolutedSignalCreator convolutedSignalCreator;

    public SignalsConvolutionOperation()
    {
        this.signalOperation = SignalOperation.CONVOLUTION;
    }

    @Override
    public Signal calculate(Signal first, Signal second, Signal result) throws SignalParametersException
    {
        List<Complex> convolutedValues = convolutionService.calculateConvolution(first.getValues(), second.getValues());

        double firstInitialTime = first.getInitialTime().getReal();
        double secondInitialTime = second.getInitialTime().getReal();
        result = convolutedSignalCreator.create(firstInitialTime <= secondInitialTime ? first : second,
                convolutedValues);

        return result;
    }
}
