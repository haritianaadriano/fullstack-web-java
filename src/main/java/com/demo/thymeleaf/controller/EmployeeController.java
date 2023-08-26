package com.demo.thymeleaf.controller;

import com.demo.thymeleaf.controller.form.EmployeeForm;
import com.demo.thymeleaf.controller.form.UpdateEmployeeForm;
import com.demo.thymeleaf.controller.mapper.EmployeeMapper;
import com.demo.thymeleaf.service.EmployeeService;
import com.demo.thymeleaf.service.PdfService;
import com.demo.thymeleaf.service.utils.AuthService;
import com.lowagie.text.DocumentException;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.UUID;

@Controller
@AllArgsConstructor
public class EmployeeController {
  private final PdfService pdfService;
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
            Model model,
            HttpServletResponse response
    ) {
        try {
            Path file = Paths.get(pdfService.generatedPdf(idEmploye).getAbsolutePath());
            if(Files.exists(file)) {
                response.setContentType("application/pdf");
                response.addHeader(
                        "Content-Disposition",
                        "attachment; filename:"+file.getFileName()
                        );
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
        }
        catch (IOException | DocumentException e) {
            e.printStackTrace();
        }
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
}
