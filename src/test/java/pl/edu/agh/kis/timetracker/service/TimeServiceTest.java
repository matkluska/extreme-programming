package pl.edu.agh.kis.timetracker.service;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.kis.timetracker.domain.Task;
import pl.edu.agh.kis.timetracker.domain.TimeRange;

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
    assertEquals(LocalDateTime.ofInstant(instant, zoneId), task.getTimeRanges().get(0).getStart());
    assertNull(task.getTimeRanges().get(0).getFinish());
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenTaskStarted() {
    // given
    Task task = new Task(NAME);
    TimeRange timeRange = new TimeRange();
    timeRange.setStart(LocalDateTime.now());
    task.getTimeRanges().add(timeRange);

    // then
    new TimeService(Clock.fixed(instant, zoneId)).start(task);
  }

  @Test(expected = IllegalArgumentException.class)
  public void shouldThrowExceptionWhenTaskNotStarted() {
    new TimeService(Clock.fixed(instant, zoneId)).finish(new Task(NAME));
  }

  @Test
  public void shouldSetTaskFinishTime() {
    // given
    Task task = new Task(NAME);
    Clock clock = Clock.fixed(instant, zoneId);
    TimeRange timeRange = new TimeRange();
    timeRange.setStart(LocalDateTime.now(clock).minusHours(1));
    task.getTimeRanges().add(timeRange);

    // when
    new TimeService(clock).finish(task);

    // then
    assertEquals(LocalDateTime.ofInstant(instant, zoneId), task.getTimeRanges().get(0).getFinish());
  }
}