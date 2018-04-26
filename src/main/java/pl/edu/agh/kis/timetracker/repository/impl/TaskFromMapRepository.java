package pl.edu.agh.kis.timetracker.repository.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
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
  public List<Task> findByStartDate(LocalDateTime startDate) {
    return tasks.values().stream()
        .filter(task -> task.getStart() != null)
        .filter(task -> task.getStart().isEqual(startDate))
        .collect(Collectors.toList());

  }

  @Override
  public List<Task> findByFinishDate(LocalDateTime endDate) {
    return tasks.values().stream()
        .filter(task -> task.getFinish() != null)
        .filter(task -> task.getFinish().isEqual(endDate))
        .collect(Collectors.toList());

  }

  @Override
  public List<Task> findAll() {
    return new ArrayList<>(tasks.values());
  }
}
