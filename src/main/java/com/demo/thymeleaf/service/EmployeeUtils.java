package com.demo.thymeleaf.service;

import com.demo.thymeleaf.model.Employee;
import com.demo.thymeleaf.repository.EmployeeRepository;

import java.util.List;

public class EmployeeUtils {
    public static Employee findLastEmployee(EmployeeRepository repository){
        List<Employee> employeeList = repository.findAll();
        if(employeeList.isEmpty()){
            return Employee.builder()
                    .ref("EP-0")
                    .build();
        }
        return employeeList.get(employeeList.size()-1);
    }
}
