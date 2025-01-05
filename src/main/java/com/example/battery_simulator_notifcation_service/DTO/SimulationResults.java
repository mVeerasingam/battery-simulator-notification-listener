package com.example.battery_simulator_notifcation_service.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SimulationResults {
    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("task_id")
    private String taskId;

    private String status;
    private Map<String, Object> results; // result is a dict, where {time(s):{output_variables_of_Simulation}}
    private String error;
}
