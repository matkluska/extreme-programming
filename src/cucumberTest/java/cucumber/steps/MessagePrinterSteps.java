package cucumber.steps;

import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pl.edu.agh.kis.timetracker.domain.Project;
import pl.edu.agh.kis.timetracker.factory.ProjectFactory;
import pl.edu.agh.kis.timetracker.repository.impl.ProjectRepositoryImpl;
import pl.edu.agh.kis.timetracker.utils.DatabaseHandler;

public class MessagePrinterSteps {

  private ProjectFactory projectFactory = new ProjectFactory();
  private ProjectRepositoryImpl projectRepository = new ProjectRepositoryImpl();
  private Project project = null;

  @Given("^user choose project to modify$")
  public void saveProjectToFile() {
    DatabaseHandler.instance().restoreDatabase();
    project = projectFactory.createProject("project1", "PROJ-1 task 1");
  }

  @When("^save and read from file$")
  public void saveAndRead() {
    projectRepository.saveProject(project);
  }

  @Then("^project successfully retrieved$")
  public void retrieveAndCompare() {
    projectRepository.getProject(project.getName())
        .ifPresent(projectFromFile -> assertTrue(projectFromFile.equals(project)));
  }
}
