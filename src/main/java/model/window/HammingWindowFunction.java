package model.window;

public class HammingWindowFunction implements WindowFunction
{
    private static final double CONST_VALUE = 0.53836;
    private static final double CONST_COS_VALUE = -0.46164;

    @Override
    public double calculate(double value, double m)
    {
        return CONST_VALUE - (CONST_COS_VALUE * Math.cos((2.0 * Math.PI * value) / m));
    }
}
