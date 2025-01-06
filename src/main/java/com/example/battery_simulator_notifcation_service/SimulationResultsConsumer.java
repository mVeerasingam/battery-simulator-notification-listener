package com.example.battery_simulator_notifcation_service;

import com.example.battery_simulator_notifcation_service.DTO.SimulationResults;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulationResultsConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final SimulationResultsRepository repository;

    @Autowired
    public SimulationResultsConsumer(SimulationResultsRepository repository) {
        this.repository = repository;
    }

    @RabbitListener(queues = "simulation_results")
    public void consumeMessage(String message) {
        try {
            // Deserialize the message into SimulationResults object
            SimulationResults results = objectMapper.readValue(message, SimulationResults.class);

            // Save the results to MongoDB
            repository.save(results);

            System.out.println("User ID " + results.getUserId() +
                    " Saved SimulationResult with Task ID: " + results.getTaskId());
        } catch (Exception e) {
            // Handle errors in deserialization and processing
            System.err.println("Failed to process message: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
