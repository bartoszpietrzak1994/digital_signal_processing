import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.math.complex.Complex;
import org.junit.Test;

import service.ConvolutionServiceImpl;

/**
 * Created by bartoszpietrzak on 13/12/2017.
 */
public class ConvolutionServiceTest
{
	@Test
	public void testConvolution() throws Exception
	{
		// GIVEN
		ConvolutionServiceImpl convolutionService = new ConvolutionServiceImpl();
		List<Double> first = Arrays.asList(1.0, 2.0, 3.0, 4.0);
		List<Double> second = Arrays.asList(5.0, 6.0, 7.0);

		// WHEN
		List<Complex> convolution = convolutionService.calculateConvolution(toComplex(first), toComplex(second));

		// THEN
		assertEquals(first.size() + second.size() - 1, convolution.size());
	}

	private List<Complex> toComplex(List<Double> doubles)
	{
		List<Complex> complexNumbers = new ArrayList<>();

		for (Double value : doubles)
		{
			complexNumbers.add(new Complex(value, 0.0D));
		}

		return complexNumbers;
	}

}
