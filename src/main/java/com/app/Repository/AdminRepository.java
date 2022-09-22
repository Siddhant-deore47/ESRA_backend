package com.app.Repository;

import com.app.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    @Query("select a from Admin a where a.email=:email and a.password=:password")
    Admin validateAdmin(String email, String password);

    @Query("select a from Admin a where a.email=:email")
    Admin findAdminByEmail(String email);
}
