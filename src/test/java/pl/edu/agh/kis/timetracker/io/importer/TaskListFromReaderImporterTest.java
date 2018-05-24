package pl.edu.agh.kis.timetracker.io.importer;

import static org.junit.Assert.assertEquals;
import static pl.edu.agh.kis.timetracker.providers.TaskProvider.FAKE_TASK;

import com.google.gson.Gson;
import java.io.Reader;
import java.io.StringReader;
import java.util.Collections;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.kis.timetracker.domain.Task;

public class TaskListFromReaderImporterTest {

  private Importer<List<Task>, Reader> taskFromFileImporer;

  @Before
  public void setUp() {
    taskFromFileImporer = new TaskListFromReaderImporter();
  }

  @Test
  public void shouldParseTaskListFromReader() {
    final List<Task> tasks = taskFromFileImporer.importFrom(new StringReader(getJsonTaskList()));

    assertEquals(1, tasks.size());
    assertEquals(FAKE_TASK.getName(), tasks.get(0).getName());
  }

  private String getJsonTaskList() {
    return new Gson().toJson(Collections.singleton(FAKE_TASK));
  }
}
