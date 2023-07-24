package com.demo.thymeleaf.service.utils;

import com.demo.thymeleaf.model.Phonenumber;
import com.demo.thymeleaf.repository.PhonenumberRepository;
import com.demo.thymeleaf.repository.Repository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Component
public class PhonenumberUtils {
    private final PhonenumberRepository phonenumberRepository;
    private final Repository repository;

    public String employeePhones(int idEmployee) {
        List<Phonenumber> phones = phonenumberRepository.find_phone_by_employee_id(idEmployee);
        StringBuilder stringBuilder = new StringBuilder();
        for(Phonenumber phone : phones) {
            stringBuilder.append(phone.getPhoneNumber());
            stringBuilder.append(" ");
        }

        return String.valueOf(stringBuilder);
    }

    public List<Phonenumber> checkPhonenumber(String phonenumber, int idEmployee) {
        String[] phonenumbers = phonenumber.split(";");
        List<Phonenumber> phonenumberData = new ArrayList<>();

        for (int i = 0; i < phonenumbers.length; i++) {
            Phonenumber phone = Phonenumber.builder()
                    .phoneNumber(phonenumbers[i])
                    .employee(repository.findById(idEmployee).get())
                    .build();
            phonenumberData.add(phone);
        }

        return phonenumberData;
    }
}
