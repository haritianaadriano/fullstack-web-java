package com.demo.thymeleaf.service.utils;

import com.demo.thymeleaf.repository.PhonenumberRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class PhonenumberUtils {
    private PhonenumberRepository phonenumberRepository;

    public List<String> employeePhones(int idEmployee) {
        return phonenumberRepository.find_phone_by_employee_id(idEmployee).stream()
                .map(phones -> {
                    return phones.getPhoneNumber();
                })
                .toList();
    }
}
