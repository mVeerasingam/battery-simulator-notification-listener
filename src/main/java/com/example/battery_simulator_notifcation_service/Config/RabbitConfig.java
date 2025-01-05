package com.example.battery_simulator_notifcation_service.Config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    @Bean
    public Queue simulationResultsQueue() {
        return new Queue("simulation_results", true);
    }
}
