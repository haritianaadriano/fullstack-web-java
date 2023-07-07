package com.demo.thymeleaf.service;

import com.demo.thymeleaf.model.Employee;
import com.demo.thymeleaf.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository repository;

    public List<Employee> getEmployee(){
        return repository.findAll();
    }

    public List<Employee> crUpdateEmployee(Employee newEmployee){
        repository.save(newEmployee);
        return repository.findAll();
    }
}
