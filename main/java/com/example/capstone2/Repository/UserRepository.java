package com.example.capstone2.Repository;

import com.example.capstone2.Model.Garden;
import com.example.capstone2.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository

public interface UserRepository extends JpaRepository<User,Integer> {

    @Query("select u from User u where u.id=?1 ")
    User findPleaseById(Integer id);


    User findByEmail(String email);

    // Find users by role
    List<User> findByRole(String role);
}
