package com.demo.thymeleaf.controller;

import com.demo.thymeleaf.service.ProfileService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@AllArgsConstructor
public class ProfileController {
    private ProfileService profileService;

    @PutMapping("/upload/profile/{id}")
    public String saveEmployeeProfile(
            Model model,
            @PathVariable(name = "id") int idEmployee,
            @RequestParam(name = "image") MultipartFile file
    ) throws IOException {
        profileService.uploadProfile(file, idEmployee);
        return "redirect:/employee";
    }

    @RequestMapping(value = "/employee/profile/{id}", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getEmployeeProfile(@PathVariable("id") int idEmployee){
        byte[] profileData = profileService.getEmployeeProfile(idEmployee);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);

        return new ResponseEntity<>(profileData, headers, HttpStatus.OK);
    }
}
