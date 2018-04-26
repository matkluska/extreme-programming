package pl.edu.agh.kis.timetracker.service;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.kis.timetracker.domain.Task;

public class TimeServiceTest {

  private static final String NAME = "test";

  private Instant instant;
  private ZoneId zoneId;

  @Before
  public void setUp() {
    instant = Instant.now();
    zoneId = ZoneId.of("Z");
  }

  @Test
  public void shouldSetTaskStartTime() {
    // given
    Task task = new Task(NAME);

    // when
    new TimeService(Clock.fixed(instant, zoneId)).start(task);

    // then
    assertEquals(LocalDateTime.ofInstant(instant, zoneId), task.getStart());
    assertNull(task.getFinish());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenTaskStarted() {
    // given
    Task task = new Task(NAME);
    task.setStart(LocalDateTime.now());

    // then
    new TimeService(Clock.fixed(instant, zoneId)).start(task);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenNotStartedTaskIsFinished() {
    // given
    Task task = new Task(NAME);
    task.setFinish(LocalDateTime.now());

    // then
    new TimeService(Clock.fixed(instant, zoneId)).start(task);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenTaskNotStarted() {
    new TimeService(Clock.fixed(instant, zoneId)).finish(new Task(NAME));
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenTaskFinishedBeforeStart() {
    // given
    Task task = new Task(NAME);
    Clock clock = Clock.fixed(instant, zoneId);
    task.setStart(LocalDateTime.now(clock).plusYears(1));

    // when
    new TimeService(clock).finish(task);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenTaskAlreadyFinished() {
    // given
    Task task = new Task(NAME);
    Clock clock = Clock.fixed(instant, zoneId);
    task.setStart(LocalDateTime.now(clock));
    task.setFinish(LocalDateTime.now(clock).plusDays(1));

    // when
    new TimeService(clock).finish(task);
  }

  @Test
  public void shouldSetTaskFinishTime() {
    // given
    Task task = new Task(NAME);
    Clock clock = Clock.fixed(instant, zoneId);
    task.setStart(LocalDateTime.now(clock).minusHours(1));

    // when
    new TimeService(clock).finish(task);

    // then
    assertEquals(LocalDateTime.ofInstant(instant, zoneId), task.getFinish());
  }
}