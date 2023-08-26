package com.demo.thymeleaf.controller;

import com.demo.thymeleaf.controller.form.EmployeeForm;
import com.demo.thymeleaf.controller.form.UpdateEmployeeForm;
import com.demo.thymeleaf.controller.mapper.EmployeeMapper;
import com.demo.thymeleaf.service.EmployeeService;
import com.demo.thymeleaf.service.utils.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class EmployeeController {
  private final AuthService authService;
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
    @GetMapping("/employee/{id_employee}/raw/pdf")
    public String getOneEmployeePdf(
            @PathVariable(name = "id_employee")int idEmploye,
            Model model
    ) {
        model.addAttribute("one_employee", mapper.toRest(service.getOneEmployee(idEmploye)));
        return "employee_pdf";
    }

    @GetMapping("/employee/{id_employee}")
    public String getOneEmployee(
            @PathVariable(name = "id_employee")int idEmploye,
            Model model
    ) {
        model.addAttribute("one_employee", mapper.toRest(service.getOneEmployee(idEmploye)));
        return "employee";
    }

    @RequestMapping(value = "/employee/update", method = RequestMethod.GET)
    public String updateEmployeeResolver(
            Model model,
            HttpSession session
    ) {
        String token = (String) session.getAttribute("token");
        model.addAttribute("employee", new UpdateEmployeeForm());
        if (Objects.nonNull(token) && authService.isAuthorised(UUID.fromString(token))) {
            return "updateEmployee";
        }
        return "redirect:/auth/login";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String employeeResolver(
            HttpSession session,
            Model model,
            @RequestParam(name = "order", defaultValue = "ASC") String order,
            @RequestParam(name = "firstname", required = false, defaultValue = "") String firstname,
            @RequestParam(name = "lastname", required = false, defaultValue = "") String lastname,
            @RequestParam(name = "job", required = false, defaultValue = "") String job,
            @RequestParam(name = "address", required = false, defaultValue = "") String address,
            @RequestParam(name = "phone", required = false, defaultValue = "") String phone
            ) {
        model.addAttribute(
                "employees",
                service.getEmployee(order, firstname, lastname, job, address, phone).stream()
                        .map(mapper::toRest)
        );
        String token = (String) session.getAttribute("token");
        if (Objects.nonNull(token) && authService.isAuthorised(UUID.fromString(token))) {
            return "index";
        }
        return "redirect:/auth/login";
    }

    @RequestMapping(value = "/employee/save", method = RequestMethod.GET)
    public String saveEmployeeResolver(
            Model model,
            HttpSession session
    ) {
        model.addAttribute("employee", new EmployeeForm());
        String token = (String) session.getAttribute("token");
        if (Objects.nonNull(token) && authService.isAuthorised(UUID.fromString(token))) {
            return "addEmployee";
        }
        return "redirect:/auth/login";
    }
}
