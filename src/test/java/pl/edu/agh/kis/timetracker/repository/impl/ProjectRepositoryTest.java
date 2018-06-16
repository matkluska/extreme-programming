package pl.edu.agh.kis.timetracker.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.agh.kis.timetracker.domain.Project;
import pl.edu.agh.kis.timetracker.repository.ProjectRepository;

import java.util.*;

public class ProjectRepositoryTest {


    @Test
    public void shouldSaveProjectToFile() {
        //given
        String name = "testProject";
        Project project = new Project(name, new HashSet<>());

        // when
        ProjectRepository repository = new ProjectRepositoryImpl();
        repository.saveProject(project);

        // then
        Optional<Project> foundProject = repository.getProject(name);
        if(foundProject.isPresent()) {
            Assert.assertEquals(foundProject.get().getName(), name);
        } else {
            Assert.fail();
        }
    }

    @Test
    public void shouldGetAllProjects() {

        // when
        ProjectRepository repository = new ProjectRepositoryImpl();

        // then
        Set<Project> foundProjects = repository.findAll();
        Assert.assertNotNull(foundProjects);

    }
}
