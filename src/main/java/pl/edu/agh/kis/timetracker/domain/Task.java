package pl.edu.agh.kis.timetracker.domain;

import java.time.LocalDateTime;

public class Task {
  private final String name;
  private LocalDateTime start;
  private LocalDateTime finish;

  public Task(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public LocalDateTime getStart() {
    return start;
  }

  public void setStart(LocalDateTime start) {
    this.start = start;
  }

  public LocalDateTime getFinish() {
    return finish;
  }

  public void setFinish(LocalDateTime finish) {
    this.finish = finish;
  }
}
