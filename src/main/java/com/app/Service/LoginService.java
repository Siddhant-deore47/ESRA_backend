package com.app.Service;

import com.app.model.Admin;
import com.app.model.Hospital;
import com.app.model.PoliceStation;
import com.app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {




    public Admin validateAdmin(String email, String password) {
        return null;
    }

    public User validateUser(String email, String password) {
        return null;
    }

    public Hospital validateHospital(String email, String password) {
        return null;
    }

    public PoliceStation validateStation(String email, String password) {
        return null;
    }
}
