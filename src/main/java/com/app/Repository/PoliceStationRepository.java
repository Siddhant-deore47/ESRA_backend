package com.app.Repository;

import com.app.model.PoliceStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PoliceStationRepository extends JpaRepository<PoliceStation,Integer> {

    @Query("select p from PoliceStation p where p.email =: email")
    PoliceStation findPoliceStationByEmail(String email);

    @Query("select p from PoliceStation p where p.id =: id")
    PoliceStation findPoliceStationById(int id);
}
