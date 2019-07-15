package com.example.seekingdevelopers.Controllers;

import com.example.seekingdevelopers.Repositories.UserRepository;
import com.example.seekingdevelopers.models.User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class UserController {

    private UserRepository userDao;


    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/signup";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user, Model model){
        userDao.save(user);
        return "redirect:login";
    }

}
