package com.demo.thymeleaf.service;

import com.demo.thymeleaf.model.Employee;
import com.demo.thymeleaf.model.Profile;
import com.demo.thymeleaf.repository.ProfileRepository;
import com.demo.thymeleaf.repository.Repository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor
public class ProfileService {
    private ProfileRepository repository;
    private Repository employeeRepository;

    @Transactional
    public byte[] getEmployeeProfile(int id){
        return repository.findProfileByEmployee_Id(id).getImg();
    }

    public void uploadProfile(MultipartFile file, int idEmployee) throws IOException {
        employeeRepository.save(Employee.builder()
                        .id(idEmployee)
                        .lastname(employeeRepository.findById(idEmployee)
                                .get()
                                .getLastname())
                        .firstname(employeeRepository.findById(idEmployee)
                                .get()
                                .getFirstname())
                        .birthdate(employeeRepository.findById(idEmployee)
                                .get()
                                .getBirthdate())
                        .ref(employeeRepository.findById(idEmployee)
                                .get()
                                .getRef())
                        .profile(repository.save(Profile.builder()
                                        .img(file.getBytes())
                                        .employee(employeeRepository.findById(idEmployee).get())
                                .build()))
                .build());
    }
}
