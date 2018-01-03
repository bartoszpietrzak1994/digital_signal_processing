package model.filter;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

@Component
public class HighPassFilter extends Filter
{
    @Override
    public Complex calculate(double value, double k)
    {
        return new Complex(Math.pow(-1.0D, value), 0.0D);
    }
}
