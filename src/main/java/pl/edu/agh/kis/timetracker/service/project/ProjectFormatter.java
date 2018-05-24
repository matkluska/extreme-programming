package pl.edu.agh.kis.timetracker.service.project;

import pl.edu.agh.kis.timetracker.domain.Project;
import pl.edu.agh.kis.timetracker.service.Formatter;

public class ProjectFormatter implements Formatter<Project> {

  @Override
  public String format(final Project project) {
    if (project == null) {
      throw new IllegalArgumentException("Project must not be null");
    }
    return String.format("Project: %s", project.getName());
  }
}