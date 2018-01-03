package model.filter;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

@Component
public class LowPassFilter extends Filter
{
    @Override
    public Complex calculate(double value, double k)
    {
        if (value == 0)
        {
            return new Complex(2.0 / k, 0.0D);
        }

        return new Complex(Math.sin((2.0 * Math.PI * value) / k) / (Math.PI * value), 0.0D);
    }
}
