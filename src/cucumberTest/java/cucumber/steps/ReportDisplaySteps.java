package cucumber.steps;

import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import pl.edu.agh.kis.timetracker.domain.MessageType;
import pl.edu.agh.kis.timetracker.service.MessagePrinter;
import pl.edu.agh.kis.timetracker.service.MessagePrinterFactory;
import pl.edu.agh.kis.timetracker.utils.DatabaseHandler;

public class ReportDisplaySteps {

  private MessagePrinter messagePrinter;
  private ByteArrayOutputStream OUT = new ByteArrayOutputStream();

  @Given("^Initialize application state with test data$")
  public void createReportPrinter() {
    DatabaseHandler.instance().restoreDatabase();
    messagePrinter = new MessagePrinterFactory().build(MessageType.REPORT);
    System.setOut(new PrintStream(OUT));
  }

  @When("^Exit application and invoke report generation$")
  public void invokeReportPrinterPrint() {
    messagePrinter.print();
  }

  @Then("^Check displayed report$")
  public void printReportMassage() throws IOException {
    OUT.flush();
    String allWrittenLines = new String(OUT.toByteArray());
    assertTrue(allWrittenLines.contains("Project: project1\n"
        + "Task: PROJ-1 task 1\n"
        + "Task: PROJ-1 task 2\n"
        + "Project: project2\n"
        + "Task: PROJ-2 task 1\n"
        + "Task: PROJ-2 task 2\n"));
  }
}
