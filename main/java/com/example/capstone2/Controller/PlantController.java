package com.example.capstone2.Controller;

import com.example.capstone2.ApiResponse.ApiResponse;
import com.example.capstone2.Model.Plant;
import com.example.capstone2.Service.PlantService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/capstone/v1/plants")
public class PlantController {
    public PlantController(PlantService plantService) {
        this.plantService = plantService;
    }

    private final PlantService plantService;

    // Get all plants
    @GetMapping("get")
    public ResponseEntity getAllPlants() {
        return ResponseEntity.status(200).body(plantService.getAllPlants());
    }

    // Add a new plant
    @PostMapping("/add")
    public ResponseEntity addPlant(@RequestBody @Valid Plant plant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        plantService.addPlant(plant);
        return ResponseEntity.status(200).body(new ApiResponse("Plant added successfully"));
    }

    // Update an existing plant
    @PutMapping("/update/{id}")
    public ResponseEntity updatePlant(@PathVariable Integer id, @RequestBody @Valid Plant plant, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new ApiResponse(message));
        }
        plantService.updatePlant(id, plant);
        return ResponseEntity.status(200).body(new ApiResponse("Plant updated successfully"));
    }

    // Delete a plant by ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deletePlant(@PathVariable Integer id) {
        plantService.deletePlant(id);
        return ResponseEntity.status(200).body(new ApiResponse("Plant deleted successfully"));
    }

    //18
    // Filter plants by type
    @GetMapping("/type/{type}")
    public ResponseEntity filterPlantsByType(@PathVariable String type) {
        return ResponseEntity.status(200).body(plantService.filterPlantsByType(type));
    }


    //19
    // Suggest plants for a season
    @GetMapping("/season/{season}")
    public ResponseEntity suggestPlantsForSeason(@PathVariable String season) {
        return ResponseEntity.status(200).body(plantService.suggestPlantsForSeason(season));
    }

    //20
    // Check if provided water is enough for a plant
    @GetMapping("/{id}/check-water/{waterProvided}")
    public ResponseEntity checkWaterRequirement(@PathVariable Integer id, @PathVariable Double waterProvided) {
        return ResponseEntity.status(200).body(plantService.checkWaterRequirement(id, waterProvided));
    }


    //23
    // Find plant by ID
    @GetMapping("/{id}")
    public ResponseEntity findPlantById(@PathVariable Integer id) {
        Plant plant = plantService.findPlantById(id);
        return ResponseEntity.status(200).body(plant);
    }

    //24
    // Find plants by type
    @GetMapping("/find/type/{type}")
    public ResponseEntity findPlantsByType(@PathVariable String type) {
        return ResponseEntity.status(200).body(plantService.findPlantsByType(type));
    }
}
