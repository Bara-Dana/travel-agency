package com.proiect.travel.agency.service;

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
    private OfferRepository travelOfferRepository;

    public List<UserModel> getCustomer(Long offerId){
        Optional<OfferModel> offerFound = travelOfferRepository.findById(offerId);
        OfferModel travelOfferModel = offerFound.get();
        List<UserModel> customerModels = travelOfferModel.getCustomers();

        return customerModels;
    }

    public void addOffer(OfferModel travelOfferModel) {
        travelOfferRepository.save(travelOfferModel);
    }

    public void editOffer(OfferModel travelOfferModel) {
        travelOfferRepository.save(travelOfferModel);
    }

    public List<OfferModel> findOffers(double maxPrice, long destinationId) {
        return travelOfferRepository.findOffers(maxPrice, destinationId);
    }

    public OfferModel getOfferById(Long id) {
        return travelOfferRepository.findById(id).orElse(null);
    }

    public void deleteOfferById (Long id) {
        travelOfferRepository.deleteById(id);
    }

    public List<OfferModel> getOffers (){
        return travelOfferRepository.findAll();
    }


}
