package com.demo.thymeleaf.service;

import com.demo.thymeleaf.model.Employee;
import com.demo.thymeleaf.repository.Repository;

import java.util.List;

public class EmployeeUtils {
    public static Employee findLastEmployee(Repository repository){
        List<Employee> employeeList = repository.findAll();
        if(employeeList.isEmpty()){
            return Employee.builder()
                    .ref("EP-0")
                    .build();
        }
        return employeeList.get(employeeList.size()-1);
    }
    public static String mapRefId(Employee employee) {
        String lastRef = employee.getRef();
        int lastNumber = Integer.parseInt(lastRef.substring(lastRef.lastIndexOf("-") + 1));
        int newNumber = lastNumber + 1;
        return "EP-" + newNumber;
    }
}
