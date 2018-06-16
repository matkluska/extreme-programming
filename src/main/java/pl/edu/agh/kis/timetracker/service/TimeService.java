package pl.edu.agh.kis.timetracker.service;

import java.time.Clock;
import java.time.LocalDateTime;
import pl.edu.agh.kis.timetracker.domain.Task;
import pl.edu.agh.kis.timetracker.domain.TimeRange;

public class TimeService {

  private final Clock clock;

  public TimeService(Clock clock) {
    this.clock = clock;
  }

  public void start(Task task) {
    if (isInvalidNotStartedTask(task)) {
      throw new IllegalArgumentException();
    }
    TimeRange timeRange = new TimeRange();
    timeRange.setStart(LocalDateTime.now(clock));
    task.getTimeRanges().add(timeRange);
    System.out.println(task.getTimeRanges().get(0).getStart());
  }

  private boolean isInvalidNotStartedTask(Task task) {
    return !task.getTimeRanges().isEmpty()
        && task.getTimeRanges().get(task.getTimeRanges().size() - 1).getStart() != null
        && task.getTimeRanges().get(task.getTimeRanges().size() - 1).getFinish() == null;
  }

  public void finish(Task task) {
    LocalDateTime finishTime = LocalDateTime.now(clock);
    if (isInvalidStartedTask(task)) {
      throw new IllegalArgumentException();
    }
    task.getTimeRanges().get(task.getTimeRanges().size() - 1).setFinish(finishTime);
  }

  private boolean isInvalidStartedTask(Task task) {
    return task.getTimeRanges().isEmpty()
        || task.getTimeRanges().get(task.getTimeRanges().size() - 1).getStart() == null;
  }
}
