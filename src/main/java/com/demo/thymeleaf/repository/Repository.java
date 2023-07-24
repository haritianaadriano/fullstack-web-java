package com.demo.thymeleaf.repository;

import com.demo.thymeleaf.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@org.springframework.stereotype.Repository
public interface Repository extends JpaRepository <Employee, Integer>, JpaSpecificationExecutor<Employee> {
    @Query(value = "SELECT * FROM employee " +
            "WHERE (:firstname IS NULL OR firstname LIKE CONCAT('%', :firstname, '%')) " +
            "AND (:lastname IS NULL OR lastname LIKE CONCAT('%', :lastname, '%'))",
            nativeQuery = true)
    List<Employee> find_employee_by_name_criteria(
            @Param("firstname") String firstname,
            @Param("lastname") String lastname);

}
