package service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.complex.Complex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import exception.SignalParametersException;
import utils.signal.SignalTypeResolver;

/**
 * Created by bartoszpietrzak on 13/12/2017.
 */
@Component
public class ConvolutionServiceImpl implements ConvolutionService
{
	@Autowired
	private SignalTypeResolver signalTypeResolver;

	@Override
	public List<Complex> calculateConvolution(List<Complex> first, List<Complex> second) throws SignalParametersException
	{
		List<Complex> convolutionValues = new ArrayList<>();

		int convolutionValuesSize = first.size() + second.size() - 1;
		int j;
		double tmp;
		for (int i = 0; i < convolutionValuesSize; i++)
		{
			j = i;
			tmp = 0.0;
			for (int k = 0; k < second.size(); k++)
			{
				if (j >= 0 && j < first.size())
					tmp = tmp + (first.get(j).getReal() * second.get(k).getReal());

				j -= 1;
			}
			convolutionValues.add(new Complex(tmp, 0.0D));
		}

		return convolutionValues;
	}
}
