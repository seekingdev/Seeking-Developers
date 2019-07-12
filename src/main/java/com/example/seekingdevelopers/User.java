package com.example.seekingdevelopers;


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
}
