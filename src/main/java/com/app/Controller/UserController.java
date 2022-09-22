package com.app.Controller;

import com.app.Service.VehicleService;
import com.app.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/addVehicle")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle){
        return vehicleService.addVehicle(vehicle);
    }

    @GetMapping("/fetchVehicle")
    public List<Vehicle> fetchVehicles(){
        return vehicleService.fetchVehicles();
    }

    @GetMapping("/deleteVehicle/{vid}")
    public String removeVehicle(@PathVariable int vid) {
        return vehicleService.removeVehicle(vid);
    }
}
