package pl.edu.agh.kis.timetracker.repository.impl;

import com.google.gson.Gson;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import pl.edu.agh.kis.timetracker.domain.Project;
import pl.edu.agh.kis.timetracker.domain.ProjectsConfig;
import pl.edu.agh.kis.timetracker.repository.ProjectRepository;

public class ProjectRepositoryImpl implements ProjectRepository {

  private final String PROJECTS_FILE = "./projects.json";

  @Override
  public void saveProject(Project project) {
    try {
//      URL url = getClass().getClassLoader().getResource(PROJECTS_FILE);
      Path path = Paths.get(PROJECTS_FILE);
      byte[] encoded = Files.readAllBytes(path);

      String json = new String(encoded, Charset.forName("UTF-8"));
      ProjectsConfig config = new Gson().fromJson(json, ProjectsConfig.class);
      config.getProjects().remove(project);
      config.getProjects().add(project);
      String jsonToSave = new Gson().toJson(config);

      PrintWriter out = new PrintWriter(new FileOutputStream(path.toString(), false));
      out.println(jsonToSave);
      out.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void saveAll(final Set<Project> projects) {
    projects.forEach(this::saveProject);
  }

  @Override
  public Optional<Project> getProject(String projectName) {
    try {
      Path path = Paths.get(PROJECTS_FILE);
      byte[] encoded = Files.readAllBytes(path);
      String json = new String(encoded, Charset.forName("UTF-8"));
      ProjectsConfig config = new Gson().fromJson(json, ProjectsConfig.class);

      for (Project project : config.getProjects()) {
        if (project.getName().equals(projectName)) {
          return Optional.of(project);
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return Optional.empty();
  }

  @Override
  public Set<Project> findAll() {
    try {
      Path path = Paths.get(PROJECTS_FILE);
      byte[] encoded = Files.readAllBytes(path);
      String json = new String(encoded, Charset.forName("UTF-8"));
      ProjectsConfig config = new Gson().fromJson(json, ProjectsConfig.class);

      return config.getProjects();
    } catch (Exception e) {
      e.printStackTrace();
      return new HashSet<>();
    }
  }

  @Override
  public Set<Project> removeAll() {
    try {
      Path path = Paths.get(PROJECTS_FILE);
      byte[] encoded = Files.readAllBytes(path);
      String json = new String(encoded, Charset.forName("UTF-8"));
      ProjectsConfig config = new Gson().fromJson(json, ProjectsConfig.class);

      final Set<Project> projects = config.getProjects();
      config.getProjects().clear();

      String jsonToSave = new Gson().toJson(config);

      PrintWriter out = new PrintWriter(new FileOutputStream(path.toString(), false));
      out.println(jsonToSave);
      out.close();

      return projects;
    } catch (Exception e) {
      e.printStackTrace();
      return new HashSet<>();
    }

  }
}
