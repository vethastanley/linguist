package com.softwareag.linguist.service;

import com.softwareag.linguist.domain.project.Project;
import com.softwareag.linguist.repository.ProjectRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by VST on 11/10/2018.
 */
@Service
public class ProjectService {

    private ProjectRepository repository;

    private VirtualTranslator translator;

    public ProjectService(ProjectRepository repository, VirtualTranslator translator) {
        this.repository = repository;
        this.translator = translator;
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

    public Page<Project> getAllProjects(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public void deleteProject(String id) {
        repository.deleteById(id);
    }

    public void triggerTranslation(String id) {
        try {
            translator.triggerTranslation(getProject(id).get());
        } catch (Exception e) {
            System.out.println("Failed to trigger translation");
        }
    }
}
