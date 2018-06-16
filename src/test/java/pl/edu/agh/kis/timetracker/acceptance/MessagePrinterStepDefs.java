package pl.edu.agh.kis.timetracker.acceptance;

import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import pl.edu.agh.kis.timetracker.domain.MessageType;
import pl.edu.agh.kis.timetracker.domain.Project;
import pl.edu.agh.kis.timetracker.factory.ProjectFactory;
import pl.edu.agh.kis.timetracker.repository.impl.ProjectRepositoryImpl;
import pl.edu.agh.kis.timetracker.service.MessagePrinter;
import pl.edu.agh.kis.timetracker.service.MessagePrinterFactory;

public class MessagePrinterStepDefs {

  private MessagePrinter messagePrinter;
  private ByteArrayOutputStream bo = new ByteArrayOutputStream();
  private ProjectFactory projectFactory = new ProjectFactory();
  private ProjectRepositoryImpl projectRepository = new ProjectRepositoryImpl();
  private Project project = null;


  @Given("^create hello message printer$")
  public void createHelloMessagePrinter() {
    messagePrinter = new MessagePrinterFactory().build(MessageType.HELLO);
    System.setOut(new PrintStream(bo));
  }

  @When("^invoke message printer print$")
  public void invokeMessagePrinterPrint() {
    messagePrinter.print();
  }


  @Then("^print hello massage$")
  public void printHelloMassage() throws Throwable {
    bo.flush();
    String allWrittenLines = new String(bo.toByteArray());
    assertTrue(allWrittenLines.contains("Hello"));
  }

  @Given("^create goodbye message printer$")
  public void createGoodbyeMessagePrinter() {
    messagePrinter = new MessagePrinterFactory().build(MessageType.BYE);
    System.setOut(new PrintStream(bo));
  }

  @When("^invoke goodbye message printer print$")
  public void invokeGoodbyeMessagePrinterPrint() {
    messagePrinter.print();
  }

  @Then("^print goodbye massage$")
  public void printGoodbyeMassage() throws IOException {
    bo.flush();
    String allWrittenLines = new String(bo.toByteArray());
    assertTrue(allWrittenLines.contains("Goodbye"));
  }

  @Given("^create report$")
  public void createReportPrinter() {
    messagePrinter = new MessagePrinterFactory().build(MessageType.REPORT);
    System.setOut(new PrintStream(bo));
  }

  @When("^invoke report$")
  public void invokeReportPrinterPrint() {
    messagePrinter.print();
  }

  @Then("^print report$")
  public void printReportMassage() throws IOException {
    bo.flush();
    String allWrittenLines = new String(bo.toByteArray());
    assertTrue(allWrittenLines.contains(   "Project: project1\n"
        + "Task: PROJ-1 task 1\n"
        + "Task: PROJ-1 task 2\n"
        + "Project: project2\n"
        + "Task: PROJ-2 task 1\n"
        + "Task: PROJ-2 task 2\n"
        + "Project: testProject\n"
        + "Project: testProject\n"
    ));
  }


  @Given("^user choose project to modify$")
  public void saveProjectToFile() {
    project = projectFactory.createProject("project1","PROJ-1 task 1");
  }

  @When("^save and read from file$")
  public void saveAndRead() {
    projectRepository.saveProject(project);
  }

  @Then("^project successfully retrieved$")
  public void retrieveAndCompare() throws IOException {
    projectRepository.getProject(project.getName())
            .ifPresent(projectFromFile -> assertTrue(projectFromFile.equals(project)));
  }

}
