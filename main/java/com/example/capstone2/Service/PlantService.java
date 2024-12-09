package com.example.capstone2.Service;

import com.example.capstone2.ApiResponse.ApiException;
import com.example.capstone2.Model.Plant;
import com.example.capstone2.Repository.PlantRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlantService {
    public PlantService(PlantRepository plantRepository, ArrayList<Plant> plantList) {
        this.plantRepository = plantRepository;
        this.plantList = plantList;
    }

    private final PlantRepository plantRepository;
    private  List<Plant> plantStore = new ArrayList<>();

    // Get all plants
    public List<Plant> getAllPlants() {
        return plantRepository.findAll();
    }

    // Add a new plant
    public void addPlant(Plant plant) {
        plantRepository.save(plant);
    }

    // Update an existing plant
    public void updatePlant(Integer id, Plant plant) {
        Plant existingPlant = plantRepository.findPleaseById(id);

        if (existingPlant == null) {
            throw new ApiException("Plant not found");
        }

        existingPlant.setName(plant.getName());
        existingPlant.setType(plant.getType());
        existingPlant.setWaterRequirement(plant.getWaterRequirement());

        plantRepository.save(existingPlant);
    }

    // Delete a plant by ID
    public void deletePlant(Integer id) {
        Plant plant = plantRepository.findPleaseById(id);

        if (plant == null) {
            throw new ApiException("Plant not found");
        }

        plantRepository.delete(plant);
    }

    //1
    public List<Plant> filterPlantsByType(String type) {
        // Fetch all plants from the database
        List<Plant> allPlants = plantRepository.findAll();

        // Create an empty list to store the filtered plants
        List<Plant> filteredPlants = new ArrayList<>();

        // Iterate over all plants and filter them by type
        for (Plant plant : allPlants) {
            if (plant.getType().equalsIgnoreCase(type)) {
                // Add plant to the filtered list if the type matches
                filteredPlants.add(plant);
            }
        }

        // Return the filtered list
        return filteredPlants;
    }

    public List<Plant> suggestPlantsForSeason(String season) {
        // Fetch all plants from the database
        List<Plant> allPlants = plantRepository.findAll();
        List<Plant> suggestedPlants = new ArrayList<>();

        // Loop through plants and filter by season
        for (Plant plant : allPlants) {
            if (plant.getSeason().equalsIgnoreCase(season)) {
                suggestedPlants.add(plant);
            }
        }

        // Return the list of suggested plants
        return suggestedPlants;
    }

    public String checkWaterRequirement(Integer id, Double waterProvided) {
        // Fetch the plant from the database (using the plant ID)
        Plant plant = plantRepository.findById(id).orElse(null);
        if (plant == null) {
            throw new ApiException("Plant not found.");
        }

        // Check if the water provided is enough
        if (waterProvided >= plant.getWaterRequirement()) {
            return "Water provided is sufficient.";
        } else {
            return "Water provided is not sufficient. The required amount is " + plant.getWaterRequirement() + " liters.";
        }
    }



    //2
    ArrayList<Plant>plantList=new ArrayList<>();




    //3
    public List<Plant> searchPlantsByWaterRequirement(double minWater, double maxWater) {
        List<Plant> matchingPlants = new ArrayList<>();
        for (Plant plant : plantList) {
            if (plant.getWaterRequirement() >= minWater && plant.getWaterRequirement() <= maxWater) {
                matchingPlants.add(plant);
            }
        }
        return matchingPlants;
    }


    //4
    public Plant findPlantById(Integer plantId) {
        Plant plant=plantRepository.findPlantById(plantId);
        if(plant==null){
            throw new ApiException("not found");
        }
        return plantRepository.findPlantById(plantId);
    }

    //5
    public List<Plant> findPlantsByType(String type) {
        return plantRepository.findByType(type);
    }

}

