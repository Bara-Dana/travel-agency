package com.proiect.travel.agency.dto;

import com.proiect.travel.agency.entity.CountryModel;

public class DestinationDto {
    private Long id;
    private String name;
    private String description;

    private CountryDto country;

    public Long getId() {
        return id;
    }

    public CountryDto getCountry() {
        return country;
    }

    public void setCountry(CountryDto country) {
        this.country = country;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
