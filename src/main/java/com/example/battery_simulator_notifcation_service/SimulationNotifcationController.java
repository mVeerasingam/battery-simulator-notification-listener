package com.example.battery_simulator_notifcation_service;

import com.example.battery_simulator_notifcation_service.DTO.SimulationResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/simulations")
public class SimulationNotifcationController {

    private final SimulationNotifcationService simulationNotifcationService;

    @Autowired
    public SimulationNotifcationController(SimulationNotifcationService simulationNotifcationService) {
        this.simulationNotifcationService = simulationNotifcationService;
    }

    // get all simulations for a given user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SimulationResults>> getSimulationsByUser(@PathVariable String userId) {
        List<SimulationResults> simulations = simulationNotifcationService.getSimulationsByUser(userId);
        if (simulations.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(simulations);
    }

    // get a specific simulation by taskId and userId
    @GetMapping("/user/{userId}/task/{taskId}")
    public ResponseEntity<SimulationResults> getSimulationByTaskIdAndUserId(@PathVariable String userId, @PathVariable String taskId) {
        Optional<SimulationResults> simulation = simulationNotifcationService.getSimulationByTaskIdAndUserId(userId, taskId);
        return simulation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<SimulationResults> createOrUpdateSimulation(@RequestBody SimulationResults simulationResults) {
        SimulationResults savedSimulation = simulationNotifcationService.saveSimulation(simulationResults);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSimulation);
    }

    @DeleteMapping("/user/{userId}/task/{taskId}")
    public ResponseEntity<Void> deleteSimulation(@PathVariable String userId, @PathVariable String taskId) {
        boolean deleted = simulationNotifcationService.deleteSimulation(userId, taskId);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
