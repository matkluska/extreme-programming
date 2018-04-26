package pl.edu.agh.kis.timetracker.service;

import pl.edu.agh.kis.timetracker.domain.Project;

public class ProjectFormatter implements Formatter<Project> {

  @Override
  public String format(final Project project) {
    if (project == null) {
      throw new IllegalArgumentException("Project must not be null");
    }
    return String.format("Project: %s", project.getName());
  }
}