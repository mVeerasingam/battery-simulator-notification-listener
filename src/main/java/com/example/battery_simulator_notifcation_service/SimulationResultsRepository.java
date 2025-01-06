package com.example.battery_simulator_notifcation_service;

import com.example.battery_simulator_notifcation_service.DTO.SimulationResults;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SimulationResultsRepository extends MongoRepository<SimulationResults, Long> {
    List<SimulationResults> findByUserId(String userId);
    Optional<SimulationResults> findByUserIdAndTaskId(String userId, String taskId);
}
