package model.filter;

import lombok.Getter;
import org.apache.commons.math.complex.Complex;

@Getter
public abstract class Filter
{
    protected FilterType filterType;
    public abstract Complex calculate(double value, double k);
}
