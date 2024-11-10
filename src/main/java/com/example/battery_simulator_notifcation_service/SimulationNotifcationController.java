package com.example.battery_simulator_notifcation_service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SimulationNotifcationController {

    private static final Logger logger = LoggerFactory.getLogger(SimulationNotifcationController.class);
    @PostMapping("/webhook")
    public ResponseEntity<String> receiveWebhook(@RequestBody SimulationNotificationRequest request) {

        logger.info("Received Simulation Notification for: " + request.getTask_id() + ", Status: " + request.getStatus());

        if("success".equals(request.getStatus())) {
            logger.info("Simulation successful. Results: " + request.getResults());
        } else {
            logger.info("Simulation failed. Error: " + request.getError());
        }

        return new ResponseEntity<>("Notification received", HttpStatus.OK);
    }
}