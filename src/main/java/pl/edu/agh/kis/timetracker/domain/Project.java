package pl.edu.agh.kis.timetracker.domain;

import java.util.List;
import java.util.Set;

public class Project {

  private final String name;
  private final Set<Task> tasks;

  public Project(String name, Set<Task> tasks) {
    this.name = name;
    this.tasks = tasks;
  }

  public String getName() {
    return name;
  }

  public Set<Task> getTasks() {
    return tasks;
  }

  @Override
  public int hashCode() {
    return 1;
  }

  @Override
  public boolean equals(Object other) {
    if (other == null) return false;
    if (other == this) return true;
    if (!(other instanceof Project))
      return false;
    Project otherProject = (Project)other;
    if (otherProject.name.equals(this.name))
      return true;
    return false;
  }
}
