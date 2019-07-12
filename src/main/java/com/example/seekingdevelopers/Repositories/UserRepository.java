package com.example.seekingdevelopers.Repositories;

import com.example.seekingdevelopers.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

}