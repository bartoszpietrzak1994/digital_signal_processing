package model.filter;

import org.apache.commons.math.complex.Complex;
import org.springframework.stereotype.Component;

@Component
public class LowPassFilter
{
    public Complex calculate(int n, int m, double k)
    {
        if (n == ((m-1) / 2))
        {
            return new Complex(2.0 / k, 0.0D);
        }

        double numerator = Math.sin((2.0 * Math.PI * (n - ((m-1) / 2.0))) / k);
        double denominator = Math.PI * (n - ((m-1) / 2.0));

        return new Complex(numerator / denominator, 0.0D);
    }
}
