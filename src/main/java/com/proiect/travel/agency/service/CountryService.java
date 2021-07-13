package com.proiect.travel.agency.service;

import com.proiect.travel.agency.entity.CountryModel;
import com.proiect.travel.agency.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {
    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public void addCountry (CountryModel countryModel){
        countryRepository.save(countryModel);
    }

    public void editCountry (CountryModel countryModel){
        countryRepository.save(countryModel);
    }

    public List<CountryModel> getCountries(){
        return countryRepository.findAll();
    }

    public CountryModel getById(Long id){
        return countryRepository.findById(id).get();
    }

    public CountryModel getByName(String name){
        return countryRepository.findByName(name);
    }

    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }

}
