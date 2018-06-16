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

  @Override
  public int hashCode() {
    return 1;
  }

@Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (obj == this) return true;
    if (!(obj instanceof Task))return false;
    if (((Task) obj).name.equals(this.name)) return true;
    return false;
  }
}
