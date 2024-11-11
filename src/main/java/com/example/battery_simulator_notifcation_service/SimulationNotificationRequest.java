package com.example.battery_simulator_notifcation_service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimulationNotificationRequest {
    private String simulation_id;
    private String status;
    private Map<String, Object> results; // result is a dict, where {time(s):{output_variables_of_Simulation}}
    private String error;
}
