package pl.edu.agh.kis.timetracker.service;

import static org.junit.Assert.*;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import org.junit.Test;
import pl.edu.agh.kis.timetracker.domain.Task;

public class TimeServiceTest {

  @Test
  public void shouldSetTaskStartTime() {
    // given
    String name = "Test";
    Task task = new Task(name);
    Instant instant = Instant.now();
    ZoneId zoneId = ZoneId.of("Z");

    // when
    new TimeService(Clock.fixed(instant, zoneId)).start(task);

    // then
    assertEquals(LocalDateTime.ofInstant(instant, zoneId), task.getStart());
    assertNull(task.getFinish());
  }
}