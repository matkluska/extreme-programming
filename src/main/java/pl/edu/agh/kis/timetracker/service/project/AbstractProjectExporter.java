package pl.edu.agh.kis.timetracker.service.project;

import pl.edu.agh.kis.timetracker.domain.Project;

public abstract class AbstractProjectExporter {

  public abstract  void exportProject(final Project project);

}
