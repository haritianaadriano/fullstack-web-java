package com.demo.thymeleaf.controller.mapper;

import com.demo.thymeleaf.controller.form.EmployeeForm;
import com.demo.thymeleaf.model.Cin;
import com.demo.thymeleaf.model.Employee;
import com.demo.thymeleaf.repository.CinRepostiroy;
import com.demo.thymeleaf.repository.PhonenumberRepository;
import com.demo.thymeleaf.repository.Repository;
import com.demo.thymeleaf.service.utils.EmployeeUtils;
import com.demo.thymeleaf.service.utils.PhonenumberUtils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class EmployeeMapper {
    private CinRepostiroy cinRepostiroy;
    private Repository repository;
    private PhonenumberUtils phonenumberUtils;

    public Employee toRest(Employee employee) {
        return Employee.builder()
                .id(employee.getId())
                .ref(employee.getRef())
                .sexe(employee.getSexe())
                .job(employee.getJob())
                .finishDate(employee.getFinishDate())
                .beginDate(employee.getBeginDate())
                .CNAPS(employee.getCNAPS())
                .adress(employee.getAdress())
                .csp(employee.getCsp())
                .cin(employee.getCin())
                .birthdate(employee.getBirthdate())
                .phones(phonenumberUtils.employeePhones(employee.getId()))
                .build();
    }

    public Employee toDomain(EmployeeForm employeeForm){
        Cin cin = Cin.builder()
                .deliveryDate(employeeForm.getCIN_delivery_date())
                .deliveryLocation(employeeForm.getCIN_delivery_location())
                .number(employeeForm.getCIN_number())
                .build();
        return Employee.builder()
                .lastname(employeeForm.getLastname())
                .firstname(employeeForm.getFirstname())
                .birthdate(employeeForm.getBirthdate())
                .adress(employeeForm.getAdress())
                .CNAPS(employeeForm.getCNAPS())
                .csp(employeeForm.getCsp())
                .cin(cinRepostiroy.save(cin))
                .children(employeeForm.getChildren())
                .emailPerso(employeeForm.getEmailPerso())
                .emailPro(employeeForm.getEmailPro())
                .beginDate(employeeForm.getBegindate())
                .ref(EmployeeUtils.mapRefId(EmployeeUtils.findLastEmployee(repository)))
                .job(employeeForm.getJob())
                .sexe(employeeForm.getGender())
                .build();
    }

    public Employee toDomain(Employee employee){
        Cin cin = Cin.builder()
                .id(employee.getCin().getId())
                .number(employee.getCin().getNumber())
                .deliveryDate(employee.getCin().getDeliveryDate())
                .deliveryLocation(employee.getCin().getDeliveryLocation())
                .build();
        return Employee.builder()
                .id(employee.getId())
                .lastname(employee.getLastname())
                .firstname(employee.getFirstname())
                .birthdate(employee.getBirthdate())
                .adress(employee.getAdress())
                .CNAPS(employee.getCNAPS())
                .csp(employee.getCsp())
                .cin(cinRepostiroy.save(cin))
                .children(employee.getChildren())
                .emailPerso(employee.getEmailPerso())
                .emailPro(employee.getEmailPro())
                .beginDate(employee.getBeginDate())
                .ref(EmployeeUtils.mapRefId(EmployeeUtils.findLastEmployee(repository)))
                .job(employee.getJob())
                .sexe(employee.getSexe())
                .build();
    }
}
