package com.proiect.travel.agency.config;

import com.proiect.travel.agency.convertes.StringToContinentConverter;
import com.proiect.travel.agency.convertes.StringToCountryConverter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToContinentConverter());
        registry.addConverter(new StringToCountryConverter());
    }
}
