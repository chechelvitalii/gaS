package com.petrol.station.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractConverter<S, T> implements Converter<S, T> {
    public List<T> convertToList(List<S> sources) {
        return sources.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }
}
