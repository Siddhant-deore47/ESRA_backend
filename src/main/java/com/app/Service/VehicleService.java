package com.app.Service;

import com.app.Repository.UserRepository;
import com.app.Repository.VehicleRepository;
import com.app.model.User;
import com.app.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;
    public Vehicle addVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    public List<Vehicle> fetchVehicles() {
        return vehicleRepository.findAll();
    }

    public String removeVehicle(int vid) {
        Vehicle vehicle = vehicleRepository.findVehicleById(vid);
        vehicleRepository.delete(vehicle);
        return "Vehicle Removed Successfully";
    }
}
