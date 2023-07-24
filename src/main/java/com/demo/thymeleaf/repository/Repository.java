package com.demo.thymeleaf.repository;

import com.demo.thymeleaf.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository <Employee, Integer>, JpaSpecificationExecutor<Employee> {
}
