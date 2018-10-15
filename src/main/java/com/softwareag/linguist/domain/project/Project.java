package com.softwareag.linguist.domain.project;

import com.softwareag.linguist.domain.AbstractAuditingEntity;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.List;

/**
 * Created by VST on 11/10/2018.
 */
@org.springframework.data.mongodb.core.mapping.Document(collection = "project")
public class Project extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private String name;

    private String owner;

    private List<Repository> repositories;

    private List<Language> languages;

    private List<Translator> translators;

    private List<String> developers;

    protected boolean review;

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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
    }

    public List<Language> getLanguages() {
        return languages;
    }

    public void setLanguages(List<Language> languages) {
        this.languages = languages;
    }

    public List<Translator> getTranslators() {
        return translators;
    }

    public void setTranslators(List<Translator> translators) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Project)) return false;

        Project project = (Project) o;

        if (review != project.review) return false;
        if (id != null ? !id.equals(project.id) : project.id != null) return false;
        if (name != null ? !name.equals(project.name) : project.name != null) return false;
        if (owner != null ? !owner.equals(project.owner) : project.owner != null) return false;
        if (repositories != null ? !repositories.equals(project.repositories) : project.repositories != null)
            return false;
        if (languages != null ? !languages.equals(project.languages) : project.languages != null) return false;
        if (translators != null ? !translators.equals(project.translators) : project.translators != null) return false;
        return developers != null ? developers.equals(project.developers) : project.developers == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (repositories != null ? repositories.hashCode() : 0);
        result = 31 * result + (languages != null ? languages.hashCode() : 0);
        result = 31 * result + (translators != null ? translators.hashCode() : 0);
        result = 31 * result + (developers != null ? developers.hashCode() : 0);
        result = 31 * result + (review ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Project{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", owner='" + owner + '\'' +
            ", repositories=" + repositories +
            ", languages=" + languages +
            ", translators=" + translators +
            ", developers=" + developers +
            ", review=" + review +
            '}';
    }
}
