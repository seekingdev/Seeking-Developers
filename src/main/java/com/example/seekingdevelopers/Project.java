package com.example.seekingdevelopers;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "int(11) unsigned")
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @DateTimeFormat
    @Column(nullable = false)
    private Date creating_date;

    @Column(columnDefinition = "Boolean default false")
    private boolean isComplete;

    @ManyToOne
    @JoinColumn(name="creator_id")
    private User creator;

    @ManyToOne
    @JoinColumn(name="dev_type_id")
    private Dev_type dev_type;


}
