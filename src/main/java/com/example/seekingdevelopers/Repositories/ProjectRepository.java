package com.example.seekingdevelopers.Repositories;

import com.example.seekingdevelopers.models.Project;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

}