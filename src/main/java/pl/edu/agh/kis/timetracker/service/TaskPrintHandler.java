package pl.edu.agh.kis.timetracker.service;

import java.time.Clock;
import java.util.Scanner;
import pl.edu.agh.kis.timetracker.domain.Task;

public class TaskPrintHandler {

  private TimeService timeService = new TimeService(Clock.systemUTC());

  public void handleChosenTask(Task task, Scanner scanner) {
    System.out.println("Chosen task: " + task.getName());
    timeService.start(task);
    System.out.println("Type anything to stop");
    scanner.next();
    timeService.finish(task);
    System.out.println("Chosen task: " + task.getName() + " " + task.getStart() + " to " + task.getFinish());
  }


}
