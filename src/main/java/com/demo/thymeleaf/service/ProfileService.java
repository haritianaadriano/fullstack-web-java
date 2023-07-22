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
                        .emailPerso(employeeRepository.findById(idEmployee)
                                .get()
                                .getEmailPerso())
                        .emailPro(employeeRepository.findById(idEmployee)
                                .get()
                                .getEmailPro())
                        .CNAPS(employeeRepository.findById(idEmployee)
                                .get()
                                .getCNAPS())
                        .cin(employeeRepository.findById(idEmployee)
                                .get()
                                .getCin())
                        .csp(employeeRepository.findById(idEmployee)
                                .get()
                                .getCsp())
                        .job(employeeRepository.findById(idEmployee)
                                .get()
                                .getJob())
                        .phones(employeeRepository.findById(idEmployee)
                                .get()
                                .getPhones())
                        .adress(employeeRepository.findById(idEmployee)
                                .get()
                                .getAdress())
                        .sexe(employeeRepository.findById(idEmployee)
                                .get()
                                .getSexe())
                        .children(employeeRepository.findById(idEmployee)
                                .get()
                                .getChildren())
                        .beginDate(employeeRepository.findById(idEmployee)
                                .get()
                                .getBeginDate())
                        .finishDate(employeeRepository.findById(idEmployee)
                                .get()
                                .getFinishDate())
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
