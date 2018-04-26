package pl.edu.agh.kis.timetracker.repository;

import pl.edu.agh.kis.timetracker.domain.Project;

public interface ProjectRepository {

    void saveProject(Project project);

    Project getProject(String projectName);
}
