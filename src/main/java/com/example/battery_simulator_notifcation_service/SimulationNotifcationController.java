package com.example.battery_simulator_notifcation_service;
import com.example.battery_simulator_notifcation_service.DTO.SimulationResults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimulationNotifcationController {
    private static final Logger logger = LoggerFactory.getLogger(SimulationNotifcationController.class);
    @PostMapping("/notification")
    public ResponseEntity<String> receiveWebhook(@RequestBody SimulationResults request) {

        logger.info("Received Simulation Notification for: " + request.getSimulation_id() + ", Status: " + request.getStatus());

        if("success".equals(request.getStatus())) {
            logger.info("Simulation successful. Results: " + request.getResults());
        } else {
            logger.info("Simulation failed. Error: " + request.getError());
        }

        return new ResponseEntity<>("Notification received", HttpStatus.OK);
    }
}