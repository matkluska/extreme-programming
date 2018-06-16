package pl.edu.agh.kis.timetracker.domain;

import java.util.List;
import java.util.Set;

public class ProjectsConfig {
    Set<Project> projects;

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

}
