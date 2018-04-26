package pl.edu.agh.kis.timetracker.repository;

import pl.edu.agh.kis.timetracker.domain.Project;

import java.util.Optional;

public interface ProjectRepository {

    void saveProject(Project project);

    Optional<Project> getProject(String projectName);
}