package com.demo.thymeleaf.repository;

import com.demo.thymeleaf.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository <Employee, Integer> {
}
