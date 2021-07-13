package com.proiect.travel.agency.repository;

import com.proiect.travel.agency.entity.ContinentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContinentRepository extends JpaRepository<ContinentModel, Long> {
    ContinentModel findByName(String name);
}
