package com.demo.thymeleaf.service;

import com.demo.thymeleaf.repository.PhonenumberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PhonenumberService {
    private PhonenumberRepository repository;


}
