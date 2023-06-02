package com.carinsurance.insurancecalculator;

import com.carinsurance.car.Car;
import com.carinsurance.client.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CalculatePointsTest {


    private CalculatePoints calculatePoints;

    @Mock
    private CalculatorOC calculatorOC;
    @Mock
    private CalculatorAC calculatorAC;
    @Mock
    Car car;
    @Mock
    Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        calculatePoints = new CalculatePoints(calculatorOC, calculatorAC);

    }

    @Test
    @DisplayName("Should return 0.25 points")
    void calculate_points_for_OC_insurance() {

        double expectedPoints = 0.025;

        when(calculatorOC.pointsForClientAge(client)).thenReturn(0.005);
        when(calculatorOC.pointsForVehicleAge(car)).thenReturn(0.005);
        when(calculatorOC.pointsForEnginCapacity(car)).thenReturn(0.005);
        when(calculatorOC.pointsForAverageKMTraveledPerYear(car)).thenReturn(0.005);
        when(calculatorOC.pointsForTypeOfVehicle(car)).thenReturn(0.005);

        double actualPoints = calculatePoints.calculatePointsForOC(car, client);

        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    @DisplayName("Should return 0.22 points")
    void calculate_points_for_OC_insurance_second() {

        double expectedPoints = 0.022;

        when(calculatorOC.pointsForClientAge(client)).thenReturn(0.005);
        when(calculatorOC.pointsForVehicleAge(car)).thenReturn(0.007);
        when(calculatorOC.pointsForEnginCapacity(car)).thenReturn(0.002);
        when(calculatorOC.pointsForAverageKMTraveledPerYear(car)).thenReturn(0.005);
        when(calculatorOC.pointsForTypeOfVehicle(car)).thenReturn(0.003);

        double actualPoints = calculatePoints.calculatePointsForOC(car, client);

        assertEquals(expectedPoints, actualPoints);
    }

    @Test
    @DisplayName("Should return 0.43 points")
    void calculate_points_for_AC() {

        double expectedPoints = 0.043;

        when(calculatorAC.pointsForClientAge(client)).thenReturn(0.005);
        when(calculatorAC.pointsForVehicleAge(car)).thenReturn(0.005);
        when(calculatorAC.pointsForParkingType(car)).thenReturn(0.02);
        when(calculatorAC.pointsForEnginCapacity(car)).thenReturn(0.003);
        when(calculatorAC.pointsForAverageKMTraveledPerYear(car)).thenReturn(0.005);
        when(calculatorAC.pointsForTypeOfVehicle(car)).thenReturn(0.005);

        double actualPoints = calculatePoints.calculatePointsForAC(car, client);

        assertEquals(expectedPoints, actualPoints);
    }

}



