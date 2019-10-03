package com.example.seekingdevelopers.Controllers;

import com.example.seekingdevelopers.Repositories.ProjectRepository;
import com.example.seekingdevelopers.Repositories.UserRepository;
import com.example.seekingdevelopers.Services.EmailService;
import com.example.seekingdevelopers.models.Project;
import com.example.seekingdevelopers.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class DashboardController {

    private final ProjectRepository projectDao;
    private final UserRepository userDao;
    private final EmailService emailService;


    public DashboardController(ProjectRepository projectDao, UserRepository userDao, EmailService emailService){
        this.projectDao = projectDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        ArrayList<Project> listOfProjects = projectDao.findAllByisCompleteFalseOrderByCreatingDateDesc();
        model.addAttribute("listOfProjects", listOfProjects);
        return "projects/dashboard-foundation";
    }

    @PostMapping("/dashboard")
    public String dashboard(Model model, @RequestParam(name = "joinProject") Long joinId){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User loggedInUser = userDao.findOne(user.getId());
        Project project = projectDao.findDistinctById(joinId);
        ArrayList<Project> listOfProjects = projectDao.findAllByisCompleteFalseOrderByCreatingDateDesc();
        model.addAttribute("listOfProjects", listOfProjects);
        emailService.prepareAndSend(project, loggedInUser.getUsername() + " is applying.", "They want to help you on " + project.getTitle() + "they are a " + loggedInUser.getDev_type().getTitle() + " developer. You can email them at " + loggedInUser.getEmail());
        return "projects/dashboard";
    }

}
