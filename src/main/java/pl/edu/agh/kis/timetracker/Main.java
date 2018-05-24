package pl.edu.agh.kis.timetracker;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;

import java.time.Clock;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import pl.edu.agh.kis.timetracker.domain.MessageType;
import pl.edu.agh.kis.timetracker.domain.Project;
import pl.edu.agh.kis.timetracker.domain.Task;
import pl.edu.agh.kis.timetracker.service.MessagePrinter;
import pl.edu.agh.kis.timetracker.service.MessagePrinterFactory;
import pl.edu.agh.kis.timetracker.service.Printer;
import pl.edu.agh.kis.timetracker.service.TaskChooser;
import pl.edu.agh.kis.timetracker.service.TimeService;

public class Main {

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);

    Printer printer = new Printer();
    TaskChooser taskChooser = new TaskChooser(scanner);
    TimeService timeService = new TimeService(Clock.systemUTC());
    MessagePrinterFactory factory = new MessagePrinterFactory();
    MessagePrinter helloPrinter = factory.build(MessageType.HELLO);
    MessagePrinter goodbyePrinter = factory.build(MessageType.BYE);

    List<Project> projects = singletonList(new Project("projectName",
        asList(new Task("task1"), new Task("task2")))); // TODO projects repo

    helloPrinter.print();

    while (true) {
      printAvailableTasks(printer, projects);
      Optional<Task> chosenTask = taskChooser.getChosenTask(projects);
      chosenTask.ifPresent(task -> handleChosenTask(timeService, task, scanner));

    }
//    goodbyePrinter.print();
  }

  private static void printAvailableTasks(Printer printer, List<Project> projects) {
    printer.getTaskLines(projects)
        .forEach(System.out::println);
  }

  private static void handleChosenTask(TimeService timeService, Task task, Scanner scanner) {
    System.out.println("Chosen task: " + task.getName());
    timeService.start(task);
    System.out.println("Type anything to stop");
    scanner.next();
    timeService.finish(task);
    System.out.println(
        "Chosen task: " + task.getName() + " " + task.getStart() + " to " + task.getFinish());
  }
}
