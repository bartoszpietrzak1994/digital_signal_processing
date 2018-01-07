package model.window;

import org.apache.commons.math.complex.Complex;

public interface WindowFunction
{
    Complex calculate(int n, double m);
}
