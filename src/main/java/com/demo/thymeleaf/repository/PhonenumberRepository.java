package com.demo.thymeleaf.repository;

import com.demo.thymeleaf.model.Phonenumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhonenumberRepository extends JpaRepository <Phonenumber, Integer>{
    @Query("SELECT ph FROM Phonenumber ph WHERE ph.employee.id = ?1")
    List<Phonenumber> find_phone_by_employee_id(int employeeId);
}
