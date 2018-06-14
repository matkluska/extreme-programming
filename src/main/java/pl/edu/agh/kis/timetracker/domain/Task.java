package pl.edu.agh.kis.timetracker.domain;

import java.util.ArrayList;
import java.util.List;

public class Task {

  private final String name;
  private final List<TimeRange> timeRanges = new ArrayList<>();

  public Task(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public List<TimeRange> getTimeRanges() {
    return timeRanges;
  }
}
