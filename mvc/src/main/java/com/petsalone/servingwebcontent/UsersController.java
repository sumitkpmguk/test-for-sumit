package com.petsalone.servingwebcontent;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.petsalone.core.*;
import com.petsalone.servingwebcontent.models.*;
import java.util.*;

import org.springframework.validation.BindingResult;
import javax.validation.Valid;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class UsersController implements WebMvcConfigurer {

    Logger logger = LoggerFactory.getLogger(UsersController.class);

    @GetMapping("/users/login")
    public String showLoginForm(LoginModel loginModel, Model model, String error, String logout) {

        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "users/login";
    }
}


