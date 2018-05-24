package pl.edu.agh.kis.timetracker.io.exporter;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import pl.edu.agh.kis.timetracker.domain.Task;

public class TaskListToFileExporter implements Exporter<List<Task>, FileWriter> {

  @Override
  public void export(List<Task> task, FileWriter writer) {
    try {
      writer.write(new Gson().toJson(task));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
