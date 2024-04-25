package com.rest.webservices.restfulwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public SomeBean staticFiltering() {
        return new SomeBean("value1", "value2", "value3");
    }

    @GetMapping("/filtering-list")
    public List<SomeBean> staticFilteringList() {
        return Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value10", "value20", "value30"));
    }

    @GetMapping("/dynamic-filtering")
    public MappingJacksonValue dynamicFiltering() {
        SomeBean2 someBean2 = new SomeBean2("value1", "value2", "value3");
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean2);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    @GetMapping("/dynamic-filtering-list")
    public MappingJacksonValue dynamicFilteringList() {

        List<SomeBean2> beanList = Arrays.asList(new SomeBean2("value1", "value2", "value3"), new SomeBean2("value10", "value20", "value30"));
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(beanList);

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }
}
