package com.carinsurance.policy;

import com.carinsurance.car.CarRepository;
import com.carinsurance.car.exception.CarNotFoundException;
import com.carinsurance.client.Client;
import com.carinsurance.car.Car;
import com.carinsurance.client.ClientRepository;
import com.carinsurance.client.exception.ClientNotFoundException;
import com.carinsurance.insurancecalculator.Calculator;
import com.carinsurance.insurancecalculator.UniqueStringGenerator;
import com.carinsurance.policy.dto.PolicyResponseDto;
import com.carinsurance.policy.exception.PolicyNotFoundException;
import lombok.AllArgsConstructor;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

import static com.carinsurance.policy.PolicyMapper.policyMapper;

@Service
@AllArgsConstructor
@Log4j2
public class PolicyService {

    private final PolicyRepository policyRepository;
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;
    private final Calculator calculator;
    private final UniqueStringGenerator generator;

    public Policy savePolicy(Long clientId, Long carId) {
        log.info("Creating new policy for client with ID {} and car with ID {}", clientId, carId);
        Car car = carRepository.findById(carId).orElseThrow(() -> new CarNotFoundException(carId));
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new ClientNotFoundException(clientId));

        Policy newPolicy = Policy.builder()
                .policyName(generator.generateUniqueString())
                .startDate(LocalDate.now())
                .endDate(LocalDate.now().plusYears(1))
                .priceOfInsurance(calculator.calculatePrice(car, client))
                .build();
        policyRepository.save(newPolicy);
        log.info("Created new policy {}", newPolicy);
        return newPolicy;

    }

    public PolicyResponseDto findPolicyById(Long id) {
        log.info("Finding policy with ID {}", id);
        Policy policy = policyRepository.findById(id).orElseThrow(() -> new PolicyNotFoundException(id));
        log.info("Found policy {}", policy);
        return policyMapper.entityToDto(policy);
    }


}

