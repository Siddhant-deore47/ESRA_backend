package com.app.Repository;

import com.app.model.HospitalCoordinates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalCoordinatesRepository extends JpaRepository<HospitalCoordinates,Integer> {
}
