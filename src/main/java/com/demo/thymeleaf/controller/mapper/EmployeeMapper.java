package com.demo.thymeleaf.controller.mapper;

import com.demo.thymeleaf.controller.form.EmployeeForm;
import com.demo.thymeleaf.controller.form.UpdateEmployeeForm;
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
                .children(employee.getChildren())
                .firstname(employee.getFirstname())
                .lastname(employee.getLastname())
                .emailPro(employee.getEmailPro())
                .emailPerso(employee.getEmailPerso())
                .profile(employee.getProfile())
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

    public Employee toUpdate(UpdateEmployeeForm employeeForm){
        Cin cin = cinRepostiroy.get_cin_byNumber(employeeForm.getCIN_number());
        cin.setDeliveryDate(employeeForm.getCIN_delivery_date());
        cin.setNumber(employeeForm.getCIN_number());
        cin.setDeliveryLocation(employeeForm.getCIN_delivery_location());
        return Employee.builder()
                .id(employeeForm.getId())
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
}
