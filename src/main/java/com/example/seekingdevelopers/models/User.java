package com.example.seekingdevelopers.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "int(11) unsigned")
    private long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(columnDefinition = "Boolean default false")
    private boolean isAdmin;

    @Column
    private String photo;

    @Column
    private String github;

    @Column
    private String linkedin;

    @OneToMany(mappedBy = "creator")
    private List<Project> projects;

    @ManyToOne
    @JoinColumn(name="dev_type_id")
    private Dev_type dev_type;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "favorite_users",
            joinColumns={@JoinColumn(name = "picked_user_id")},
            inverseJoinColumns = {@JoinColumn(name = "picking_user_id")}
    )
    private List<User> favorite_users;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_projects",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="project_id")}
    )
    private List<Project> userProjects;


    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }


    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public User(){};

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }
}
