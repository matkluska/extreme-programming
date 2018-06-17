package cucumber.steps;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Scanner;
import pl.edu.agh.kis.timetracker.repository.ProjectRepository;
import pl.edu.agh.kis.timetracker.repository.impl.ProjectRepositoryImpl;
import pl.edu.agh.kis.timetracker.service.CommandRouter;
import pl.edu.agh.kis.timetracker.service.Printer;
import pl.edu.agh.kis.timetracker.service.TaskChooser;
import pl.edu.agh.kis.timetracker.service.TaskFormatter;
import pl.edu.agh.kis.timetracker.service.TaskPrintHandler;
import pl.edu.agh.kis.timetracker.utils.DatabaseHandler;

public class TaskRegistrationSteps {

  private static final ByteArrayOutputStream OUT = new ByteArrayOutputStream();
  private CommandRouter commandRouter;

  @Before(order = 1)
  public void cleanUpDatabase() {
    DatabaseHandler.instance().restoreDatabase();
  }

  @Given("^select first project and register task$")
  public void givenSelectFirstProjectAndRegisterTask() {
    final String givenRouterInput = "1\n" +
        "stop\n" +
        "exit\n";
    commandRouter = createCommandRouter(givenRouterInput);

    System.setOut(new PrintStream(OUT));
  }

  @When("^route commands$")
  public void routeCommands() {
    commandRouter.route();
  }

  @Then("^print finished tasks")
  public void thenShouldRegisterTask() {
    final String output = OUT.toString();
    final String expected = "1. project1: PROJ-1 task 1\n"
        + "2. project1: PROJ-1 task 2\n"
        + "3. project2: PROJ-2 task 1\n"
        + "4. project2: PROJ-2 task 2\n"
        + "Chosen task: PROJ-1 task 1\n"
        + "2007-12-03T10:15:30\n"
        + "Type anything to stop\n"
        + "Task: PROJ-1 task 1 [2007-12-03T10:15:30-2007-12-03T10:15:30]\n"
        + "1. project1: PROJ-1 task 1\n"
        + "2. project1: PROJ-1 task 2\n"
        + "3. project2: PROJ-2 task 1\n"
        + "4. project2: PROJ-2 task 2\n";

    assertEquals(expected, output);
  }

  private CommandRouter createCommandRouter(final String givenRouterInput) {
    final Scanner stubbedScanner = new Scanner(
        new ByteArrayInputStream(givenRouterInput.getBytes()));
    final ProjectRepository repository = new ProjectRepositoryImpl();
    final Clock clock = mock(Clock.class);
    when(clock.instant()).thenReturn(Instant.parse("2007-12-03T10:15:30.00Z"));
    when(clock.getZone()).thenReturn(ZoneId.of("Z"));

    return new CommandRouter(stubbedScanner, new TaskChooser(repository.findAll()), new Printer(),
        new TaskPrintHandler(clock), repository.findAll(), new TaskFormatter(), repository);
  }
}
