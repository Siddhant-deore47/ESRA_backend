package com.app.Service;

import com.app.Repository.*;
import com.app.model.*;
import javafx.scene.canvas.GraphicsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HospitalRepository hospitalRepository;
    @Autowired
    private FeedbackRepository feedbackRepository;
    @Autowired
    private AccidentRepository accidentRepository;
    @Autowired
    private PoliceStationRepository policeStationRepository;


    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<User> fetchUserList() {
        return userRepository.findAll();
    }

    public User findUserById(int uid) {
        return userRepository.findUserById(uid);
    }

    public String removeUser(int uid) {
        User user = userRepository.findUserById(uid);
        userRepository.delete(user);
        return "User Removed Successfully";
    }

    public Hospital registerHospital(Hospital hospital) {
        String encodedPassword = this.passwordEncoder.encode(hospital.getPassword());
        hospital.setPassword(encodedPassword);
        return hospitalRepository.save(hospital);
    }

    public String toggleHospitalStatus(int hid) {
        Hospital hospital = hospitalRepository.findHospitalById(hid);
        String status = hospital.getStatus();
        if (status.equals("new")) {
            hospital.setStatus("Active");
        } else if (status.equals("Active")) {
            hospital.setStatus("Inactive");
        } else {
            hospital.setStatus("Active");
        }
        return "Status Updated";
    }

    public Hospital fetchHospitalById(int hid) {
        return hospitalRepository.findHospitalById(hid);
    }

    public String removeHospital(int hid) {
        Hospital hospital = hospitalRepository.findHospitalById(hid);
        hospitalRepository.delete(hospital);
        return "Hospital Removed Successfully";
    }
    public PoliceStation registerPoliceStation(PoliceStation policeStation) {
        String encodedPassword = this.passwordEncoder.encode(policeStation.getPassword());
        policeStation.setPassword(encodedPassword);
        return policeStationRepository.save(policeStation);
    }

    public List<PoliceStation> fetchStationsList() {
        return policeStationRepository.findAll();
    }
//
//    public String toggleStationStatus(int sid) {
//        PoliceStation p = policeStationRepository.findPoliceStationById(sid);
//        if (p.getStatus().equals("new")) {
//            p.setStatus("Active");
//        } else if (p.getStatus().equals("Active")) {
//            p.setStatus("Inactive");
//        } else {
//            p.setStatus("Active");
//        }
//        return null;
//    }
//
    public PoliceStation findStationById(int sid) {
        return policeStationRepository.findPoliceStationById(sid);
    }
//
    public String removeStation(int sid) {
        PoliceStation p = policeStationRepository.findPoliceStationById(sid);
        policeStationRepository.delete(p);
        return "Police Station Removed Successfully";
    }

    public List<Feedback> fetchAllFeedback() {
        return feedbackRepository.findAll();
    }

    public List<Accidents> fetchAllAccidents() {
        return accidentRepository.findAll();
    }

    public List<Hospital> fetchHospitalList() {
        List<Hospital> hospitalList = new ArrayList<>();
        List<Hospital> hospitals = hospitalRepository.findAll();
        if (!hospitals.isEmpty())
            return hospitals;
        return null;
    }
}
