package com.emlakprojesi.config;

import com.emlakprojesi.domain.Property;
import com.emlakprojesi.repository.PropertyRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToPropertyConverter implements Converter<String, Property> {

    private final PropertyRepository propertyRepository;

    public StringToPropertyConverter(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public Property convert(String source) {
        if (source == null || source.trim().isEmpty()) {
            return null;
        }
        return propertyRepository.findById(Long.valueOf(source)).orElse(null);
    }
}
