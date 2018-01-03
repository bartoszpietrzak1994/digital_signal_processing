package model.filter;

public class LowPassFilter
{
    public double calculate(double value, double k)
    {
        if (value == 0)
        {
            return 2.0 / k;
        }

        return Math.sin((2.0 * Math.PI * value) / k) / (Math.PI * value);
    }
}
