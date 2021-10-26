package com.proiect.travel.agency.repository;

import com.proiect.travel.agency.entity.DestinationModel;
import com.proiect.travel.agency.entity.OfferModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<OfferModel, Long> {

//    @Query("  from OfferModel as offers join offers.destination as destinations " +
//            "where destinations.id = :destinationId and offers.pricePerNight < :maxPrice")
//    List<OfferModel> findOffers(@Param("maxPrice") String maxPrice,
//                                @Param("destinationId") long destinationId);

    @Query("   from OfferModel as offers where offers.pricePerNight <= :price")
    List<OfferModel> findPrice(@Param("price") double price);

    @Query("  from OfferModel as offers " +
            " where offers.destination.name like %:destination% ")
    List<OfferModel> findDestination(@Param("destination") String destinationName);


}

