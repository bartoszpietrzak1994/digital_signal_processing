package model.window;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

@Component
public class HammingWindowFunction implements WindowFunction
{
    private static final double CONST_VALUE = 0.53836;
    private static final double CONST_COS_VALUE = -0.46164;

    @Override
    public Complex calculate(int n, double m)
    {
        return new Complex(CONST_VALUE - (CONST_COS_VALUE * Math.cos((2.0 * Math.PI * n) / m)), 0.0D);
    }
}
