package pl.edu.agh.kis.timetracker.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import pl.edu.agh.kis.timetracker.domain.Task;
import pl.edu.agh.kis.timetracker.service.TaskPrintHandler;

public class TaskPrinterStepDef {

  private ByteArrayInputStream bo = new ByteArrayInputStream("1".getBytes());
  private TaskPrintHandler handler;
  private Task task = new Task("name");
  private ByteArrayOutputStream bx = new ByteArrayOutputStream();


  @Given("^start task handler$")
  public void startTask() {
    handler = new TaskPrintHandler();
    System.setOut(new PrintStream(bx));
  }

  @When("^handle user task start event$")
  public void handleTask() {
    handler.handleChosenTask(task, new Scanner(bo), Task::getName);
  }

  @Then("^should register start event$")
  public void doSomething() {
    assertEquals("name", task.getName());
    final String output = new String(bx.toByteArray());
    assertTrue(output.contains("Chosen task: name"));
    assertTrue(output.contains("Type anything to stop"));
    assertTrue(!task.getTimeRanges().isEmpty());
  }
}
