package com.example.seekingdevelopers.Repositories;

import com.example.seekingdevelopers.Repositories.;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
    User findByUsername(String username);
    User findByEmail(String email);
}