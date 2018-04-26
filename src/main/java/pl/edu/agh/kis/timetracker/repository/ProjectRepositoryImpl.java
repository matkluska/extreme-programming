package pl.edu.agh.kis.timetracker.repository;

import com.google.gson.Gson;
import pl.edu.agh.kis.timetracker.domain.Project;
import pl.edu.agh.kis.timetracker.domain.ProjectsConfig;

import java.io.PrintWriter;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProjectRepositoryImpl implements ProjectRepository {

    private final String PROJECTS_FILE = "projects.json";

    @Override
    public void saveProject(Project project) {
        try {
            URL url = getClass().getClassLoader().getResource(PROJECTS_FILE);
            Path path = Paths.get(url.toURI());
            byte[] encoded = Files.readAllBytes(path);


            String json = new String(encoded, Charset.forName("UTF-8"));
            ProjectsConfig config = new Gson().fromJson(json, ProjectsConfig.class);

            config.getProjects().add(project);
            String jsonToSave = new Gson().toJson(config).toString();
            PrintWriter out = new PrintWriter(path.toString());
            out.println(jsonToSave);
            out.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Project getProject(String projectName) {
        try {
            URL url = getClass().getClassLoader().getResource(PROJECTS_FILE);
            Path path = Paths.get(url.toURI());
            byte[] encoded = Files.readAllBytes(path);
            String json = new String(encoded, Charset.forName("UTF-8"));
            ProjectsConfig config = new Gson().fromJson(json, ProjectsConfig.class);

            for(Project project: config.getProjects()){
                if(project.getName().equals(projectName)){
                    return project;
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }

        return null;
    }

}
