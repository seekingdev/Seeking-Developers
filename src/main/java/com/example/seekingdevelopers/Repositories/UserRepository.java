package com.example.seekingdevelopers.Repositories;

import com.example.seekingdevelopers.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);

    List<User> findAll();

    List<User> findAllByUsernameContaining(String serach);
    ;
}