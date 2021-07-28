package com.proiect.travel.agency.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.proiect.travel.agency.dto.OfferDto;
import org.springframework.data.repository.cdi.Eager;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "offers")
public class OfferModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double pricePerNight;
    private String description;
    private String title;
    private String contactNumber;
    private String imageUrl;

    @ManyToOne
    private DestinationModel destination;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "offers_customers", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "offer_id")})
    private List<UserModel> customers = new ArrayList<>();


    public OfferDto toOfferDto(){
        OfferDto offerDto= new OfferDto();
        offerDto.setId(this.id);
        offerDto.setDescription(this.description);
        offerDto.setTitle(this.title);
        offerDto.setPricePerNight(this.pricePerNight);
        offerDto.setImageUrl(imageUrl);
        offerDto.setDestinationId(this.destination.getId());

        return offerDto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(double pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public DestinationModel getDestination() {
        return destination;
    }

    public void setDestination(DestinationModel destination) {
        this.destination = destination;
    }

    public List<UserModel> getCustomers() {
        return customers;
    }

    public void setCustomers(List<UserModel> customers) {
        this.customers = customers;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
