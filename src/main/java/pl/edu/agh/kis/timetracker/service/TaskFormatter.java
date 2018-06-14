package pl.edu.agh.kis.timetracker.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import pl.edu.agh.kis.timetracker.domain.Task;

public class TaskFormatter implements Formatter<Task> {

  private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

  @Override
  public String format(final Task task) {
    if (task == null) {
      throw new IllegalArgumentException("Task must not be null");
    }
    StringBuilder builder = new StringBuilder("Task: ");
    builder.append(task.getName());

    task.getTimeRanges().forEach(timeRange -> {
      builder.append(" [");
      builder.append(formatDate(timeRange.getStart()));
      builder.append("-");
      builder.append(formatDate(timeRange.getFinish()));
      builder.append("]");
    });

    return builder.toString();
  }

  private String formatDate(final LocalDateTime dateTime) {
    return dateTime != null ? dateTime.format(DATE_TIME_FORMATTER) : "_";
  }


}
