package com.demo.thymeleaf.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "phone_number")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Phonenumber {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    private String phoneNumber;

    @ManyToOne
    private Employee employee;
}
