package pl.edu.agh.kis.timetracker.domain;

import java.util.List;

public class Project {

  private final String name;
  private final List<Task> tasks;

  public Project(String name, List<Task> tasks) {
    this.name = name;
    this.tasks = tasks;
  }

  public String getName() {
    return name;
  }

  public List<Task> getTasks() {
    return tasks;
  }
}
