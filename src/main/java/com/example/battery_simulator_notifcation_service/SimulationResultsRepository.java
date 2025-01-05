package com.example.battery_simulator_notifcation_service;

import com.example.battery_simulator_notifcation_service.DTO.SimulationResults;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SimulationResultsRepository extends MongoRepository<SimulationResults, Long> {
}
