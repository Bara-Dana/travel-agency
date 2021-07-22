package com.proiect.travel.agency.entity;

import javax.persistence.*;


@Entity
@Table(name = "country")
public class CountryModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String continent;


    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public CountryModel() {
    }

    public CountryModel(String name) {
        this.name = name;
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
}



