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
    task.setStart(LocalDateTime.now(clock));
  }
}
