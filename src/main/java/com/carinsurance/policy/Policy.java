package com.carinsurance.policy;

import javax.persistence.*;

import com.carinsurance.car.CarModel;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "policy")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Policy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String policyName;

    @Enumerated(EnumType.STRING)
    private PolicyType policyType;

    private double priceOfInsurance;
    private LocalDate startDate;

    private LocalDate endDate;


}
