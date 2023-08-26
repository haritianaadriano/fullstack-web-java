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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Objects;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class EmployeeViewResolver {
    private final EmployeeService employeeService;
    private final EmployeeMapper mapper;
    private final AuthService authService;

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
                employeeService.getEmployee(order, firstname, lastname, job, address, phone).stream()
                        .map(mapper::toRest)
        );
        String token = (String) session.getAttribute("token");
        if (Objects.nonNull(token) && authService.isAuthorised(UUID.fromString(token))) {
            return "index";
        }
        return "redirect:/auth/login";
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
}
