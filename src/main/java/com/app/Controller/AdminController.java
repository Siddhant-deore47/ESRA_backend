package com.app.Controller;

import com.app.Service.AdminService;
import com.app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@CrossOrigin("*")
public class AdminController {
    @Autowired
    private AdminService adminService;

    //USER
    @PostMapping("/addNewUser")
    public User addNewUser(@RequestBody User user){
        return adminService.registerUser(user);
    }

    @GetMapping("/availableUser")
    public List<User> showAvailableUsers() {
        return adminService.fetchUserList();
    }
    @GetMapping("/deleteUser/{uid}")
    public String removeUser(@PathVariable int uid) {
        return adminService.removeUser(uid);
    }

    //Hospital
    @PostMapping(value = "/addNewHospital",consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public Hospital addNewHospital(@RequestBody Hospital hospital){
        return adminService.registerHospital(hospital);
    }

    @GetMapping("/availableHospitals")
    public List<Hospital> showHospitals() {
        return adminService.fetchHospitalList();
    }

    @GetMapping("/deleteHospital/{hid}")
    public String removeHospital(@PathVariable int hid) {
        return adminService.removeHospital(hid);
    }
    @PostMapping(value = "/addNewPoliceStation",consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
    public PoliceStation addNewPoliceStation(@RequestBody PoliceStation policeStation){
        return adminService.registerPoliceStation(policeStation);
    }

    @GetMapping("/availablePoliceStations")
    public List<PoliceStation> showPoliceStation() {
        return adminService.fetchStationsList();
    }

    @GetMapping("/deletePoliceStation/{pid}")
    public String removePoliceStation(@PathVariable int pid) {
        return adminService.removeStation(pid);
    }
//
//    @GetMapping("/availablestations")
//    public List<PoliceStation> showPoliceStationList() {
//
//        return adminService.fetchStationsList();
//    }
//
    @GetMapping("/feedback")
    public List<Feedback> showFeedback() {
        return adminService.fetchAllFeedback();
    }

    @GetMapping("/accidentHistory")
    public List<Accidents> showAccidentList() {
        return adminService.fetchAllAccidents();
    }
}
