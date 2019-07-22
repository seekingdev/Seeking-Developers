package com.example.seekingdevelopers.Controllers;

import com.example.seekingdevelopers.Repositories.ProjectRepository;
import com.example.seekingdevelopers.Repositories.UserRepository;
import com.example.seekingdevelopers.models.Language;
import com.example.seekingdevelopers.models.Project;
import com.example.seekingdevelopers.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProfileController {

    private final ProjectRepository projectDao;
    private final UserRepository userDao;

    public ProfileController(ProjectRepository projectDao, UserRepository userDao){
        this.projectDao = projectDao;
        this.userDao = userDao;
    }
    @GetMapping("/profile")
    public String loggedInProfile(Model model){
        User loggedinUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = userDao.findOne(loggedinUser.getId());
        List<User> favorite_users = currentUser.getFavorite_users();
        model.addAttribute("user",currentUser);
        model.addAttribute("favorite_users", favorite_users);
        List<Language> langs = new ArrayList<>();
        langs = currentUser.getLanguage();
        model.addAttribute("langs",langs);
        Project lastProject = projectDao.findDistinctTopByCreatorOrderByCreatingDateDesc(loggedinUser);
        model.addAttribute("lastProject",lastProject);
        List<Project> completedProjectList = projectDao.findAllByCreatorAndIsCompleteTrue(loggedinUser);
       model.addAttribute("completedProjects",completedProjectList);
        ArrayList<Project> projects = projectDao.findAllByCreatorOrderByCreatingDate(loggedinUser);
        model.addAttribute("projects", projects);


        return "users/profile";
    }


}
