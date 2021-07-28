package com.proiect.travel.agency.repository;

import com.proiect.travel.agency.dto.UserDto;
import com.proiect.travel.agency.entity.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {
    @Query("from UserModel  as users where users.email = :userEmail  ")
    Optional<UserModel>checkIfExistUserByEmail(@Param("userEmail") String userEmail);


    @Query("from UserModel as users where users.username= :username ")
    Optional<UserModel>findByUsername(@Param("username")String username);


}


