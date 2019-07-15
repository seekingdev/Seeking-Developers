package com.example.seekingdevelopers.Controllers;

import com.example.seekingdevelopers.Repositories.UserRepository;
import com.example.seekingdevelopers.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthenticationController {
    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    public AuthenticationController(UserRepository userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/login")
    public String showLoginForm(){
        return "users/login";
    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/signup";
    }
    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user, Errors validation, Model model){
//        if(userDao.findByUsername(user.getUsername()) != null ){
//            validation.rejectValue("username",null, "Username is already in use");
//        }
//        if(validation.hasErrors()){
//            model.addAttribute("errors",validation);
//            return "users/signUp";
//        }
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:login";

    }

}
