package com.app.Repository;

import com.app.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle,Integer> {

    @Query("select v from Vehicle v where v.id =: vid")
    Vehicle findVehicleById(int vid);
}
