package cucumber.steps;

import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pl.edu.agh.kis.timetracker.domain.Project;
import pl.edu.agh.kis.timetracker.domain.Task;
import pl.edu.agh.kis.timetracker.repository.impl.ProjectRepositoryImpl;
import pl.edu.agh.kis.timetracker.service.Printer;
import pl.edu.agh.kis.timetracker.service.TimeService;
import pl.edu.agh.kis.timetracker.utils.DatabaseHandler;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MessagePrinterSteps {

  private TimeService timeService = new TimeService(Clock.systemUTC());
  private ProjectRepositoryImpl projectRepository = new ProjectRepositoryImpl();
  private Project project = null;
  private Printer printer = new Printer();

  @Given("^user choose project to modify$")
  public void saveProjectToFile() {
    DatabaseHandler.instance().restoreDatabase();
    Task task1 = new Task("PROJ-8 task 1");
    timeService.start(task1);
    timeService.finish(task1);

    project = new Project("project8", Stream.of(task1).collect(Collectors.toSet()));

  }

  @When("^save to file$")
  public void save() {
    projectRepository.saveProject(project);
  }

  @Then("^project successfully retrieved$")
  public void retrieveAndCompare() {
    assertTrue(printer.getTaskLines(projectRepository.findAll()).collect(Collectors.toSet()).toString()
            .contains("project8: PROJ-8 task 1")
    );

  }


}
