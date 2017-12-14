package model.convultion;

import java.util.List;

import org.apache.commons.math.complex.Complex;

import lombok.Data;

/**
 * Created by bartoszpietrzak on 13/12/2017.
 */
@Data
public class Convolution
{
	private List<Complex> samples;
	private List<Complex> values;
}
