package com.demo.thymeleaf.model;

import com.demo.thymeleaf.model.type.Csp;
import com.demo.thymeleaf.model.type.Sexe;
import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "employee")
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
    @Enumerated(value = STRING)
    private Sexe sexe;
    private String adress;
    private String job;
    @Enumerated(value = STRING)
    private Csp csp;
    private String CNAPS;
    private int children;
    private LocalDate beginDate;
    @Nullable
    private LocalDate finishDate;
    @Email
    @NotEmpty
    private String emailPro;
    @Email
    @NotEmpty
    private String emailPerso;
    @OneToOne
    private Cin cin;
    private LocalDate birthdate;
    @OneToOne
    private Profile profile;
    String phones;
}
