package com.demo.thymeleaf.repository;

import com.demo.thymeleaf.model.Cin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CinRepostiroy extends JpaRepository<Cin, Integer> {
    @Query("SELECT c FROM Cin c WHERE c.number = ?1 ")
    Cin get_cin_byNumber(Double number);
}
