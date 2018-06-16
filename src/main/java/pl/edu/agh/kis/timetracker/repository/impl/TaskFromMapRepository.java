package pl.edu.agh.kis.timetracker.repository.impl;

import java.util.*;

import pl.edu.agh.kis.timetracker.domain.Task;
import pl.edu.agh.kis.timetracker.repository.TaskRepository;

public class TaskFromMapRepository implements TaskRepository {

  private Map<String, Task> tasks;

  public TaskFromMapRepository(
      Map<String, Task> tasks) {
    this.tasks = tasks;
  }

  @Override
  public Optional<Task> save(Task task) {
    return Optional.ofNullable(tasks.put(task.getName(), task));

  }

  @Override
  public Optional<Task> findByName(String name) {
    return Optional.ofNullable(tasks.get(name));
  }

  @Override
  public Set<Task> findAll() {
    return new HashSet<>(tasks.values());
  }
}
