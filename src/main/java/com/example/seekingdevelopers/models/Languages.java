package com.example.seekingdevelopers.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "languages")
public class Languages {
    @Id
    @GeneratedValue
    @Column(columnDefinition = "int(11) unsigned")
    private long id;

    @Column(nullable = false)
    private String language;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_languages",
            joinColumns={@JoinColumn(name="languages_id")},
            inverseJoinColumns = {@JoinColumn(name="users_id")}
    )
    private List<User> userLanguages;


}
