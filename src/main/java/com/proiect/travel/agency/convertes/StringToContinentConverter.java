package com.proiect.travel.agency.convertes;

import com.proiect.travel.agency.entity.ContinentModel;
import org.springframework.core.convert.converter.Converter;

public class StringToContinentConverter implements Converter<String, ContinentModel> {
    @Override
    public ContinentModel convert(String name) {
        return new ContinentModel(name);
    }
}
