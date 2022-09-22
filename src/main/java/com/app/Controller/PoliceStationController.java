package com.app.Controller;

import com.app.Service.LoginService;
import com.app.Service.PoliceStationService;
import com.app.model.Addresses;
import com.app.model.PoliceStation;
import com.app.model.StationCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

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
    public String processFirstLogin(@RequestParam String npassword, @RequestParam String cpassword,
                                    @RequestParam MultipartFile image, @RequestParam String latitude, @RequestParam String longitude,
                                    Model modelMap, RedirectAttributes flashMap, HttpSession hs) {
        try {
            if (npassword.equals(cpassword)) {
                StationCoordinates coordinates = new StationCoordinates(Double.parseDouble(latitude),
                        Double.parseDouble(longitude));
                byte[] imageFile = image.getBytes();
                PoliceStation ps = (PoliceStation) hs.getAttribute("userDetails");
                modelMap.addAttribute("message",
                        stationService.processFirstLogin(ps, imageFile, cpassword, coordinates));
                return "redirect:/";
            } else {
                modelMap.addAttribute("error", "Password Not Matched");
                return "/PoliceStation/firstLogin";
            }
        } catch (Exception e) {
            modelMap.addAttribute("error", "Internal Server Error");
            return "/PoliceStation/firstLogin";
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
                                       @RequestParam int pin_code, @RequestParam String country, HttpSession hs, Model modelMap,
                                       RedirectAttributes flashMap) {
        PoliceStation ps = (PoliceStation) hs.getAttribute("userDetails");
        Addresses address = new Addresses(city, district, state, country, add_ln, pin_code);
        modelMap.addAttribute("success",
                stationService.updateStation(station_name, mobile, alt_mobile, email, address, ps));

        return "redirect:/PoliceStation/stationprofile";
    }

    @GetMapping("/viewnewaccidents")
    public String showNewAccident(HttpSession hs) {
        PoliceStation p = (PoliceStation) hs.getAttribute("userDetails");
        hs.setAttribute("accidentList", stationService.fetchAccidentbyID(p));
        return "/PoliceStation/viewnewaccidents";
    }
}
