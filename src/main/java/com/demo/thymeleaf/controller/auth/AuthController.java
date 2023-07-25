package com.demo.thymeleaf.controller.auth;

import com.demo.thymeleaf.model.conf.User;
import com.demo.thymeleaf.repository.auth.AuthRepository;
import com.demo.thymeleaf.service.utils.AuthService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

@Controller
@AllArgsConstructor
public class AuthController {
    private final AuthRepository authRepository;
    private final AuthService authService;

    @PostMapping("/auth/login")
    public String postLogin(
            @ModelAttribute User user,
            HttpSession session
    ) {
        if (authService.isPasswordTrue(user)) {
            User user_data = authRepository.findUserByUsernameContainingIgnoreCase(user.getUsername());
            String uuid = user_data.getId().toString();
            session.setAttribute("token", uuid);
            return "redirect:/employee";
        }
        return "redirect:/auth/login";
    }

    @PostMapping("/auth/signup")
    public String postSignup(@ModelAttribute User user) {
        authService.saveNewUser(user);
        return "redirect:/auth/login";
    }

    //RESOLVER ->
    @RequestMapping(value = "/auth/login", method = RequestMethod.GET)
    public String login(
            Model model
    ){
        model.addAttribute("user", new User());
        return "login";
    }

    @RequestMapping(value = "/auth/signup", method = RequestMethod.GET)
    public String signup(
            Model model
    ){
        model.addAttribute("user", new User());
        return "signup";
    }
}
