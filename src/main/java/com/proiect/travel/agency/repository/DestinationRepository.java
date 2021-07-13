package com.proiect.travel.agency.repository;

import com.proiect.travel.agency.entity.DestinationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DestinationRepository extends JpaRepository<DestinationModel, Long> {
}
