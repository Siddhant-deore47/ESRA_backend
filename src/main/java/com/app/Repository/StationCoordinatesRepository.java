package com.app.Repository;

import com.app.model.StationCoordinates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StationCoordinatesRepository extends JpaRepository<StationCoordinates,Integer> {
}
