package com.example.seekingdevelopers.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SDHomepageController {
    @GetMapping("/home")
    public String returnHomePage() {
        return "home";
    }
}