package com.demo.thymeleaf.controller;

import com.demo.thymeleaf.model.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {
    private List<jakarta.servlet.http.Cookie> employeesInCookie = new ArrayList<>();
    private List<Employee> employees = new ArrayList<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = "/employee/save", method = RequestMethod.POST)
    public String saveEmployee(
            @ModelAttribute("employee") Employee employee,
            BindingResult errors,
            Model model,
            HttpServletResponse response) throws JsonProcessingException {
        jakarta.servlet.http.Cookie newEmployee = new jakarta.servlet.http.Cookie(
                (""+employee.getId()),
                objectMapper.writeValueAsString(employee));
        employees.add(employee);
        response.addCookie(newEmployee);
        employeesInCookie.add(newEmployee);
        return "redirect:/employee";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String employeeResolver(Model model, HttpServlet servlet) {
        model.addAttribute("employees", employees);
        model.addAttribute("employee", new Employee());
        return "index";
    }
}
