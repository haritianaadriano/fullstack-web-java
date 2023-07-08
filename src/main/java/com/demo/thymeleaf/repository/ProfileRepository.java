package com.demo.thymeleaf.repository;

import com.demo.thymeleaf.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends JpaRepository <Profile, Integer>{
    public Profile findProfileByEmployee_Id(int employeeId);
}
