package com.app.Controller;

import com.app.Service.LoginService;
import com.app.Service.PoliceStationService;
import com.app.model.Accidents;
import com.app.model.Addresses;
import com.app.model.PoliceStation;
import com.app.model.StationCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/PoliceStation")
@CrossOrigin("*")
public class PoliceStationController {

    @Autowired
    private PoliceStationService stationService;

    @Autowired
    private LoginService loginService;

    public PoliceStationController() {
        System.out.println("in constr of " + getClass().getName());
    }

    @GetMapping("/firstLogin")
    public String showFirstLogin() {
        return "/PoliceStation/firstLogin";
    }

    @PostMapping("/firstLogin")
    public ResponseEntity<PoliceStation> processFirstLogin(Authentication authentication, @RequestParam String npassword, @RequestParam String cpassword,
                                            @RequestParam MultipartFile image, @RequestParam String latitude, @RequestParam String longitude) {
        try {
            if (npassword.equals(cpassword)) {
                StationCoordinates coordinates = new StationCoordinates(Double.parseDouble(latitude),
                        Double.parseDouble(longitude));
                byte[] imageFile = image.getBytes();
                String email = authentication.getName();
                PoliceStation ps = stationService.fetchPoliceStationByEmail(email);
                return ResponseEntity.of(Optional.of(stationService.processFirstLogin(ps, imageFile, cpassword, coordinates)));

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/Dashboard")
    public String showDashboard() {
        return "/PoliceStation/Dashboard";
    }

    @GetMapping("/stationprofile")
    public String showStationProfile(HttpSession hs) {
        PoliceStation police = (PoliceStation) hs.getAttribute("userDetails");
        hs.setAttribute("userDetails", loginService.validateStation(police.getEmail(), police.getPassword()));
        return "/PoliceStation/stationprofile";
    }

    @PostMapping("/stationprofile")
    public String processUpdateProfile(@RequestParam String station_name, @RequestParam String mobile,
                                       @RequestParam String alt_mobile, @RequestParam String email, @RequestParam String city,
                                       @RequestParam String state, @RequestParam String district, @RequestParam String add_ln,
                                       @RequestParam int pin_code, @RequestParam String country, HttpSession hs, Model modelMap) {
        PoliceStation ps = (PoliceStation) hs.getAttribute("userDetails");
        Addresses address = new Addresses(city, district, state, country, add_ln, pin_code);
        modelMap.addAttribute("success",
                stationService.updateStation(station_name, mobile, alt_mobile, email, address, ps));

        return "redirect:/PoliceStation/stationprofile";
    }

    @GetMapping("/viewnewaccidents")
    public ResponseEntity<List<Accidents>> showNewAccident(Authentication authentication) {
        String email = authentication.getName();
        PoliceStation p = stationService.fetchPoliceStationByEmail(email);
        return ResponseEntity.of(Optional.of(stationService.fetchAccidentbyID(p)));
    }
}
