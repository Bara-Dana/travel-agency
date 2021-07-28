package com.proiect.travel.agency.service;

import com.proiect.travel.agency.dto.CountryDto;
import com.proiect.travel.agency.entity.CountryModel;
import com.proiect.travel.agency.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public CountryModel addCountry(CountryDto countryDto) {

        CountryModel country = new CountryModel();
        country.setName(countryDto.getName());
        country.setContinent(countryDto.getContinent());

       return countryRepository.save(country);
    }

    public void editCountry(Long countryId, CountryDto countryDto) throws Exception {

        Optional<CountryModel> countryModel = countryRepository.findById(countryId);

        CountryModel country = countryModel.orElseThrow(() -> new Exception ("Tara cu id " + countryId + "nu exista"));

        country.setId(countryDto.getId());
        country.setName(countryDto.getName());
        country.setContinent(countryDto.getContinent());
        countryRepository.save(country);
    }

    public List<CountryModel> getCountries() {
        return countryRepository.findAll();
    }

    public CountryModel getById(Long id) {
        return countryRepository.findById(id).get();
    }


    public CountryModel checkIfExist(CountryDto countryDto) {
         Optional<CountryModel> countryModelOptional = countryRepository.findCountryByNameAndContinent(countryDto.getName(), countryDto.getContinent());
        if
        (countryModelOptional.isPresent()){
            return countryModelOptional.get();

        }else{
            return  addCountry(countryDto);
        }


    }

    public void deleteCountry(Long id) {
        countryRepository.deleteById(id);
    }


}
