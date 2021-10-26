package com.proiect.travel.agency.repository;

import com.proiect.travel.agency.entity.CountryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends JpaRepository<CountryModel, Long> {

    @Query("from CountryModel as country where country.name = :countryName and country.continent = :continentName")
    Optional<CountryModel> findCountryByNameAndContinent(@Param("countryName") String countryName,
                                                         @Param("continentName") String continentName);


}
