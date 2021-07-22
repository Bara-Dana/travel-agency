package com.proiect.travel.agency.service;


import com.proiect.travel.agency.dto.DestinationDto;
import com.proiect.travel.agency.entity.CountryModel;
import com.proiect.travel.agency.entity.DestinationModel;
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



    public List<DestinationModel> getAll() {
        List<DestinationModel> travelList = destinationRepository.findAll();
        return travelList;
    }

    public void addDestination(DestinationDto destinationDto) {

        DestinationModel destination = new DestinationModel();

        destination.setName(destinationDto.getName());
        destination.setDescription(destinationDto.getDescription());

        CountryModel country = new CountryModel();

        country.setName(destinationDto.getCountryDto().getName());
        country.setContinent(destinationDto.getCountryDto().getContinent());

       country =  countryRepository.save(country);
        destination.setCountry(country);
        destinationRepository.save(destination);
    }


    public void editDestination(Long destinationId, DestinationDto destinationDto) throws Exception {

        Optional<DestinationModel> destinationModel = destinationRepository.findById(destinationId);

        DestinationModel destination = destinationModel.orElseThrow(() -> new Exception("Destinatia cu id " + destinationId + "nu exista"));

        destination.setName(destinationDto.getName());
        destination.setDescription(destinationDto.getDescription());

        Optional<CountryModel> countryModel = countryRepository.findById(destinationDto.getCountryDto().getId());

        CountryModel country = countryModel.orElseThrow(() -> new Exception("Tara cu id " + destinationDto.getCountryDto().getId() + "nu exista"));
        country.setName(destinationDto.getCountryDto().getName());
        country.setContinent(destinationDto.getCountryDto().getContinent());
        countryRepository.save(country);

        destination.setCountry(country);
        destinationRepository.save(destination);
        ;

    }

    public DestinationModel getById(Long id) {
        Optional<DestinationModel> destinationOptional = destinationRepository.findById(id);

        return destinationOptional.get();
    }

    public void remove(Long id) {
        destinationRepository.deleteById(id);
    }

}
