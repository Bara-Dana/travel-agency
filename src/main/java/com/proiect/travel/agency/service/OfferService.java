package com.proiect.travel.agency.service;

import com.proiect.travel.agency.dto.OfferDto;
import com.proiect.travel.agency.entity.DestinationModel;
import com.proiect.travel.agency.entity.OfferModel;
import com.proiect.travel.agency.entity.UserModel;
import com.proiect.travel.agency.repository.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OfferService {
    @Autowired
    private OfferRepository offerRepository;

    public List<UserModel> getCustomer(Long offerId){
        Optional<OfferModel> offerFound = offerRepository.findById(offerId);
        OfferModel travelOfferModel = offerFound.get();
        List<UserModel> customerModels = travelOfferModel.getCustomers();

        return customerModels;
    }

    public void addOffer(OfferDto offerDto) {

        DestinationModel destinationModel = new DestinationModel();

        OfferModel offerModel = new OfferModel();
        offerModel.setTitle(offerDto.getTitle());
        offerModel.setDescription(offerDto.getDescription());
        offerModel.setContactNumber(offerDto.getContactNumber());
        offerModel.setPricePerNight(offerDto.getPricePerNight());
        offerModel.setDestination(destinationModel);
        offerModel.setImageUrl(offerDto.getImageUrl());

        offerRepository.save(offerModel);
    }

    public void editOffer(Long offerId, OfferDto offerDto) throws Exception {

        Optional<OfferModel> offerModel = offerRepository.findById(offerId);

        OfferModel offer = offerModel.orElseThrow(() -> new Exception("Oferta cu id " + offerId + "nu exista"));


        offer.setTitle(offerDto.getTitle());
        offer.setDescription(offerDto.getDescription());
        offer.setContactNumber(offerDto.getContactNumber());
        offer.setPricePerNight(offerDto.getPricePerNight());
        offer.setDestination(offerModel.get().getDestination());
        offer.setImageUrl(offerDto.getImageUrl());

        offerRepository.save(offer);
    }

    public List<OfferModel> findOffers(double maxPrice, long destinationId) {
        return offerRepository.findOffers(maxPrice, destinationId);
    }

    public OfferModel getOfferById(Long id) {



        return offerRepository.findById(id).orElse(null);
    }

    public void deleteOfferById (Long id) {
        offerRepository.deleteById(id);
    }

    public List<OfferModel> getOffers (){
        return offerRepository.findAll();
    }


}
