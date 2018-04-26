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
    return String
        .format("Task: %s [%s-%s]", task.getName(), formatDate(task.getStart()),
            formatDate(task.getFinish()));
  }

  private String formatDate(final LocalDateTime dateTime) {
    return dateTime != null ? dateTime.format(DATE_TIME_FORMATTER) : "_";
  }


}