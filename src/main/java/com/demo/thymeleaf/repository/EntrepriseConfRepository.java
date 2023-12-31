package com.demo.thymeleaf.repository;

import com.demo.thymeleaf.model.conf.EntrepriseConf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrepriseConfRepository extends JpaRepository<EntrepriseConf, Integer> {
}
