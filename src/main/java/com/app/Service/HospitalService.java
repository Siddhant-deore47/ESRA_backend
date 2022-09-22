package com.app.Service;

import com.app.Repository.HospitalRepository;
import com.app.model.Addresses;
import com.app.model.Hospital;
import com.app.model.HospitalCoordinates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;
    public Hospital processFirstLogin(Hospital h, byte[] imageFile, String cpassword, HospitalCoordinates coordinates) {
        Hospital hospital = hospitalRepository.findHospitalById(h.getId());
        hospital.setImage(imageFile);
        hospital.setPassword(cpassword);
        hospital.setHospitalCoordinates(coordinates);
        hospital.setStatus("Active");
        return hospitalRepository.save(hospital);
    }

    public Hospital updateHospital(String hospital_name, String mobile, String alt_mobile, String email,
                                 Addresses address, Hospital h) {
        Hospital hs = hospitalRepository.findHospitalById(h.getId());
        hs.setEmail(email);
        hs.setAltMobile(alt_mobile);
        hs.setMobile(mobile);
        hs.setName(hospital_name);
        hs.setHospitalAddress(address);
        return hospitalRepository.save(hs);
    }

    public Hospital changePassword(Integer id, String npassword) {
        Hospital h = hospitalRepository.findHospitalById(id);
        h.setPassword(npassword);
        return hospitalRepository.save(h);
    }

    public Hospital findHospitalByEmail(String username) {
        return hospitalRepository.findHospitalByEmail(username);
    }
}
