package com.demo.thymeleaf.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "CIN")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cin {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private Double number;
    private LocalDate deliveryDate;
    private String deliveryLocation;
}
