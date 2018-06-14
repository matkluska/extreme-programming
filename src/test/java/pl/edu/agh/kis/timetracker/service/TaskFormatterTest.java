package pl.edu.agh.kis.timetracker.service;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.junit.Test;
import pl.edu.agh.kis.timetracker.domain.Task;
import pl.edu.agh.kis.timetracker.domain.TimeRange;


public class TaskFormatterTest {

  private static final String TEST_TASK_NAME = "testTaskName";

  @Test
  public void testIfFormatReturnsCorrectlyFormattedTaskText() {
    //given
    TaskFormatter taskFormatter = new TaskFormatter();
    Task task = new Task(TEST_TASK_NAME);
    LocalDateTime startDate = LocalDateTime.of(2018, 1, 13, 11, 45);
    LocalDateTime finishDate = LocalDateTime.of(2018, 1, 13, 16, 45);
    TimeRange timeRange = new TimeRange();
    timeRange.setStart(startDate);
    timeRange.setFinish(finishDate);
    task.getTimeRanges().add(timeRange);

    //when
    String result = taskFormatter.format(task);

    //then
    assertEquals(result, String
        .format("Task: %s [%s-%s]", task.getName(),
            startDate.format(DateTimeFormatter.ISO_DATE_TIME),
            finishDate.format(DateTimeFormatter.ISO_DATE_TIME)));
  }

  @Test
  public void testIfFormatReturnsCorrectlyFormattedTaskTextWhenDatesAreNull() {
    //given
    TaskFormatter taskFormatter = new TaskFormatter();
    Task task = new Task(TEST_TASK_NAME);

    //when
    String result = taskFormatter.format(task);

    //then
    assertEquals(String.format("Task: %s", TEST_TASK_NAME), result);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfFormatThrowsIllegalArgumentExceptionWhenTaskIsNull() {
    //given
    TaskFormatter taskFormatter = new TaskFormatter();

    //when
    taskFormatter.format(null);

    //then throw IllegalArgumentException
  }
}