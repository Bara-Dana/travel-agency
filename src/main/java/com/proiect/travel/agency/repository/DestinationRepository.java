package com.proiect.travel.agency.repository;

import com.proiect.travel.agency.entity.DestinationModel;
import com.proiect.travel.agency.entity.OfferModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<DestinationModel, Long> {

    @Query("  from DestinationModel as destionations  where destionations.name like :destination")
    List<DestinationModel> findDestination(@Param("destination") String destinationName);



}
