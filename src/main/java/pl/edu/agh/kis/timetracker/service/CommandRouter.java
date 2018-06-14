package pl.edu.agh.kis.timetracker.service;

import java.util.List;
import java.util.Scanner;
import pl.edu.agh.kis.timetracker.domain.Project;
import pl.edu.agh.kis.timetracker.domain.Task;

public class CommandRouter {

  private Scanner scanner;
  private TaskChooser taskChooser;
  private Printer printer;
  private TaskPrintHandler taskHandeler;
  private List<Project> projects;
  private final Formatter<Task> taskFormatter;

  public CommandRouter(Scanner scanner, TaskChooser taskChooser,
      Printer printer, TaskPrintHandler taskHandeler,
      List<Project> projects, Formatter<Task> taskFormatter) {
    this.scanner = scanner;
    this.taskChooser = taskChooser;
    this.printer = printer;
    this.taskHandeler = taskHandeler;
    this.projects = projects;
    this.taskFormatter = taskFormatter;
  }

  public void route() {
    while (true) {
      printer.getTaskLines(projects).forEach(System.out::println);

      final String command = scanner.next();
      if (isTaskChooser(command)) {
        taskChooser
            .getTaskWithNumber(Integer.parseInt(command)).ifPresent(
            task -> taskHandeler.handleChosenTask(task, scanner, taskFormatter)
        );
      } else if (isExit(command)) {
        break;
      }
    }
  }

  private boolean isExit(String command) {
    return "exit".equalsIgnoreCase(command);
  }

  private boolean isTaskChooser(final String command) {
    try {
      Integer.parseInt(command);
      return true;
    } catch (NumberFormatException ex) {

    }

    return false;
  }
}
