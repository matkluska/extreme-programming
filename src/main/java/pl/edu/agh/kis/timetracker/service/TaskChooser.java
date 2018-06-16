package pl.edu.agh.kis.timetracker.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import pl.edu.agh.kis.timetracker.domain.Project;
import pl.edu.agh.kis.timetracker.domain.Task;

public class TaskChooser {

  private final Set<Project> projects;

  public TaskChooser(Set<Project> projects) {
    this.projects = projects;
  }

  Optional<Task> getTaskWithNumber(int taskNumber) {
    int counter = 0;
    for (Project project : projects) {
      for (Task task : project.getTasks()) {
        counter++;
        if (taskNumber == counter) {
          return Optional.of(task);
        }
      }
    }
    return Optional.empty();
  }
}