package pl.edu.agh.kis.timetracker.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import pl.edu.agh.kis.timetracker.domain.Task;

public interface TaskRepository {

  Optional<Task> save(Task task);

  Optional<Task> findByName(final String name);

  List<Task> findByStartDate(final LocalDateTime startDate);

  List<Task> findByFinishDate(final LocalDateTime endDate);

  List<Task> findAll();
}
