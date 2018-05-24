package pl.edu.agh.kis.timetracker.io.exporter;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.kis.timetracker.domain.Task;

public class TaskToFileExporterTest {

  private Exporter<List<Task>, FileWriter> exporter;

  @Before
  public void setUp() {
    exporter = new TaskListToFileExporter();
  }

  @Test
  public void shouldWriteTasksToWriter() throws IOException {
    //given
    FileWriter fileWriter = mock(FileWriter.class);
    List<Task> tasks = singletonList(fakeTask());

    //when
    exporter.export(tasks, fileWriter);

    //then
    verify(fileWriter, times(1)).write(new Gson().toJson(tasks));
  }

  @Test
  public void shouldWriteEmptyTaskListToWriter() throws IOException {
    //given
    FileWriter fileWriter = mock(FileWriter.class);
    List<Task> tasks = emptyList();

    //when
    exporter.export(tasks, fileWriter);

    //then
    verify(fileWriter, times(1)).write(new Gson().toJson(tasks));
  }

  private Task fakeTask() {
    final Task task = new Task("fakeTask");
    task.setStart(LocalDateTime.now());
    task.setFinish(LocalDateTime.now());

    return task;
  }
}
