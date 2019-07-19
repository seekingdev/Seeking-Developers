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
    public String contribute(@PathVariable long id){
        Project singleProject = projectDao.findDistinctById(id);
        emailService.prepareAndSend(singleProject, "Contributor Applying.", "Someone wants to help you.");
        return "redirect:/dashboard";
    }

    @GetMapping("/projects/{id}/single-project/edit")
    public String editProject(@PathVariable long id, Model model){
        Project editProject = projectDao.findDistinctById(id);
        model.addAttribute("project", editProject);
        return "projects/edit";
    }

    @PostMapping("/projects/{id}/single-project/edit")
    public String editProject(HttpServletRequest request, @PathVariable long id, Project project) {
//        User creator = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Dev_type dev_type = dev_typeDao.findOne(id);

        this.projectDao.findDistinctById(id);
        request.getRequestURI();
        request.getParameter(project.getTitle());
        request.getParameter(project.getDescription());
        request.getParameter(project.getDev_type().toString());
//        project.setComplete(false);
        projectDao.save(project);
        return "/edit";
    }
//
//    @GetMapping("/projects/{id}/single-edit/edit")
//    public ModelAndView editProjectView(@PathVariable long projectId){
//
//        Project project = this.projectDao.findDistinctById(projectId);
//        ModelAndView modelAndView = new ModelAndView();
//
//        modelAndView.setViewName("edit");
//        modelAndView.addObject("project", project);
//
//        return modelAndView;
//    }

//    @PostMapping("/projects/single-project/edit/{projectId}")
//    public ModelAndView editProject(HttpServletRequest request, @PathVariable Long projectId, Project projectView,  BindingResult bindingResult){
//
//        Project project = this.projectDao.findDistinctById(projectId);
//
//        ModelAndView modelAndView = new ModelAndView();
////        modelAndView.setViewName("edit");
////        modelAndView.addObject("project", projectView);
//
//        project.getTitle();
//        project.getDescription();
//        project.getDev_type();
//        project.isComplete();
//        project.getCreator();
//        projectDao.save(project);
//
////       Normalizer.Form.bind(request, projectView);
//
//       this.projectDao.findDistinctById(projectId);
//
//       modelAndView.setViewName("redirect: user/projects");
//
//        return modelAndView;
//    }

}

