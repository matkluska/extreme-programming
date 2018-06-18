package pl.edu.agh.kis.timetracker.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import pl.edu.agh.kis.timetracker.domain.Task;
import pl.edu.agh.kis.timetracker.domain.TimeRange;

public class TaskFormatter implements Formatter<Task> {

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

  @Override
  public String format(final Task task) {
    if (task == null) {
      throw new IllegalArgumentException("Task must not be null");
    }
    StringBuilder builder = new StringBuilder("Task: ");
    builder.append(task.getName());
    builder.append(" ");

    long sumSeconds = 0;
    for (TimeRange timeRange : task.getTimeRanges()) {
      sumSeconds += timeRange.getFinish().toInstant(ZoneOffset.UTC).getEpochSecond()
          - timeRange.getStart().toInstant(ZoneOffset.UTC).getEpochSecond();
    }

    builder.append(LocalDateTime.ofEpochSecond(sumSeconds, 0, ZoneOffset.UTC)
        .format(DateTimeFormatter.ofPattern("HH:mm:ss")));

    return builder.toString();
  }

}
