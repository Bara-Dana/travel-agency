package com.proiect.travel.agency.service;

import com.proiect.travel.agency.entity.ContinentModel;
import com.proiect.travel.agency.entity.CountryModel;
import com.proiect.travel.agency.entity.DestinationModel;
import com.proiect.travel.agency.repository.ContinentRepository;
import com.proiect.travel.agency.repository.CountryRepository;
import com.proiect.travel.agency.repository.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private ContinentRepository continentRepository;

    public List<DestinationModel> getAll(){
        List<DestinationModel> travelList = destinationRepository.findAll();
        return travelList;
    }

    public void addDestination(DestinationModel travelDestinationModel){

        ContinentModel continent = travelDestinationModel.getCountry().getContinent();
        ContinentModel continentSearch = continentRepository.findByName(continent.getName());
        if (continentSearch == null) {
            continentRepository.save(continent);
            continentSearch = continentRepository.findByName(continent.getName());
        }

        CountryModel country = travelDestinationModel.getCountry();
        CountryModel countrySearch = countryRepository.findByName(country.getName());
        if (countrySearch == null) {
            country.setContinent(continentSearch);
            countryRepository.save(country);
            countrySearch = countryRepository.findByName(country.getName());
        }

        countrySearch.setContinent(continentSearch);
        countryRepository.save(countrySearch);

        travelDestinationModel.setCountry(countrySearch);

        destinationRepository.save(travelDestinationModel);
    }

    public DestinationModel getById(Long id) {
        Optional<DestinationModel> destinationOptional = destinationRepository.findById(id);

        return destinationOptional.get();
    }

    public void editDestination(DestinationModel travelDestinationModel) {
        ContinentModel continent = travelDestinationModel.getCountry().getContinent();
        ContinentModel continentSearch = continentRepository.findByName(continent.getName());
        if (continentSearch == null) {
            continentRepository.save(continent);
            continentSearch = continentRepository.findByName(continent.getName());
        }

        CountryModel country = travelDestinationModel.getCountry();
        CountryModel countrySearch = countryRepository.findByName(country.getName());
        if (countrySearch == null) {
            country.setContinent(continentSearch);
            countryRepository.save(country);
            countrySearch = countryRepository.findByName(country.getName());
        }

        countrySearch.setContinent(continentSearch);
        countryRepository.save(countrySearch);

        travelDestinationModel.setCountry(countrySearch);

        destinationRepository.save(travelDestinationModel);
    }

    public void remove(Long id) {
        destinationRepository.deleteById(id);
    }

}
