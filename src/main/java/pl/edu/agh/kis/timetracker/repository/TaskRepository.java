package pl.edu.agh.kis.timetracker.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import pl.edu.agh.kis.timetracker.domain.Task;

public interface TaskRepository {

  Optional<Task> save(Task task);

  Optional<Task> findByName(final String name);

  Set<Task> findAll();
}
