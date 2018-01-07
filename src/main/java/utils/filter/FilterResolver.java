package utils.filter;

import exception.FilterException;
import model.filter.Filter;
import model.filter.FilterType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FilterResolver
{
    @Autowired
    private List<Filter> filtersList;

    public Filter resolve(FilterType filterType) throws FilterException
    {
        return filtersList.stream().filter(filter -> filterType.equals(filter.getFilterType())).findFirst()
                .orElseThrow(() -> FilterException.filterTypeNotSupported(filterType.name()));
    }
}
