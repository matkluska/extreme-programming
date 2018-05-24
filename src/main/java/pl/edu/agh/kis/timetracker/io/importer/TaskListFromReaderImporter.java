package pl.edu.agh.kis.timetracker.io.importer;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.Reader;
import java.util.List;
import pl.edu.agh.kis.timetracker.domain.Task;

public class TaskListFromReaderImporter implements Importer<List<Task>,Reader> {

  @Override
  public List<Task> importFrom(Reader reader) {
    return new Gson().fromJson(reader, new TypeToken<List<Task>>() {
    }.getType());
  }
}
