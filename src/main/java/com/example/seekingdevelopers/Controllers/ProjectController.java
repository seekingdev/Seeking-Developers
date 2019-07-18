package com.example.seekingdevelopers.Controllers;

import com.example.seekingdevelopers.Repositories.Dev_typeRepository;
import com.example.seekingdevelopers.Repositories.ProjectRepository;
import com.example.seekingdevelopers.Repositories.UserRepository;
import com.example.seekingdevelopers.models.Dev_type;
import com.example.seekingdevelopers.models.Project;
import com.example.seekingdevelopers.models.User;
import com.example.seekingdevelopers.Services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProjectController {
    final private ProjectRepository projectDao;
    final private Dev_typeRepository dev_typeDao;
    private final EmailService emailService;

    public ProjectController(ProjectRepository projectDao, Dev_typeRepository dev_typeDao, EmailService emailService){
        this.projectDao = projectDao;
        this.dev_typeDao = dev_typeDao;
        this.emailService = emailService;
    }

    @GetMapping("/projects/create")
    public String create(Model model){
        model.addAttribute("project", new Project());
        return "projects/create";
    }
    @PostMapping("/projects/create")
    public String create(@ModelAttribute Project project, Model model, @RequestParam(name = "helpNeeded") Long id){
        User creator = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Dev_type dev_type = dev_typeDao.findOne(id);
        project.setDev_type(dev_type);
        project.setCreator(creator);
        projectDao.save(project);
        return "redirect:/dashboard";
    }

    @GetMapping("/projects/{id}/single-project")
    public String singleProject(@PathVariable long id, Model model){
        Project singleProject = projectDao.findDistinctById(id);
        model.addAttribute("project", singleProject);
        return "projects/single-project";
    }

    @PostMapping("/projects/{id}/single-project")
    public String contribute(Project project){
        emailService.prepareAndSend(project, "Post edited", "Your post was edited");
        return "projects/single-project";
    }


}
