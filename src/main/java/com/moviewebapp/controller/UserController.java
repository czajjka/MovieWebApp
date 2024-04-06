package com.moviewebapp.controller;


import com.moviewebapp.model.UserDtls;
import com.moviewebapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/")
    public String home() {
        return "user/home";
    }

    @ModelAttribute
    private void userDetails(Model m, Principal p) {
        String email = p.getName();
        UserDtls user = userRepository.findByEmail(email);
        m.addAttribute("user", user);

    }
}

