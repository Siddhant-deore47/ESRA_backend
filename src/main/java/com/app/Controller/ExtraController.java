package com.app.Controller;


import com.app.Service.FeedbackService;
import com.app.Service.PoliceStationService;
import com.app.Service.UserService;
import com.app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class ExtraController {

    @Autowired
    private UserService userService;
    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private PoliceStationService stationService;

    @PostMapping("/registerUser")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        User u =userService.registerUser(user);
        if (u == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.of(Optional.of(u));
    }

    @PostMapping("/addFeedback")
    public Feedback addFeedback(@RequestBody Feedback feedback){
        return feedbackService.addFeedback(feedback);
    }

    @PostMapping("/informaccident")
    public ResponseEntity<?> informAccident(@RequestParam String name, @RequestParam String vehicalno,
                                         @RequestParam String latitude, @RequestParam String longitude, @RequestParam String count) {
        AccidentCoordinates ac = new AccidentCoordinates(Double.parseDouble(latitude), Double.parseDouble(longitude));
        PoliceStation nearestStation = stationService.fetchPoliceStationByCoordinates(Double.parseDouble(latitude),Double.parseDouble(longitude));
        Hospital nearestHospital = stationService.fetchHospitalByCoordinates(Double.parseDouble(latitude),Double.parseDouble(longitude));
        System.out.println(nearestStation);
        AccidentCoordinates c = new AccidentCoordinates(Double.parseDouble(latitude),
                Double.parseDouble(longitude));
        Accidents a = new Accidents(name, vehicalno, ac, Integer.parseInt(count), nearestStation);
        System.out.println(a);
        userService.addAccident(a, c);
        return ResponseEntity.of(Optional.of(nearestHospital));
    }

    @GetMapping("/feedback")
    public List<Feedback> getFeedback(){
        return feedbackService.getfeedback();
    }
}
