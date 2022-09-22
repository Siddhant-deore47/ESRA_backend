//package com.app.Controller;
//
//import com.app.Repository.AdminRepository;
//import com.app.Service.LoginService;
//import com.app.model.Admin;
//import com.app.model.Hospital;
//import com.app.model.PoliceStation;
//import com.app.model.User;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//import javax.servlet.http.HttpSession;
//
//@RestController
//@RequestMapping("/api/v1/")
//@CrossOrigin("*")
//public class LoginController {
//
//    @Autowired
//    private LoginService loginService;
//    @PostMapping("/login")
//    public String processLogin(@RequestParam String email, @RequestParam String password, HttpSession hs,RedirectAttributes flashMap) {
//
//
//        Admin admin = loginService.validateAdmin(email, password);
//        User user = loginService.validateUser(email, password);
//        Hospital hospital = loginService.validateHospital(email, password);
//        PoliceStation station = loginService.validateStation(email, password);
//        String status = "";
//        if (admin != null) {
//            hs.setAttribute("userDetails", admin);
//            return "redirect:/Admin/Dashboard";
//        } else if (user != null) {
//            status = user.getStatus();
//            switch (status) {
//                case "new": {
//                    hs.setAttribute("userDetails", user);
//                    return "redirect:/User/firstLogin";
//                }
//                case "Active": {
//                    hs.setAttribute("userDetails", user);
//                    return "redirect:/User/Dashboard";
//                }
//                case "Inactive": {
//                    flashMap.addFlashAttribute("error", "Please contact Admin to Reactive Your Profile");
//                    return "redirect:/";
//                }
//            }
//        } else if (hospital != null) {
//            status = hospital.getStatus();
//            switch (status) {
//                case "new": {
//                    hs.setAttribute("userDetails", hospital);
//                    return "redirect:/Hospital/firstLogin";
//                }
//                case "Active": {
//                    hs.setAttribute("userDetails", hospital);
//                    return "redirect:/Hospital/Dashboard";
//                }
//                case "Inactive": {
//                    flashMap.addFlashAttribute("error", "Please contact Admin to Reactive Your Profile");
//                    return "redirect:/";
//                }
//            }
//        } else if (station != null) {
//            status = station.getStatus();
//            switch (status) {
//                case "new": {
//                    hs.setAttribute("userDetails", station);
//                    return "redirect:/PoliceStation/firstLogin";
//                }
//                case "Active": {
//                    hs.setAttribute("userDetails", station);
//                    return "redirect:/PoliceStation/Dashboard";
//                }
//                case "Inactive": {
//                    flashMap.addFlashAttribute("error", "Please contact Admin to Reactive Your Profile");
//                    return "redirect:/";
//                }
//            }
//        } else {
//            flashMap.addFlashAttribute("error", "You are not registerd");
//            return "redirect:/signin";
//        }
//        return "/index";
//
//    }
//
//    @GetMapping("/logout")
//    public String logoutUsers() {
//        return "logout";
//    }
//}
