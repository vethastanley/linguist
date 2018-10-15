package com.softwareag.linguist.repository;

import com.softwareag.linguist.domain.project.Project;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by VST on 11/10/2018.
 */
@Repository
public interface ProjectRepository extends MongoRepository<Project, String> {

    String PROJECT_BY_NAME = "projectByName";

    @Cacheable(cacheNames = PROJECT_BY_NAME)
    Optional<Project> findOneByName(String name);
}
