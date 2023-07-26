package com.demo.thymeleaf.service;

import com.demo.thymeleaf.controller.form.EmployeeForm;
import com.demo.thymeleaf.controller.form.UpdateEmployeeForm;
import com.demo.thymeleaf.controller.mapper.EmployeeMapper;
import com.demo.thymeleaf.model.Cin;
import com.demo.thymeleaf.model.Employee;
import com.demo.thymeleaf.model.Phonenumber;
import com.demo.thymeleaf.repository.CinRepostiroy;
import com.demo.thymeleaf.repository.DAO.EmployeeDAO;
import com.demo.thymeleaf.repository.PhonenumberRepository;
import com.demo.thymeleaf.repository.Repository;
import com.demo.thymeleaf.service.utils.PhonenumberUtils;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final Repository repository;
    private EmployeeMapper mapper;
    private final PhonenumberRepository phonenumberRepository;
    private final CinRepostiroy cinRepostiroy;
    private PhonenumberUtils phonenumberUtils;

    @Transactional
    public List<Employee> getEmployee(String order, String firstname, String lastname, String job, String adress, String phone){
        Sort sort = Sort.by(Sort.Direction.fromString(order), "beginDate");
        Specification<Employee> spec = EmployeeDAO.filterEmployee(adress, job, phone);
        if(firstname.isEmpty() && lastname.isEmpty()) {
            return repository.findAll(spec, sort);
        }
        return repository.find_employee_by_name_criteria(firstname, lastname);
    }

    public Employee insertEmployee(EmployeeForm toCreate) {
        Employee employee = repository.save(mapper.toDomain(toCreate));
        phonenumberRepository.saveAll(phonenumberUtils.checkPhonenumber(employee.getPhones(), employee.getId()));
        return employee;
    }

    public Employee updateEmployee(UpdateEmployeeForm toUpdate, int idEmployee) {
        Cin cin = cinRepostiroy.get_cin_byNumber(toUpdate.getCIN_number());
        cin.setDeliveryDate(toUpdate.getCIN_delivery_date());
        cin.setNumber(toUpdate.getCIN_number());
        cin.setDeliveryLocation(toUpdate.getCIN_delivery_location());
        cinRepostiroy.save(cin);

        Employee employee = repository.findById(idEmployee).get();
        employee.setAdress(toUpdate.getAdress());
        employee.setBeginDate(toUpdate.getBegindate());
        employee.setCin(cin);
        employee.setFinishDate(toUpdate.getFinishdate());
        employee.setBirthdate(toUpdate.getBirthdate());
        employee.setCsp(toUpdate.getCsp());
        employee.setChildren(toUpdate.getChildren());
        employee.setCNAPS(toUpdate.getCNAPS());
        employee.setEmailPerso(toUpdate.getEmailPerso());
        employee.setEmailPerso(toUpdate.getEmailPerso());
        employee.setFirstname(toUpdate.getFirstname());
        employee.setLastname(toUpdate.getLastname());

        return repository.save(employee);
    }
}
