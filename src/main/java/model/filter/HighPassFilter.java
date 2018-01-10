package model.filter;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

@Component
public class HighPassFilter
{
    public Complex calculate(double lowPassFilterValue)
    {
        return new Complex(lowPassFilterValue % 2 == 0 ? lowPassFilterValue * -1 : lowPassFilterValue, 0.0D);
    }
}
