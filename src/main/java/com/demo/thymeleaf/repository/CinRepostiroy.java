package com.demo.thymeleaf.repository;

import com.demo.thymeleaf.model.Cin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CinRepostiroy extends JpaRepository<Cin, Integer> {
    @Query(value = "SELECT * FROM cin WHERE number = ?1 ", nativeQuery = true)
    Cin get_cin_byNumber(Integer number);
}
