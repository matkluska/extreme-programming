package pl.edu.agh.kis.timetracker.service;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import pl.edu.agh.kis.timetracker.domain.Project;
import pl.edu.agh.kis.timetracker.domain.Task;

public class TaskChooser {

  private final Scanner scanner;

  public TaskChooser(Scanner scanner) {
    this.scanner = scanner;
  }

  public Optional<Task> getChosenTask(List<Project> projects) {
    Optional<Integer> taskNumber = getChosenTaskNumber();
    return taskNumber
        .flatMap(number -> getTaskWithNumber(projects, number));
  }

  private Optional<Integer> getChosenTaskNumber() {
    try {
      if (scanner.hasNextInt()) {
        int readInt = scanner.nextInt();
        return Optional.of(readInt);
      }
      scanner.next();
      return Optional.empty();
    } catch (InputMismatchException e) {
      return Optional.empty();
    }
  }

  private Optional<Task> getTaskWithNumber(List<Project> projects, int taskNumber) {
    int counter = 0;
    for (Project project : projects) {
      for (Task task : project.getTasks()) {
        counter++;
        if (taskNumber == counter) {
          return Optional.of(task);
        }
      }
    }
    return Optional.empty();
  }
}