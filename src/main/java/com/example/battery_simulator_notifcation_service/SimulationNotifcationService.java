package com.example.battery_simulator_notifcation_service;

import com.example.battery_simulator_notifcation_service.DTO.SimulationResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SimulationNotifcationService {

    private final SimulationResultsRepository repository;

    @Autowired
    public SimulationNotifcationService(SimulationResultsRepository repository) {
        this.repository = repository;
    }

    // get all simulations for a specfidied user
    public List<SimulationResults> getSimulationsByUser(String userId) {
        return repository.findByUserId(userId);
    }

    // get a simulation by it's taskId and userId
    public Optional<SimulationResults> getSimulationByTaskIdAndUserId(String userId, String taskId) {
        return repository.findByUserIdAndTaskId(userId, taskId);
    }

    public SimulationResults saveSimulation(SimulationResults simulationResults) {
        return repository.save(simulationResults);
    }

    // Delete a simulation result by taskId and userId
    public boolean deleteSimulation(String userId, String taskId) {
        Optional<SimulationResults> simulationResults = repository.findByUserIdAndTaskId(userId, taskId);
        if (simulationResults.isPresent()) {
            repository.delete(simulationResults.get());
            return true;
        }
        return false;
    }
}
