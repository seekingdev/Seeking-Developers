package com.example.seekingdevelopers.models;

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

    public Project(){};

    public Project(String title, String description, Dev_type dev_type, User creator){
        this.title = title;
        this.description = description;
        this.dev_type = dev_type;
        this.creator = creator;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreating_date() {
        return creating_date;
    }

    public void setCreating_date(Date creating_date) {
        this.creating_date = creating_date;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public User getCreator() {
        return creator;
    }

    public void setCreator(User creator) {
        this.creator = creator;
    }

    public Dev_type getDev_type() {
        return dev_type;
    }

    public void setDev_type(Dev_type dev_type) {
        this.dev_type = dev_type;
    }
}
