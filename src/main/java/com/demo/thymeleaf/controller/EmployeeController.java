package com.demo.thymeleaf.controller;

import com.demo.thymeleaf.model.Employee;
import com.demo.thymeleaf.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletResponse;

@Controller
@AllArgsConstructor
public class EmployeeController {
  private EmployeeService service;

    @RequestMapping(value = "/employee/save", method = RequestMethod.POST)
    public String saveEmployee(
            @ModelAttribute(name = "employee") Employee newEmployee,
            BindingResult errors,
            Model model,
            HttpServletResponse response){
       model.addAttribute("employees", service.getEmployee());
       service.crUpdateEmployee(newEmployee);
        return "redirect:/employee";
    }


    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String employeeResolver(
            Model model
            ) {
        model.addAttribute("employees", service.getEmployee());
        model.addAttribute("employee", new Employee());
        return "index";
    }
}
