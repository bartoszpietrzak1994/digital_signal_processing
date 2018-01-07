package model.filter;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

@Component
public class HighPassFilter
{
    public Complex calculate(int n, double k)
    {
        return new Complex(Math.pow(-1.0D, n) * k, 0.0D);
    }
}
