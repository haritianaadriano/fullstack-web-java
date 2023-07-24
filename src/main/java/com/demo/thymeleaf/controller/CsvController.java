package com.demo.thymeleaf.controller;

import com.demo.thymeleaf.service.CSVservice;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class CsvController {
    private CSVservice csVservice;

    @GetMapping("/csv-employee")
    public void getAllEmployeeToCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.addHeader("Content-Disposition","attachment; filename=\"employees.csv\\\"");
        csVservice.writeEmployeeToCsv(response.getWriter());
    }
}
