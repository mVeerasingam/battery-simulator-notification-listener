package com.example.battery_simulator_notifcation_service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimulationNotificationRequest {
    private String task_id;
    private String status;
    private List<Object> results;
    private String error;
}
