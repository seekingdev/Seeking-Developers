package com.example.seekingdevelopers.Controllers;

import com.example.seekingdevelopers.Repositories.UserRepository;
import com.example.seekingdevelopers.models.Project;
import com.example.seekingdevelopers.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProjectController {


    @GetMapping("/projects/create")
    public String create(Model model){
        model.addAttribute("project", new Project());
        return "projects/create";
    }
    @PostMapping("/projects/create")
    public String create(@ModelAttribute Project project, Model model){
        User creator = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        project.setCreator(creator);
        return "projects/create";
    }

}
