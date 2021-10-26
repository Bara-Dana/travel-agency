package com.proiect.travel.agency.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proiect.travel.agency.dto.DestinationDto;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "destinations")
public class DestinationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;

    @OneToOne
    private CountryModel country;

    @JsonIgnore
    @OneToMany(mappedBy = "destination")
    private List<OfferModel> offers = new ArrayList<>();

    public DestinationDto toDestinationDto(){
        DestinationDto destinationDto = new DestinationDto();
        destinationDto.setId(this.id);
        destinationDto.setName(this.name);
        destinationDto.setDescription(this.description);
        destinationDto.setCountry(this.country.toCountryDto());

        return destinationDto;
    }


    public Long getId() {
        return id;
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

    public CountryModel getCountry() {
        return country;
    }

    public void setCountry(CountryModel country) {
        this.country = country;
    }

    public List<OfferModel> getOffers() {
        return offers;
    }

    public void setOffers(List<OfferModel> offers) {
        this.offers = offers;
    }

}
