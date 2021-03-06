package pl.edu.agh.kis.timetracker.repository;

import pl.edu.agh.kis.timetracker.domain.Project;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ProjectRepository {

    void saveProject(Project project);

    void saveAll(Set<Project> projects);

    Optional<Project> getProject(String projectName);

    Set<Project> findAll();

    Set<Project> removeAll();
}
