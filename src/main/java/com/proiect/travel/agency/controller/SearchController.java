package com.proiect.travel.agency.controller;

import com.proiect.travel.agency.entity.OfferModel;
import com.proiect.travel.agency.service.DestinationService;
import com.proiect.travel.agency.service.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class SearchController {
    @Autowired
    private DestinationService destinationService;

    @Autowired
    private OfferService offerService;


    @GetMapping("/searchOffer")
    public ResponseEntity searchOffer(@RequestParam("maxPrice") Double maxPrice, @RequestParam("destinationId") Long destinationId) {
        List<OfferModel> offers = offerService.findOffers(maxPrice, destinationId);
        return new ResponseEntity(offers, HttpStatus.OK);
    }
}
