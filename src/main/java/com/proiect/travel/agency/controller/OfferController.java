package com.proiect.travel.agency.controller;

import com.proiect.travel.agency.data.SearchOfferData;
import com.proiect.travel.agency.dto.BuyOfferDto;
import com.proiect.travel.agency.dto.OfferDto;
import com.proiect.travel.agency.entity.OfferModel;
import com.proiect.travel.agency.entity.UserModel;
import com.proiect.travel.agency.service.OfferService;
import com.proiect.travel.agency.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OfferController {


    @Autowired
    private OfferService offerService;

    @Autowired
    private UserService userService;

    @GetMapping("/offers/reservations")
    public ResponseEntity getReservations(@RequestParam("id") Long id) {
        List<UserModel> customers = offerService.getCustomer(id);
        return new ResponseEntity(customers, HttpStatus.OK);
    }

    @PostMapping("/offers/addOffer")
    public ResponseEntity addOffer(@RequestBody OfferDto offerDto) throws Exception {

        offerService.addOffer(offerDto);
        return new ResponseEntity(offerDto, HttpStatus.OK);
    }

    @PutMapping("/offers/editOffer/{id}")
    public ResponseEntity editOffer(@PathVariable("id") Long id, @RequestBody OfferDto offerDto) throws Exception {

        offerService.editOffer(id, offerDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping("/offers/deleteOffer/{id}")
    public ResponseEntity deleteOfferById(@PathVariable("id") Long id) {
        offerService.deleteOfferById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/offers/getOffers")
    public ResponseEntity getOffers() {
        List<OfferModel> offers = offerService.getOffers();
        return new ResponseEntity(offers, HttpStatus.OK);
    }

    @GetMapping("/offers/getOfferById/{id}")
    public ResponseEntity getOfferById(@PathVariable("id") Long id) {
        OfferModel offer = offerService.getOfferById(id);
        return new ResponseEntity(offer, HttpStatus.OK);
    }

    @PostMapping("/offers/buyOffer")
    public ResponseEntity buyOffer(@RequestBody BuyOfferDto buyOfferDto) {
        OfferModel offerModel = offerService.getOfferById(buyOfferDto.getOfferId());
        UserModel userModel = userService.getUserById(buyOfferDto.getUserId());
        return new ResponseEntity(offerModel, HttpStatus.OK);
    }
    @PostMapping("/offers/search")
    public ResponseEntity searchOffer(@RequestBody SearchOfferData searchOffer) throws Exception {
        List<OfferDto> offers = offerService.search(searchOffer);

        return new ResponseEntity(offers, HttpStatus.OK);
    }


}
