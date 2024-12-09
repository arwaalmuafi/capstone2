package com.example.capstone2.Service;

import com.example.capstone2.ApiResponse.ApiException;
import com.example.capstone2.Model.Request;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.RequestRepository;
import com.example.capstone2.Repository.UserRepository;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service

public class UserService {
    public UserService(UserRepository userRepository, RequestRepository requestRepository) {
        this.userRepository = userRepository;
        this.requestRepository = requestRepository;
    }

    private final UserRepository userRepository;
    private final RequestRepository requestRepository;


    // Get all users
        public List<User> getAllUsers() {
            return userRepository.findAll();
        }

        // Add a new user
        public void addUser(User user) {
            userRepository.save(user);
        }

        // Update an existing user
        public void updateUser(Integer id, User user) {
           User existingUser = userRepository.findPleaseById(id);

            if (existingUser == null) {
                throw new ApiException("User not found");
            }

            existingUser.setName(user.getName());
            existingUser.setEmail(user.getEmail());
            existingUser.setRole(user.getRole());

            userRepository.save(existingUser);
        }

        // Delete a user by ID
        public void deleteUser(Integer id) {
            User user = userRepository.findPleaseById(id);

            if (user == null) {
                throw new ApiException("User not found");
            }

            userRepository.delete(user);
        }


    //15
    public List<User> getUsersWithIncompleteRequests() {
        List<User> result = new ArrayList<>();

        for (User user : userRepository.findAll()) {
            for (Request request : requestRepository.findAll()) {
                if (request.getStatus().equals("Complete")) {
                    continue;
                }
                if (request.getDetails().contains(user.getName())) {
                    result.add(user);
                }
            }
        }
        return result;
    }

    //16
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //17
    public List<User> findUsersByRole(String role) {
        return userRepository.findByRole(role);
    }


}