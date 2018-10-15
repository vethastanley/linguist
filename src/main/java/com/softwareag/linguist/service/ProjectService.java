package com.softwareag.linguist.service;

import com.softwareag.linguist.domain.project.Project;
import com.softwareag.linguist.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by VST on 11/10/2018.
 */
@Service
public class ProjectService {

    private ProjectRepository repository;

    public ProjectService(ProjectRepository repository) {
        this.repository = repository;
    }

    public Project createProject(Project project) {
        return repository.insert(project);
    }

    public Project updateProject(Project project) {
        return repository.save(project);
    }

    public Optional<Project> getProject(String id) {
        return repository.findById(id);
    }

    public void deleteProject(String id) {
        repository.deleteById(id);
    }
}
