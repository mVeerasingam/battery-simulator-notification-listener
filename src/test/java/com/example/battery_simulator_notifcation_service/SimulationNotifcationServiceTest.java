package com.example.battery_simulator_notifcation_service;

import com.example.battery_simulator_notifcation_service.DTO.SimulationResults;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SimulationNotifcationServiceTest {

    @Mock
    private SimulationResultsRepository repository;

    @InjectMocks
    private SimulationNotifcationService simulationNotifcationService;

    private SimulationResults simulationResults;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        simulationResults = new SimulationResults();
        simulationResults.setUserId("user1");
        simulationResults.setTaskId("task1");
        simulationResults.setStatus("Completed");
        simulationResults.setError(null);
    }

    @Test
    void testGetSimulationsByUser() {
        List<SimulationResults> simulations = Collections.singletonList(simulationResults);
        when(repository.findByUserId("user1")).thenReturn(simulations);

        List<SimulationResults> result = simulationNotifcationService.getSimulationsByUser("user1");

        assertEquals(1, result.size());
        assertEquals("task1", result.get(0).getTaskId());
        verify(repository, times(1)).findByUserId("user1");
    }

    @Test
    void testGetSimulationByTaskIdAndUserId() {
        when(repository.findByUserIdAndTaskId("user1", "task1")).thenReturn(Optional.of(simulationResults));

        Optional<SimulationResults> result = simulationNotifcationService.getSimulationByTaskIdAndUserId("user1", "task1");

        assertTrue(result.isPresent());
        assertEquals("Completed", result.get().getStatus());
        verify(repository, times(1)).findByUserIdAndTaskId("user1", "task1");
    }

    @Test
    void testSaveSimulation() {
        when(repository.save(simulationResults)).thenReturn(simulationResults);

        SimulationResults result = simulationNotifcationService.saveSimulation(simulationResults);

        assertNotNull(result);
        assertEquals("task1", result.getTaskId());
        verify(repository, times(1)).save(simulationResults);
    }

    @Test
    void testDeleteSimulation() {
        when(repository.findByUserIdAndTaskId("user1", "task1")).thenReturn(Optional.of(simulationResults));
        doNothing().when(repository).delete(simulationResults);

        boolean result = simulationNotifcationService.deleteSimulation("user1", "task1");

        assertTrue(result);
        verify(repository, times(1)).findByUserIdAndTaskId("user1", "task1");
        verify(repository, times(1)).delete(simulationResults);
    }
}
