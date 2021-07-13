package com.proiect.travel.agency.controller;

import com.proiect.travel.agency.dto.CountryDto;
import com.proiect.travel.agency.entity.ContinentModel;
import com.proiect.travel.agency.entity.CountryModel;
import com.proiect.travel.agency.service.ContinentService;
import com.proiect.travel.agency.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CountryController {
    @Autowired
    private CountryService countryService;

    @Autowired
    private ContinentService continentService;

    @PostMapping("/country/addCountry")
    public ResponseEntity addCountry(@RequestBody CountryDto countryDto){
        ContinentModel continent = continentService.getById(countryDto.getContinentId());
        if (continent == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        CountryModel country = new CountryModel();
        country.setName(countryDto.getName());
        country.setContinent(continent);
        countryService.addCountry(country);

        return new ResponseEntity(country, HttpStatus.OK);
    }

    @PutMapping("/country/editCountry/{id}")
    public ResponseEntity editCountry(@PathVariable("id") Long id, @RequestBody CountryDto countryDto){
        ContinentModel continent = continentService.getById(countryDto.getContinentId());
        if (continent == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        CountryModel country = new CountryModel();
        country.setId(id);
        country.setName(countryDto.getName());
        country.setContinent(continent);
        countryService.editCountry(country);

        return new ResponseEntity(country, HttpStatus.OK);
    }

    @DeleteMapping("/country/deleteCountry/{id}")
    public ResponseEntity deleteCountry(@PathVariable("id") Long id){
        countryService.deleteCountry(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/country/getCountries")
    public ResponseEntity getCountries(){
        List<CountryModel> countries = countryService.getCountries();
        return new ResponseEntity(countries, HttpStatus.OK);
    }

    @GetMapping("/country/getCountryById/{id}")
    public ResponseEntity getCountryById(@PathVariable("id") Long id){
        CountryModel country = countryService.getById(id);
        return new ResponseEntity(country, HttpStatus.OK);
    }
}
