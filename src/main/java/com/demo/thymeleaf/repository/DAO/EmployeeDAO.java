package com.demo.thymeleaf.repository.DAO;

import com.demo.thymeleaf.model.Employee;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {
    public static Specification<Employee> filterEmployee(String firstName, String lastName, String adress, String job) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(!firstName.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("firstname")), "%" + firstName.toLowerCase() + "%"));
            }

            if(!lastName.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("lastname")), "%" + lastName.toLowerCase() + "%"));
            }

            if(!adress.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("adress")), "%" + adress.toLowerCase() + "%"));
            }

            if(!job.isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("job")), "%" + job.toLowerCase() + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
