package model.filter;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

@Component
public class HighPassFilter
{
    public Complex calculate(Complex lowPassFilterValue, int n)
    {
        return new Complex(n % 2 != 0 ? lowPassFilterValue.getReal() * -1 :
                lowPassFilterValue.getReal(), 0.0D);
    }
}
