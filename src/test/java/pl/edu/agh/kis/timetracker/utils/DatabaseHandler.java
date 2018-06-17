package pl.edu.agh.kis.timetracker.utils;

import static java.util.Arrays.asList;

import java.util.HashSet;
import java.util.Set;
import pl.edu.agh.kis.timetracker.domain.Project;
import pl.edu.agh.kis.timetracker.domain.Task;
import pl.edu.agh.kis.timetracker.repository.impl.ProjectRepositoryImpl;

public class DatabaseHandler {

  private static DatabaseHandler it;
  private Set<Project> state;

  private DatabaseHandler() {
    this.state = new HashSet<>(asList(
        new Project("project1",
            new HashSet<>(asList(
                new Task("PROJ-1 task 1"),
                new Task("PROJ-1 task 2")))),
        new Project("project2",
            new HashSet<>(asList(
                new Task("PROJ-2 task 1"),
                new Task("PROJ-2 task 2"))))));
  }

  public static DatabaseHandler instance() {
    if (it == null) {
      it = new DatabaseHandler();
    }

    return it;
  }

  public void restoreDatabase(){
    final ProjectRepositoryImpl repository = new ProjectRepositoryImpl();
    repository.removeAll();
    repository.saveAll(state);
  }
}
