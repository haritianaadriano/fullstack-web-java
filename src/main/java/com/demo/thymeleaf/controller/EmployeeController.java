package com.demo.thymeleaf.controller;

import com.demo.thymeleaf.model.Employee;
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
    private List<Employee> employees = new ArrayList<>();

    @RequestMapping(value = "/employee/save", method = RequestMethod.POST)
    public String saveEmployee(@ModelAttribute("employee") Employee employee, BindingResult errors, Model model) {
        employees.add(employee);
        return "redirect:/employee";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String employeeResolver(Model model) {
        model.addAttribute("employees", employees);
        model.addAttribute("employee", new Employee());
        return "index";
    }
}
