package pl.edu.agh.kis.timetracker.service;

import static java.util.Arrays.asList;
import static org.junit.Assert.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.Test;
import pl.edu.agh.kis.timetracker.domain.Project;
import pl.edu.agh.kis.timetracker.domain.Task;

public class PrinterTest {

  private static final String FIRST_PROJECT_NAME = "proj_1";
  private static final String SECOND_PROJECT_NAME = "proj_2";
  private static final String TASK_1 = "task_1";
  private static final String TASK_2 = "task_2";
  private static final String TASK_3 = "task_3";
  private static final String TASK_4 = "task_4";


  @Test
  public void getTaskLines() {
    // given
    Project project1 = new Project(FIRST_PROJECT_NAME, Stream.of(new Task(TASK_1), new Task(TASK_2)).collect(Collectors.toSet()));
    Project project2 = new Project(SECOND_PROJECT_NAME, Stream.of(new Task(TASK_3), new Task(TASK_4)).collect(Collectors.toSet()));

    // when
    Stream<String> taskLines = new Printer().getTaskLines(Stream.of(project1, project2).collect(Collectors.toSet()));

    // then
    List<String> tasks = taskLines.collect(Collectors.toList());
    assertEquals("1. " + FIRST_PROJECT_NAME + ": " + TASK_1, tasks.get(0));
    assertEquals("2. " + FIRST_PROJECT_NAME + ": " + TASK_2, tasks.get(1));
    assertEquals("3. " + SECOND_PROJECT_NAME + ": " + TASK_3, tasks.get(2));
    assertEquals("4. " + SECOND_PROJECT_NAME + ": " + TASK_4, tasks.get(3));
  }
}