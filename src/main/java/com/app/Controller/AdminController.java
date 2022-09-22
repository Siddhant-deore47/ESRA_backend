package com.app.Controller;

import com.app.Service.AdminService;
import com.app.model.Accidents;
import com.app.model.Feedback;
import com.app.model.Hospital;
import com.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/addNewHospital")
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
