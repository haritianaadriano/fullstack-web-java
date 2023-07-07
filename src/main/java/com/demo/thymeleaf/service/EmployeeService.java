package com.demo.thymeleaf.service;

import com.demo.thymeleaf.model.Employee;
import com.demo.thymeleaf.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.demo.thymeleaf.service.EmployeeUtils.findLastEmployee;

@Service
@AllArgsConstructor
public class EmployeeService {
    private EmployeeRepository repository;

    public List<Employee> getEmployee(){
        return repository.findAll();
    }

    public List<Employee> crUpdateEmployee(Employee newEmployee){
        Employee lastEmployee = findLastEmployee(repository);
        String lastRef = lastEmployee.getRef();
        int lastNumber = Integer.parseInt(lastRef.substring(lastRef.lastIndexOf("-") + 1));
        int newNumber = lastNumber + 1;
        String newRef = "EP-" + newNumber;

        newEmployee.setRef(newRef);
        repository.save(newEmployee);
        return repository.findAll();
    }
}
