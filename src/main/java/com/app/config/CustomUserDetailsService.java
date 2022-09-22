package com.app.config;

import com.app.Repository.AdminRepository;
import com.app.Repository.HospitalRepository;
import com.app.Repository.PoliceStationRepository;
import com.app.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private HospitalRepository hospitalRepository;
	@Autowired
	private PoliceStationRepository policeStationRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		String status = "";
		if (adminRepository.findAdminByEmail(email) != null) {
			return new CustomUserDetails(adminRepository.findAdminByEmail(email));
		} else if (userRepository.findUserByEmail(email) != null) {
			return new CustomUserDetails(userRepository.findUserByEmail(email));
		} else if (hospitalRepository.findHospitalByEmail(email) != null) {
			return new CustomUserDetails(hospitalRepository.findHospitalByEmail(email));
		} else if (policeStationRepository.findPoliceStationByEmail(email) != null) {
			return new CustomUserDetails(policeStationRepository.findPoliceStationByEmail(email));
		} else {
			throw new UsernameNotFoundException("No user");
		}
	}
}
