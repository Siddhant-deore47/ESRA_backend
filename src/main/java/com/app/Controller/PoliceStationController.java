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
@RequestMapping("/api/v1/policestation")
@CrossOrigin("*")
public class PoliceStationController {

    @Autowired
    private PoliceStationService stationService;

    @Autowired
    private LoginService loginService;

    public PoliceStationController() {
        System.out.println("in constr of " + getClass().getName());
    }


    @GetMapping("/viewnewaccidents/{email}")
    public ResponseEntity<List<Accidents>> showNewAccident(@PathVariable String email) {
        PoliceStation p = stationService.fetchPoliceStationByEmail(email);
        return ResponseEntity.of(Optional.of(stationService.fetchAccidentbyID(p)));
    }
}
