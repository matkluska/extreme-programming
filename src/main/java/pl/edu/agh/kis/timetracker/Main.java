package pl.edu.agh.kis.timetracker;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

import java.time.Clock;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import pl.edu.agh.kis.timetracker.domain.Project;
import pl.edu.agh.kis.timetracker.domain.Task;
import pl.edu.agh.kis.timetracker.service.Printer;
import pl.edu.agh.kis.timetracker.service.TaskChooser;
import pl.edu.agh.kis.timetracker.service.TimeService;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    Printer printer = new Printer();
    TaskChooser taskChooser = new TaskChooser(scanner);
    TimeService timeService = new TimeService(Clock.systemUTC());

    List<Project> projects = singletonList(new Project("projectName",
        asList(new Task("task1"), new Task("task2")))); // TODO projects repo
    // TODO HELLO MESSAGE
    while (true) {
      printAvailableTasks(printer, projects);
      Optional<Task> chosenTask = taskChooser.getChosenTask(projects);
      chosenTask.ifPresent(task -> handleChosenTask(timeService, task));

    }
    // TODO GOODBYE MESSAGE
  }

  private static void printAvailableTasks(Printer printer, List<Project> projects) {
    printer.getTaskLines(projects)
        .forEach(System.out::println);
  }

  private static void handleChosenTask(TimeService timeService, Task task) {
    System.out.println("Chosen task: " + task.getName());
    timeService.start(task);
    System.out.println("Type anything to stop");
    timeService.finish(task);
  }
}
