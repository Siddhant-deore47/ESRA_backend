package com.app.Repository;

import com.app.model.User;
import com.app.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select u from User u where u.id = ?1")
    User findUserById(int id);

    @Query("select v from Vehicle v where v.parentUser =:u")
    List<Vehicle> fetchVehicles(User u);

    @Query("select u from User u where u.email =: email")
    User findUserByEmail(String email);
}
