package pl.edu.agh.kis.timetracker.service.project;

import pl.edu.agh.kis.timetracker.domain.Project;
import pl.edu.agh.kis.timetracker.repository.ProjectRepository;

public class ProjectExporterImpl extends AbstractProjectExporter {

  private ProjectRepository projectRepository;

  public ProjectExporterImpl(
      ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  @Override
  public void exportProject(Project project) {
    projectRepository.saveProject(project);
  }
}
