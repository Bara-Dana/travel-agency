package com.proiect.travel.agency.controller;

import com.proiect.travel.agency.dto.DestinationDto;
import com.proiect.travel.agency.entity.CountryModel;
import com.proiect.travel.agency.entity.DestinationModel;
import com.proiect.travel.agency.entity.OfferModel;
import com.proiect.travel.agency.service.ContinentService;
import com.proiect.travel.agency.service.CountryService;
import com.proiect.travel.agency.service.DestinationService;
import com.proiect.travel.agency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DestinationController {
    @Autowired
    private DestinationService destinationService;

    @Autowired
    private ContinentService continentService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private UserService userService;

    @GetMapping("/destination/getDestinationById/{id}")
    public ResponseEntity getDestinationById(@PathVariable Long id) {
       DestinationModel destination = destinationService.getById(id);
        return new ResponseEntity(destination, HttpStatus.OK);
    }

    @GetMapping("/destination/getDestinations")
    public ResponseEntity getDestinations() {

        List<DestinationModel> destinations = destinationService.getAll();

        return new ResponseEntity(destinations, HttpStatus.OK);
    }

    @PostMapping("/destination/addDestination")
    public ResponseEntity addDestination(@RequestBody DestinationDto destinationDto) {
        CountryModel countryModel = countryService.getById(destinationDto.getCountryId());
        if (countryModel == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

        DestinationModel destination = new DestinationModel();
        destination.setName(destinationDto.getName());
        destination.setDescription(destinationDto.getDescription());
        destination.setCountry(countryModel);

        destinationService.addDestination(destination);
        return new ResponseEntity(destination, HttpStatus.OK);
    }

    @PutMapping("/destination/editDestination/{id}")
    public ResponseEntity editDestination(@PathVariable("id") Long id, @RequestBody DestinationDto destinationDto) {
        CountryModel countryModel = countryService.getById(destinationDto.getCountryId());
        if (countryModel == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        DestinationModel destination = new DestinationModel();
        destination.setId(id);
//        destination.setId(destinationDto.getId());
        destination.setName(destinationDto.getName());
        destination.setDescription(destinationDto.getDescription());
        destination.setCountry(countryModel);

        destinationService.editDestination(destination);
        return new ResponseEntity(destination, HttpStatus.OK);
    }

    @DeleteMapping("/destination/deleteDestination/{id}")
    public ResponseEntity deleteDestination(@PathVariable("id") Long id) {
        destinationService.remove(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/destination/offers")
    public ResponseEntity viewOffers(@RequestParam("destinationId") Long destinationId) {
        DestinationModel destination = destinationService.getById(destinationId);
        List<OfferModel> offers = destination.getOffers();
        return new ResponseEntity(offers, HttpStatus.OK);
    }
}