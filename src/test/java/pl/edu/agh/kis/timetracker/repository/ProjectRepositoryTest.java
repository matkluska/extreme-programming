package pl.edu.agh.kis.timetracker.repository;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.kis.timetracker.domain.Project;
import java.util.LinkedList;

public class ProjectRepositoryTest {


    @Test
    public void shouldSaveProjectToFile() {
        String name = "dupa";
        Project project = new Project(name, new LinkedList<>());


        // when
        ProjectRepository repository = new ProjectRepositoryImpl();
        repository.saveProject(project);

        // then
        Project foundProject = repository.getProject(name);
        Assert.assertEquals(foundProject.getName(), name);
    }
}
