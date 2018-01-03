package model.filter;

public class HighPassFilter
{
    double calculate(double value)
    {
        return Math.pow(-1.0D, value);
    }
}
