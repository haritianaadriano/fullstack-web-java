package com.demo.thymeleaf.service.utils;

import com.demo.thymeleaf.model.conf.User;
import com.demo.thymeleaf.repository.auth.AuthRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;

    //TODO: implement this method with paramater an HTTPSession to check if the UUID is already there.
    public Boolean isAuthorised(UUID token) {
        if(token != null && authRepository.findById(token).isPresent()) {
            return true;
        }
        return false;
    }

    //TODO: check if the password is true
    public Boolean isPasswordTrue(User user) {
        User user_data = authRepository.findUserByUsernameContainingIgnoreCase(user.getUsername());
        if(!user.getPassword().isEmpty() && user.getPassword().equals(user_data.getPassword())){
            return true;
        }
        return false;
    }

    public void saveNewUser(User user) {
        authRepository.save(user);
    }
}
