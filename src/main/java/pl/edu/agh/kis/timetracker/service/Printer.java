package pl.edu.agh.kis.timetracker.service;

import java.util.List;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;
import pl.edu.agh.kis.timetracker.domain.Project;
import pl.edu.agh.kis.timetracker.domain.Task;
import pl.edu.agh.kis.timetracker.domain.TimeRange;

public class Printer {

  public Stream<String> getTaskLines(Set<Project> projects) {
    Builder<String> streamBuilder = Stream.builder();
    int counter = 0;
    for (Project project : projects) {
      for (Task task : project.getTasks()) {
        counter++;
        String line = counter + ". " + project.getName() + ": " + task.getName();
        streamBuilder.add(line);
      }
    }
    return streamBuilder.build();
  }
}
