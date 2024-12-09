package com.example.capstone2.Service;

import com.example.capstone2.ApiResponse.ApiException;
import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.Garden;
import com.example.capstone2.Model.User;
import com.example.capstone2.Repository.GardenRepository;
import com.example.capstone2.Repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GardenService {
    public GardenService(GardenRepository gardenRepository, UserRepository userRepository) {
        this.gardenRepository = gardenRepository;
        this.userRepository = userRepository;
    }

    private final GardenRepository gardenRepository;
    private final UserRepository userRepository;
    private final List<Garden> gardenStore = new ArrayList<>();



    // Get all gardens
    public List<Garden> getAllGardens() {
        return gardenRepository.findAll();
    }

    public void addGarden(Garden garden, Integer userId) {
        // Check if the user exists
        User user = userRepository.findPleaseById(userId);
        if (user == null) {
            throw new ApiException("User not found");
        }

        // Save the garden
        gardenRepository.save(garden);
    }

    // Update an existing garden
    public void updateGarden(Integer id, Garden garden) {
        Garden existingGarden = gardenRepository.findGardenById(id);

        if (existingGarden == null) {
            throw new ApiException("Garden not found");
        }

        existingGarden.setName(garden.getName());
        existingGarden.setLocation(garden.getLocation());
        existingGarden.setSizeOfTheGarden(garden.getSizeOfTheGarden());

        gardenRepository.save(existingGarden);
    }

    // Delete a garden by ID
    public void deleteGarden(Integer id) {
        Garden garden = gardenRepository.findGardenById(id);

        if (garden == null) {
            throw new ApiException("Garden not found");
        }

        gardenRepository.delete(garden);
    }
    public String assignGardenerToGarden(Integer gardenerId, Integer gardenId) {
        for (Garden garden : gardenStore) {
            if (garden.getId().equals(gardenId)) {
                return "Gardener " + gardenerId + " assigned to garden: " + garden.getName();
            }
        }
        return "Garden not found.";
    }

    public List<String> recommendPlantsForGarden(String sizeOfTheGarden) {
        // Initialize an empty list for recommendations
        List<String> recommendations = new ArrayList<>();

        // Check the size of the garden and add recommendations accordingly
        if (sizeOfTheGarden.equalsIgnoreCase("Big")) {
            recommendations.add("Oak Tree");
            recommendations.add("Pine Tree");
            recommendations.add("Palm Tree");
        } else if (sizeOfTheGarden.equalsIgnoreCase("Medium")) {
            recommendations.add("Rose");
            recommendations.add("Lavender");
            recommendations.add("Daffodil");
        } else if (sizeOfTheGarden.equalsIgnoreCase("Small")) {
            recommendations.add("Succulent");
            recommendations.add("Cactus");
            recommendations.add("Tulip");
        }

        // Return the list of recommendations
        return recommendations;
    }

    //14
    public Garden findGardenByName(String gardenName) {
        Garden garden=gardenRepository.findByName(gardenName);
        if (garden == null) {
           throw new ApiException("not found");
        }
        return garden;
    }

}
