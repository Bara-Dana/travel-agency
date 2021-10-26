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
    @Autowired
    private CountryService countryService;


    public List<DestinationModel> getDestination() {
        return destinationRepository.findAll();
    }

    public void addDestination(DestinationDto destinationDto) {

        DestinationModel destination = new DestinationModel();

        destination.setName(destinationDto.getName());
        destination.setDescription(destinationDto.getDescription());

        CountryModel country = countryService.checkIfExist(destinationDto.getCountry());

        destination.setCountry(country);
        destinationRepository.save(destination);
    }


    public void editDestination(Long destinationId, DestinationDto destinationDto) throws Exception {

        Optional<DestinationModel> destinationModel = destinationRepository.findById(destinationId);

        DestinationModel destination = destinationModel.orElseThrow(() -> new Exception("Destinatia cu id " + destinationId + "nu exista"));

        destination.setName(destinationDto.getName());
        destination.setDescription(destinationDto.getDescription());

        Optional<CountryModel> countryModel = countryRepository.findById(destinationDto.getCountry().getId());

        CountryModel country = countryModel.orElseThrow(() -> new Exception("Tara cu id " + destinationDto.getCountry().getId() + "nu exista"));
        country.setName(destinationDto.getCountry().getName());
        country.setContinent(destinationDto.getCountry().getContinent());
        countryRepository.save(country);

        destination.setCountry(country);
        destinationRepository.save(destination);


    }

    public DestinationDto getPreviewById(Long id) throws Exception {
        DestinationModel destinationModel = destinationRepository.findById(id).orElseThrow(() -> new Exception("Destinatia cu id " + id + " nu exista"));
        return destinationModel.toDestinationDto();

    }

    public void remove(Long id) {
        destinationRepository.deleteById(id);
    }

    public DestinationModel getById(Long id) {
        return destinationRepository.findById(id).orElse(null);
    }


}
