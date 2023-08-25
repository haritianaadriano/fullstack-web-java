package com.demo.thymeleaf.controller.form;

import com.demo.thymeleaf.model.type.Csp;
import com.demo.thymeleaf.model.type.Sexe;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeForm {
    private String firstname;
    private String lastname;
    private String emailPro;
    private String emailPerso;
    private String job;
    private String adress;
    private String CNAPS;
    private Csp csp;
    private String phonenumber;
    private LocalDate birthdate;
    private Sexe gender;
    private int children;
    private LocalDate begindate;
    private LocalDate finishdate;
    private Integer CIN_number;
    private LocalDate CIN_delivery_date;
    private String CIN_delivery_location;
}
