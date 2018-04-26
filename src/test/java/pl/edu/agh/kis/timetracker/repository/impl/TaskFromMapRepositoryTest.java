package pl.edu.agh.kis.timetracker.repository.impl;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import pl.edu.agh.kis.timetracker.domain.Task;
import pl.edu.agh.kis.timetracker.repository.TaskRepository;

public class TaskFromMapRepositoryTest {

  @Mock
  private Map<String, Task> mockedTasks;

  private TaskRepository taskRepository;

  @Before
  public void setUp() {
    mockedTasks = mock(Map.class);
    this.taskRepository = new TaskFromMapRepository(mockedTasks);
  }

  @Test
  public void shouldSaveTask() {
    final Task task = fakeTask();

    taskRepository.save(task);

    verify(mockedTasks, times(1)).put(task.getName(), task);
  }

  @Test
  public void shouldGetTask() {
    final Task task = fakeTask();
    when(mockedTasks.get(task.getName())).thenReturn(task);

    Optional<Task> findedTask = taskRepository.findByName(task.getName());

    assertTrue(findedTask.isPresent());
  }

  private Task fakeTask() {
    return new Task("fake1");
  }
}
