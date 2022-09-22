package com.app.Controller;

import com.app.Service.HospitalService;
import com.app.Service.LoginService;
import com.app.model.Addresses;
import com.app.model.Hospital;
import com.app.model.HospitalCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1/Hospital")
@CrossOrigin("*")
public class HospitalController {


    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private LoginService loginService;

    public HospitalController() {
    }

    @GetMapping("/firstLogin")
    public String showFirstLogin() {
        return "/Hospital/firstLogin";
    }

    @PostMapping("/firstLogin")
    public String processFirstLogin(@RequestParam String npassword, @RequestParam String cpassword,
                                    @RequestParam MultipartFile image, @RequestParam String latitude, @RequestParam String longitude,
                                    Model modelMap,HttpSession hs) {
        try {
            if (npassword.equals(cpassword)) {
                HospitalCoordinates coordinates = new HospitalCoordinates(Double.parseDouble(latitude),
                        Double.parseDouble(longitude));
                byte[] imageFile = image.getBytes();
                Hospital h = (Hospital) hs.getAttribute("userDetails");
                modelMap.addAttribute("message",
                        hospitalService.processFirstLogin(h, imageFile, cpassword, coordinates));
                return "redirect:/";
            } else {
                modelMap.addAttribute("error", "Password Not Matched");
                return "/Hospital/firstLogin";
            }
        } catch (Exception e) {
            modelMap.addAttribute("error", "Internal Server Error");
            return "/Hospital/firstLogin";
        }
    }

    @GetMapping("/Dashboard")
    public String showDashboard() {
        return "/Hospital/Dashboard";
    }

    @GetMapping("/hospitalprofile")
    public String showHospitalProfile() {
        return "/Hospital/hospitalprofile";
    }

    @PostMapping("/hospitalprofile")
    public String processUpdateProfile(@RequestParam String hospital_name, @RequestParam String mobile,
                                       @RequestParam String alt_mobile, @RequestParam String email, @RequestParam String city,
                                       @RequestParam String state, @RequestParam String district, @RequestParam String add_ln,
                                       @RequestParam int pin_code, @RequestParam String country, HttpSession hs, Model modelMap) {
        Hospital h = (Hospital) hs.getAttribute("userDetails");
        Addresses address = new Addresses(city, district, state, country, add_ln, pin_code);
        modelMap.addAttribute("success",
                hospitalService.updateHospital(hospital_name, mobile, alt_mobile, email, address, h));
        hs.setAttribute("userDetails", loginService.validateHospital(email, h.getPassword()));
        return "redirect:/Hospital/hospitalprofile";
    }

    @GetMapping("/updatePassword")
    public String showupdatePass() {
        return "/Hospital/updatePassword";
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
