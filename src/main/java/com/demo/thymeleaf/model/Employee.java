package com.demo.thymeleaf.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee implements Serializable {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;

    @Pattern(regexp = "^EP-[0-9]+")
    private String ref;

    @NotEmpty
    private String firstname;

    @NotEmpty
    private String lastname;

    private LocalDate birthdate;

    @OneToOne
    private Profile profile;
}
