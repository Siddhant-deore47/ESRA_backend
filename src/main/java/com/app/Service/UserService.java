package com.app.Service;

import com.app.Repository.AccidentRepository;
import com.app.Repository.UserRepository;
import com.app.model.AccidentCoordinates;
import com.app.model.Accidents;
import com.app.model.User;
import javafx.scene.canvas.GraphicsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccidentRepository accidentRepository;

    public User registerUser(User user) {
        try {
            return userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Accidents addAccident(Accidents a, AccidentCoordinates c) {
            a.setStatus("new");
            a.setCoordinates(c);
            return accidentRepository.save(a);
    }

    public User userFirstLogin(User user, byte[] imageFile, String cpassword) {
        User u = userRepository.findUserById(user.getId());
        u.setImage(imageFile);
        u.setPassword(cpassword);
        u.setStatus("Active");
        return userRepository.save(u);
    }

    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
