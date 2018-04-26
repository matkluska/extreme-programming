package pl.edu.agh.kis.timetracker.repository;

import com.google.gson.Gson;
import pl.edu.agh.kis.timetracker.domain.Project;
import pl.edu.agh.kis.timetracker.domain.ProjectsConfig;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProjectRepositoryImpl implements ProjectRepository {

    private final String PROJECTS_FILE = "src/main/resources/projects.json";

    @Override
    public void saveProject(Project project) {
        try {
            Path path = Paths.get(PROJECTS_FILE);
            byte[] encoded = Files.readAllBytes(path);
            String json = new String(encoded, Charset.forName("UTF-8"));
            ProjectsConfig config = new Gson().fromJson(json, ProjectsConfig.class);

            config.getProjects().add(project);
            String jsonToSave = new Gson().toJson(config).toString();
            PrintWriter out = new PrintWriter(PROJECTS_FILE);
            out.println(jsonToSave);
            out.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Project getProject(String projectName) {
        try {
            byte[] encoded = Files.readAllBytes(Paths.get(PROJECTS_FILE));
            String json = new String(encoded, Charset.forName("UTF-8"));
            ProjectsConfig config = new Gson().fromJson(json, ProjectsConfig.class);
            System.out.println(config);

            for(Project project: config.getProjects()){
                if(project.getName().equals(projectName)){
                    return project;
                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }

        return null;
    }

}
