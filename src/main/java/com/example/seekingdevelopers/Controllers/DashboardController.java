package com.example.seekingdevelopers.Controllers;

import com.example.seekingdevelopers.Repositories.ProjectRepository;
import com.example.seekingdevelopers.Repositories.UserRepository;
import com.example.seekingdevelopers.models.Project;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class DashboardController {

    private final ProjectRepository projectDao;
    private final UserRepository userDao;

    public DashboardController(ProjectRepository projectDao, UserRepository userDao){
        this.projectDao = projectDao;
        this.userDao = userDao;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model){
        ArrayList<Project> listOfProjects = projectDao.findAllByisCompleteFalseOrderByCreatingDateDesc();
        model.addAttribute("listOfProjects", listOfProjects);
        return "projects/dashboard";
    }

}
