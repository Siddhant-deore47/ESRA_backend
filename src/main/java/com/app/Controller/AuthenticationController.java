package com.app.Controller;

import com.app.Repository.HospitalRepository;
import com.app.Repository.PoliceStationRepository;
import com.app.Repository.UserRepository;
import com.app.Service.HospitalService;
import com.app.config.*;
import com.app.model.Hospital;
import com.app.model.PoliceStation;
import com.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class AuthenticationController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private HospitalService hospitalService;
    @Autowired
    private PoliceStationRepository stationService;
    @Autowired
    private UserRepository userService;

//    @GetMapping("/login")
//    public ResponseEntity<?> checkIfAlreadyLoggedIn(HttpServletRequest request) {
//        String authorizationToken = request.getHeader("Authorization");
//        String jwtToken = null;
//        String username = null;
//
//        if (authorizationToken != null && authorizationToken.startsWith("Bearer ")) {
//            jwtToken = authorizationToken.substring(7);
//            try {
//                username = this.jwtUtil.extractUsername(jwtToken);
//            } catch (ExpiredJwtException e) {
//                e.printStackTrace();
//                System.out.println("JWT Token Expired");
//            } catch (Exception exception) {
//                exception.printStackTrace();
//            }
//        } else {
//            System.out.println("Invalid token: does not start with Bearer");
//        }
//
//        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//            final UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(username);
//            if (this.jwtUtil.validateToken(jwtToken, userDetails)) {
//                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//                        userDetails, null, userDetails.getAuthorities());
//                usernamePasswordAuthenticationToken
//                        .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//
//                return ResponseEntity.ok(username);
//
//            } else {
//                System.out.println("Token is not valid");
//            }
//        }
//        return new ResponseEntity<>("Login required", HttpStatus.CONTINUE);
//    }

    @PostMapping("/login")
    public ResponseEntity<?> generateToken(@RequestBody JWTRequest jwtRequest, HttpServletResponse response) throws Exception {

        System.out.println(jwtRequest);

        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("Bad credentials");
        }

        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
        Object[] role = userDetails.getAuthorities().toArray();
        System.out.println(role[0].toString());

//        response.setHeader("role",role[0].toString());
        if(role[0].toString() == "ROLE_HOSPITAL"){
            Hospital hospital = hospitalService.findHospitalByEmail(userDetails.getUsername());
            String status = hospital.getStatus();
            response.setHeader("status",status);
        }else if (role[0].toString() == "ROLE_POLICESTATION"){
            PoliceStation policeStation = stationService.findPoliceStationByEmail(userDetails.getUsername());
            String status = policeStation.getStatus();
            response.setHeader("status",status);
        }else if (role[0].toString() == "ROLE_USER"){
            User user = userService.findUserByEmail(userDetails.getUsername());
            String status = user.getStatus();
            response.setHeader("status",status);
        }
        String token = this.jwtUtil.generateToken(userDetails);
        System.out.println("JWT " + token);

        return ResponseEntity.ok(new JWTResponse(token));
    }

    @GetMapping("/current-user")
    public CustomUserDetails getCurrentUser(Principal principal) {
        return (CustomUserDetails) customUserDetailsService.loadUserByUsername(principal.getName());
    }
}

