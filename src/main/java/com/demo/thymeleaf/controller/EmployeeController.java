package com.demo.thymeleaf.controller;

import com.demo.thymeleaf.controller.form.EmployeeForm;
import com.demo.thymeleaf.controller.mapper.EmployeeMapper;
import com.demo.thymeleaf.model.Employee;
import com.demo.thymeleaf.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@AllArgsConstructor
public class EmployeeController {
  private EmployeeService service;
  private EmployeeMapper mapper;

    @RequestMapping(value = "/employee/save", method = RequestMethod.POST)
    public String saveEmployee(
            @ModelAttribute(name = "employee") EmployeeForm newEmployee,
            BindingResult errors,
            Model model,
            HttpServletResponse response
    ) {
       model.addAttribute("employees", service.getEmployee());
        //TODO: check if returning a simple employee don't stop the app when inserting new empployee
        service.insertEmployee(newEmployee);
        return "redirect:/employee";
    }

    //RESOLVER ->

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String employeeResolver(
            Model model
            ) {
        model.addAttribute(
                "employees",
                service.getEmployee().stream()
                        .map(mapper::toRest)
        );
        return "index";
    }

    @RequestMapping(value = "/employee/save", method = RequestMethod.GET)
    public String saveEmployeeResolver(Model model) {
        model.addAttribute("employee", new EmployeeForm());
        return "addEmployee";
    }
}
