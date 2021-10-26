package com.proiect.travel.agency.controller;

import com.proiect.travel.agency.dto.DestinationDto;
import com.proiect.travel.agency.entity.CountryModel;
import com.proiect.travel.agency.entity.DestinationModel;
import com.proiect.travel.agency.entity.OfferModel;
import com.proiect.travel.agency.service.CountryService;
import com.proiect.travel.agency.service.DestinationService;
import com.proiect.travel.agency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class DestinationController {
    @Autowired
    private DestinationService destinationService;


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

        List<DestinationModel> destinations = destinationService.getDestination();

        return new ResponseEntity(destinations, HttpStatus.OK);

    }

    @PostMapping("/destination/addDestination")
    public ResponseEntity addDestination(@RequestBody DestinationDto destinationDto) {

        destinationService.addDestination(destinationDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/destination/editDestination/{id}")
    public ResponseEntity editDestination(@PathVariable("id") Long id, @RequestBody DestinationDto destinationDto) throws Exception {

        destinationService.editDestination(id, destinationDto);
        return new ResponseEntity( HttpStatus.OK);
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
    @GetMapping("destination/previewDestination/{id}")
    public ResponseEntity previewDestination(@PathVariable ("id") Long destinationId) throws Exception {
        DestinationDto destinationDto = destinationService.getPreviewById(destinationId);

        return new ResponseEntity(destinationDto, HttpStatus.OK);
    }

}
