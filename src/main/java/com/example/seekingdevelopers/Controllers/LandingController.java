package com.example.seekingdevelopers.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LandingController {

    @GetMapping("/")
    @ResponseBody
    public String landing(){
        return "Welcome to Seeking Developers";
    }

}
