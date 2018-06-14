package pl.edu.agh.kis.timetracker.acceptance;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.ByteArrayInputStream;
import java.util.Scanner;
import pl.edu.agh.kis.timetracker.domain.MessageType;
import pl.edu.agh.kis.timetracker.domain.Task;
import pl.edu.agh.kis.timetracker.service.MessagePrinter;
import pl.edu.agh.kis.timetracker.service.MessagePrinterFactory;
import pl.edu.agh.kis.timetracker.service.TaskPrintHandler;

public class TaskPrinterStepDef {

  private MessagePrinter messagePrinter;
  private ByteArrayInputStream bo = new ByteArrayInputStream("1".getBytes());
  private TaskPrintHandler handler;

  @Given("^init handler$")
  public void startTask() {
    messagePrinter = new MessagePrinterFactory().build(MessageType.HELLO);
    handler = new TaskPrintHandler();
  }

  @When("^handle task$")
  public void handleTask() {
    handler.handleChosenTask(new Task("name"), new Scanner(bo), Task::getName);
  }

  @Then("^shoudl set time ranges$")
  public void doSomething(){
  }
}
