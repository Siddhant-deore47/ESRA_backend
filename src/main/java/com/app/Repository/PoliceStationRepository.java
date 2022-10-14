package com.app.Repository;

import com.app.model.Accidents;
import com.app.model.PoliceStation;
import com.app.model.StationCoordinates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface PoliceStationRepository extends JpaRepository<PoliceStation,Integer> {

    @Query("select p from PoliceStation p where p.email =: email")
    PoliceStation findPoliceStationByEmail(String email);


    PoliceStation findByEmail(String email);

    @Query("select p from PoliceStation p where p.id =: id")
    PoliceStation findPoliceStationById(int id);


    PoliceStation findById(int id);

    @Query("select p from PoliceStation p where p.coordinates=:nearestcoordinates")
    PoliceStation findPoliceStationByCoordinates(StationCoordinates nearestcoordinates);
    @Query("select a from Accidents a where a.nearestPoliceStation=:p")
    List<Accidents> findPoliceStationByNearestCoordinates(PoliceStation p);
}
