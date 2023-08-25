package com.demo.thymeleaf.service.utils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;


public class AgeUtils {
    public static int howOldAreI(LocalDate bithdate) {
        return Period.between(bithdate, LocalDate.now()).getYears();
    }
}
