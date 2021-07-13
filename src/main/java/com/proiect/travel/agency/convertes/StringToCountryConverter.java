package com.proiect.travel.agency.convertes;

import com.proiect.travel.agency.entity.CountryModel;
import org.springframework.core.convert.converter.Converter;

public class StringToCountryConverter implements Converter<String, CountryModel> {

    @Override
    public CountryModel convert(String name) {
        return new CountryModel(name);
    }
}
