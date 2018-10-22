package com.softwareag.linguist.web.rest;

import com.softwareag.linguist.domain.project.Project;
import com.softwareag.linguist.repository.ProjectRepository;
import com.softwareag.linguist.security.AuthoritiesConstants;
import com.softwareag.linguist.service.ProjectService;
import com.softwareag.linguist.web.rest.errors.BadRequestAlertException;
import com.softwareag.linguist.web.rest.errors.ProjectAlreadyExistException;
import com.softwareag.linguist.web.rest.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

/**
 * Created by VST on 11/10/2018.
 */
@RestController
@RequestMapping("/api")
public class ProjectResource {

    private final Logger log = LoggerFactory.getLogger(ProjectResource.class);

    private ProjectService service;

    private ProjectRepository repository;

    public ProjectResource(ProjectService service, ProjectRepository repository) {
        this.service = service;
        this.repository = repository;
    }

    @PostMapping("/projects")
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.PM + "\",\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Project> createProject(@Valid @RequestBody Project project) throws URISyntaxException {
        log.debug("About to create Project {}", project);
        if (project.getId() != null) {
            throw new BadRequestAlertException("A new project cannot already have an ID", "projectManagement", "idexists");
        } else if (repository.findOneByName(project.getName()).isPresent()) {
            throw new ProjectAlreadyExistException();
        } else {
            Project createdProject = service.createProject(project);
            return ResponseEntity.created(new URI("/api/projects/" + createdProject.getName())).body(project);
        }
    }

    @PutMapping("/projects")
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.PM + "\",\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Project> updateProject(@Valid @RequestBody Project project) throws URISyntaxException {
        log.debug("About to update the project {}", project);
        Project updatedProject = service.updateProject(project);
        return ResponseEntity.created(new URI("/api/projects/" + updatedProject.getName())).body(project);
    }

    @GetMapping("/projects/{id:.+}")
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.PM + "\",\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Project> getProject(@PathVariable String id) {
        log.debug("Get the project with id {}", id);
        return ResponseUtil.wrapOrNotFound(service.getProject(id));
    }

    @GetMapping("/projects")
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.PM + "\",\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<List<Project>> getAllUsers(Pageable pageable) {
        final Page<Project> page = service.getAllProjects(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/projects");
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @DeleteMapping("/projects/{id:.+}")
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.PM + "\",\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> deleteProject(@PathVariable String id) {
        log.debug("Delete the project with id {}", id);
        service.deleteProject(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/projects/{id:.+}/translate")
    @PreAuthorize("hasAnyRole(\"" + AuthoritiesConstants.PM + "\",\"" + AuthoritiesConstants.ADMIN + "\")")
    public ResponseEntity<Void> triggerTranslation(@PathVariable String id) {
        log.debug("Trigger translation for the project with id {}", id);
        service.triggerTranslation(id);
        return ResponseEntity.ok().build();
    }
}
