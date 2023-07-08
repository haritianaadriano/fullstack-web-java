package com.demo.thymeleaf.model;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Profile {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @OneToOne
    private Employee employee;

    @Lob
    private byte[] img;
}
