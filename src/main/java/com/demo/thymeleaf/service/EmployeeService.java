package com.demo.thymeleaf.service;

import com.demo.thymeleaf.controller.form.EmployeeForm;
import com.demo.thymeleaf.controller.mapper.EmployeeMapper;
import com.demo.thymeleaf.model.Employee;
import com.demo.thymeleaf.model.Phonenumber;
import com.demo.thymeleaf.repository.PhonenumberRepository;
import com.demo.thymeleaf.repository.Repository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private Repository repository;
    private EmployeeMapper mapper;
    private PhonenumberRepository phonenumberRepository;

    public List<Employee> getEmployee(){
        return repository.findAll();
    }

    public Employee insertEmployee(EmployeeForm toCreate) {
        Employee employee = repository.save(mapper.toDomain(toCreate));
        phonenumberRepository.save(
                Phonenumber.builder()
                        .phoneNumber(toCreate.getPhonenumber())
                        .employee(employee)
                        .build()
        );
        return employee;
    }

    public Employee updateEmployee(Employee toUpdate) {
        return repository.save(mapper.toDomain(toUpdate));
    }
}
