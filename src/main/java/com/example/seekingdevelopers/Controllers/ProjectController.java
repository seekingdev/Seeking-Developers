package com.example.seekingdevelopers.Controllers;

import com.example.seekingdevelopers.Repositories.ProjectRepository;
import com.example.seekingdevelopers.Repositories.UserRepository;
import com.example.seekingdevelopers.models.Project;
import com.example.seekingdevelopers.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProjectController {
    final private ProjectRepository projectDao;

    public ProjectController(ProjectRepository projectDao){
        this.projectDao = projectDao;
    }

    @GetMapping("/projects/create")
    public String create(Model model){
        model.addAttribute("project", new Project());
        return "projects/create";
    }
    @PostMapping("/projects/create")
    public String create(@ModelAttribute Project project, Model model){
        User creator = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
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


}
