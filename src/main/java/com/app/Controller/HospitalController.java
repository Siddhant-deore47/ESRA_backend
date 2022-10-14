package com.app.Controller;

import com.app.Service.HospitalService;
import com.app.Service.LoginService;
import com.app.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/hospital")
@CrossOrigin("*")
public class HospitalController {


    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private LoginService loginService;

    public HospitalController() {
    }


    @PostMapping("/firstLogin")
    public ResponseEntity<Hospital> processFirstLogin(Authentication authentication, @RequestParam String npassword, @RequestParam String cpassword,
                                                           @RequestParam MultipartFile image, @RequestParam String latitude, @RequestParam String longitude) {
        try {
            if (npassword.equals(cpassword)) {
                HospitalCoordinates coordinates = new HospitalCoordinates(Double.parseDouble(latitude),
                        Double.parseDouble(longitude));
                byte[] imageFile = image.getBytes();
                String email = authentication.getName();
                Hospital h = hospitalService.findHospitalByEmail(email);
                return ResponseEntity.of(Optional.of(hospitalService.processFirstLogin(h, imageFile, cpassword, coordinates)));

            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/updatePassword")
    public String processUpdatePassword(@RequestParam String cpassword, @RequestParam String npassword,
                                        @RequestParam String conpassword, HttpSession hs, Model modelMap, RedirectAttributes flashMap) {
        Hospital h = (Hospital) hs.getAttribute("userDetails");
        if (cpassword.equals(h.getPassword())) {
            if (conpassword.equals(npassword)) {

                modelMap.addAttribute("success", hospitalService.changePassword(h.getId(), npassword));
                hs.setAttribute("userDetails", loginService.validateHospital(h.getEmail(), npassword));
                return "redirect:/Hospital/updatePassword";

            } else {
                modelMap.addAttribute("success", "New Password And confirm Password not Matched");
                return "/Hospital/updatePassword";
            }
        } else {
            modelMap.addAttribute("success", "Current Password Password not matched");
            return "/Hospital/updatePassword";
        }

    }

}
