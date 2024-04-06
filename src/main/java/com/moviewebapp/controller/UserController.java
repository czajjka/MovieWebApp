package com.example.moviewebapp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/")
    public String home() {
        return "user/home";
    }
}


//    @Autowired
//    private UserRepository userRepository;

//    @ModelAttribute
//    private void userDetails(Model m, Principal p) {
//        String email = p.getName();
//        UserDtls user = userRepository.findByEmail(email);
//        m.addAttribute("user", user);
//
//    }


