package com.softwareag.linguist.domain.project;

import com.softwareag.linguist.domain.AbstractAuditingEntity;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Created by VST on 11/10/2018.
 */
@org.springframework.data.mongodb.core.mapping.Document(collection = "project")
public class Project extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String name;

    private RepositoryType repoType;

    private String url;

    private String username;

    private String password;

    private String pattern;

    private Language source;

    private List<Language> destination;

    private List<String> translators;

    private List<String> developers;

    private boolean review;

    private boolean activated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RepositoryType getRepoType() {
        return repoType;
    }

    public void setRepoType(RepositoryType repoType) {
        this.repoType = repoType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    public Language getSource() {
        return source;
    }

    public void setSource(Language source) {
        this.source = source;
    }

    public List<Language> getDestination() {
        return destination;
    }

    public void setDestination(List<Language> destination) {
        this.destination = destination;
    }

    public List<String> getTranslators() {
        return translators;
    }

    public void setTranslators(List<String> translators) {
        this.translators = translators;
    }

    public List<String> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<String> developers) {
        this.developers = developers;
    }

    public boolean isReview() {
        return review;
    }

    public void setReview(boolean review) {
        this.review = review;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;
        Project project = (Project) o;
        return review == project.review &&
            activated == project.activated &&
            Objects.equals(id, project.id) &&
            Objects.equals(name, project.name) &&
            repoType == project.repoType &&
            Objects.equals(url, project.url) &&
            Objects.equals(username, project.username) &&
            Objects.equals(password, project.password) &&
            Objects.equals(pattern, project.pattern) &&
            source == project.source &&
            Objects.equals(destination, project.destination) &&
            Objects.equals(translators, project.translators) &&
            Objects.equals(developers, project.developers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, repoType, url, username, password, pattern, source, destination, translators, developers, review, activated);
    }

    @Override
    public String toString() {
        return "Project{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", repoType=" + repoType +
            ", url='" + url + '\'' +
            ", username='" + username + '\'' +
            ", password='" + password + '\'' +
            ", pattern='" + pattern + '\'' +
            ", source=" + source +
            ", destination=" + destination +
            ", translators=" + translators +
            ", developers=" + developers +
            ", review=" + review +
            ", activated=" + activated +
            '}';
    }
}
