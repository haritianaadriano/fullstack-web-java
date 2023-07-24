package com.demo.thymeleaf.controller;

import com.demo.thymeleaf.controller.form.EmployeeForm;
import com.demo.thymeleaf.controller.form.UpdateEmployeeForm;
import com.demo.thymeleaf.controller.mapper.EmployeeMapper;
import com.demo.thymeleaf.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
        service.insertEmployee(newEmployee);
        return "redirect:/employee";
    }

    @RequestMapping(value = "/employee/update", method = RequestMethod.POST)
    public String updateEmployee(
            @ModelAttribute(name = "employee") UpdateEmployeeForm newEmployee,
            BindingResult errors,
            Model model,
            HttpServletResponse response
    ) {
        service.updateEmployee(newEmployee, newEmployee.getId());
        return "redirect:/employee";
    }

    //RESOLVER ->

    @RequestMapping(value = "/employee/update", method = RequestMethod.GET)
    public String updateEmployeeResolver(
            Model model
    ) {
        model.addAttribute("employee", new UpdateEmployeeForm());
        return "updateEmployee";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String employeeResolver(
            Model model,
            @RequestParam(name = "order", defaultValue = "ASC") String order,
            @RequestParam(name = "firstname", required = false, defaultValue = "") String firstname,
            @RequestParam(name = "lastname", required = false, defaultValue = "") String lastname,
            @RequestParam(name = "job", required = false, defaultValue = "") String job,
            @RequestParam(name = "address", required = false, defaultValue = "") String address
            ) {
        model.addAttribute(
                "employees",
                service.getEmployee(order, firstname, lastname, job, address).stream()
                        .map(mapper::toRest)
        );
        return "index";
    }

    @GetMapping("/csv-employee")
    public String exportCSV() {
        return "employeeCSV";
    }

    @RequestMapping(value = "/employee/save", method = RequestMethod.GET)
    public String saveEmployeeResolver(Model model) {
        model.addAttribute("employee", new EmployeeForm());
        return "addEmployee";
    }
}
