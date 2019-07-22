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
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ProjectController {
    final private ProjectRepository projectDao;
    final private Dev_typeRepository dev_typeDao;
    private final EmailService emailService;
    private final UserRepository userDao;

    public ProjectController(ProjectRepository projectDao, Dev_typeRepository dev_typeDao, EmailService emailService, UserRepository userDao){
        this.projectDao = projectDao;
        this.dev_typeDao = dev_typeDao;
        this.emailService = emailService;
        this.userDao = userDao;
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
        List<User> contributors =  singleProject.getContributors();
        model.addAttribute("contributors", contributors);
        model.addAttribute("project", singleProject);
        return "projects/single-project";
    }

    @PostMapping("/projects/{id}/single-project")
    public String contribute(@PathVariable long id, @RequestParam(name = "contributor") String contributor){
        User userContributor = userDao.findByUsername(contributor);
        Project singleProject = projectDao.findDistinctById(id);
        List<Project> projects = new ArrayList<>();
        projects.add(singleProject);
        userContributor.setUserProjects(projects);
        userDao.save(userContributor);
        emailService.prepareAndSend(singleProject, "Contributor Applying.", "Someone wants to help you.");
        return "redirect:/dashboard";
    }

    @GetMapping("/projects/{id}/single-project/edit")
    public String editProject(@PathVariable long id, Model model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Project editProject = projectDao.findDistinctById(id);
        if(user.getId() != editProject.getCreator().getId()){
            return "redirect:/dashboard";
        }
        model.addAttribute("project", editProject);
        return "projects/edit";
    }

    @PostMapping("/projects/{id}/single-project/edit")
    public String editProject(@ModelAttribute Project project, @RequestParam(name = "isComplete") boolean isComplete, @RequestParam(name = "contributor") String contributor) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(user.getId() != project.getCreator().getId()){
            return "redirect:/dashboard";
        }
        project.setComplete(isComplete);
        projectDao.save(project);
        return "redirect:/dashboard";
    }

}

