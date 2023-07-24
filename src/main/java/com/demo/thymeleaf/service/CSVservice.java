package com.demo.thymeleaf.service;

import com.demo.thymeleaf.model.Employee;
import com.demo.thymeleaf.repository.Repository;
import lombok.AllArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

@Service
@AllArgsConstructor
public class CSVservice {
    private final Repository repository;

    public void writeEmployeeToCsv(Writer writer) {
        List<Employee> employeeList = repository.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.POSTGRESQL_CSV)) {
            csvPrinter.printRecord(
                    "ID",
                    "ref",
                    "firstname",
                    "lastname",
                    "gender",
                    "adress",
                    "job",
                    "csp",
                    "CNAPS",
                    "children",
                    "begin-date",
                    "finish-date",
                    "email-pro",
                    "email-perso",
                    "cin",
                    "birthdate",
                    "phones"
            );
            for(Employee employee : employeeList) {
                csvPrinter.printRecord(
                        employee.getId(),
                        employee.getRef(),
                        employee.getFirstname(),
                        employee.getLastname(),
                        employee.getSexe(),
                        employee.getAdress(),
                        employee.getJob(),
                        employee.getCsp(),
                        employee.getCNAPS(),
                        employee.getChildren(),
                        employee.getBeginDate(),
                        employee.getFinishDate(),
                        employee.getEmailPro(),
                        employee.getEmailPerso(),
                        employee.getCin().getNumber(),
                        employee.getBirthdate(),
                        employee.getPhones()
                );
            }
        }
        catch (IOException ioException) {
            System.out.println(ioException);
        }
    }
}
