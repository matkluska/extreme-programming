package pl.edu.agh.kis.timetracker.service;

import pl.edu.agh.kis.timetracker.domain.Project;
import pl.edu.agh.kis.timetracker.domain.Task;
import pl.edu.agh.kis.timetracker.repository.ProjectRepository;
import pl.edu.agh.kis.timetracker.repository.impl.ProjectRepositoryImpl;
import pl.edu.agh.kis.timetracker.service.project.ProjectFormatter;

public class ReportMessagePrinter implements MessagePrinter {

  private ProjectRepository repository = new ProjectRepositoryImpl();
  private Formatter<Project> projectFormatter = new ProjectFormatter();
  private Formatter<Task> taskFormatter = new TaskFormatter();

  @Override
  public void print() {
    repository.findAll().forEach(this::printProject);
  }

  private void printProject(Project p) {
    System.out.println(projectFormatter.format(p));
    p.getTasks().forEach(this::printTask);
  }

  private void printTask(Task t) {
    System.out.println(taskFormatter.format(t));
  }
}
