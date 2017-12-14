package service;

import java.util.List;

import org.apache.commons.math.complex.Complex;

import exception.SignalParametersException;

/**
 * Created by bartoszpietrzak on 13/12/2017.
 */
public interface ConvolutionService
{
	List<Complex> calculateConvolution(List<Complex> first, List<Complex> second) throws SignalParametersException;
}
