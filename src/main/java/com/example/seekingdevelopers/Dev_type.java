package com.example.seekingdevelopers;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "dev_type")
public class Dev_type {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "int(11) unsigned")
    private long id;

    @Column(nullable = false)
    private String title;

    @OneToMany(mappedBy = "dev_type")
    private List<Project> projectsList;

    @OneToMany(mappedBy = "dev_type")
    private List<User> userListList;

}
