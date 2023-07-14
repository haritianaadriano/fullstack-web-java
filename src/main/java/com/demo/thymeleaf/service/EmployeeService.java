package com.demo.thymeleaf.service;

import com.demo.thymeleaf.model.Employee;
import com.demo.thymeleaf.repository.Repository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

import static com.demo.thymeleaf.service.EmployeeUtils.findLastEmployee;
import static com.demo.thymeleaf.service.EmployeeUtils.mapRefId;

@Service
@AllArgsConstructor
public class EmployeeService {
    private Repository repository;

    public List<Employee> getEmployee(){
        return repository.findAll();
    }

    public List<Employee> crUpdateEmployee(Employee newEmployee){
        Employee lastEmployee = findLastEmployee(repository);
        newEmployee.setRef(mapRefId(lastEmployee));
        repository.save(newEmployee);
        return repository.findAll();
    }
}
