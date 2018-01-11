package service.client;

import exception.SignalParametersException;
import org.apache.commons.math.complex.Complex;

import java.util.List;

/**
 * Created by bartoszpietrzak on 13/12/2017.
 */
public interface ConvolutionService
{
    List<Complex> calculateConvolution(List<Complex> first, List<Complex> second) throws SignalParametersException;
}
