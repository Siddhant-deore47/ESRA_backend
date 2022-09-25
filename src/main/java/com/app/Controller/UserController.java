package com.app.Controller;

import com.app.Service.UserService;
import com.app.Service.VehicleService;
import com.app.model.PoliceStation;
import com.app.model.StationCoordinates;
import com.app.model.User;
import com.app.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private UserService userService;

    @PostMapping("/firstLogin")
    public ResponseEntity<User> processFirstLogin(Authentication authentication, @RequestParam String npassword, @RequestParam String cpassword,
                                                  @RequestParam MultipartFile image) {
        try {
            if (npassword.equals(cpassword)) {
                byte[] imageFile = image.getBytes();
                String email = authentication.getName();
                User user = userService.findUserByEmail(email);
                return ResponseEntity.of(Optional.of(userService.userFirstLogin(user, imageFile, cpassword)));

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

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
