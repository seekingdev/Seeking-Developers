package com.example.seekingdevelopers.Repositories;

import com.example.seekingdevelopers.models.language;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageRepository extends CrudRepository<language, Long> {

}