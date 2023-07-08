package com.demo.thymeleaf.service;

import com.demo.thymeleaf.model.Profile;
import com.demo.thymeleaf.repository.ProfileRepository;
import com.demo.thymeleaf.repository.Repository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class ProfileService {
    private ProfileRepository repository;
    private Repository employeeRepository;

    public byte[] getEmployeeProfile(int id){
        return repository.findProfileByEmployee_Id(id).getImg();
    }

    public void uploadProfile(MultipartFile file, int idEmployee) throws IOException {
        repository.save(Profile.builder()
                .employee(employeeRepository.findById(idEmployee).get())
                .img(file.getBytes())
                .build());
    }
}
