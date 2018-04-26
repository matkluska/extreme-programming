package pl.edu.agh.kis.timetracker.service;

import java.time.Clock;
import java.time.LocalDateTime;
import pl.edu.agh.kis.timetracker.domain.Task;

public class TimeService {

  private final Clock clock;

  public TimeService(Clock clock) {
    this.clock = clock;
  }

  public void start(Task task) {
    if (isInvalidValidNotStartedTask(task)) {
      throw new IllegalArgumentException();
    }
    task.setStart(LocalDateTime.now(clock));
  }

  private boolean isInvalidValidNotStartedTask(Task task) {
    return task.getStart() != null
        || task.getFinish() != null;
  }

  public void finish(Task task) {
    LocalDateTime finishTime = LocalDateTime.now(clock);
    if (isInvalidStartedTask(task, finishTime)) {
      throw new IllegalArgumentException();
    }
    task.setFinish(finishTime);
  }

  private boolean isInvalidStartedTask(Task task, LocalDateTime finishTime) {
    return task.getStart() == null
        || task.getStart().isAfter(finishTime)
        || task.getFinish() != null;
  }
}
