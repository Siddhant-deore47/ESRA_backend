package com.app.Controller;


import com.app.Repository.VehicleRepository;
import com.app.Service.FeedbackService;
import com.app.Service.UserService;
import com.app.Service.VehicleService;
import com.app.model.Feedback;
import com.app.model.User;
import com.app.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ExtraController {

    @Autowired
    private UserService userService;
    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/registerUser")
    public User registerUser(@RequestBody User user){
        return userService.registerUser(user);
    }

    @PostMapping("/addFeedback")
    public Feedback addFeedback(@RequestBody Feedback feedback){
        return feedbackService.addFeedback(feedback);
    }

    @GetMapping("/feedback")
    public List<Feedback> getFeedback(){
        return feedbackService.getfeedback();
    }
}
