package cucumber.steps;

import static org.junit.Assert.assertTrue;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import pl.edu.agh.kis.timetracker.domain.MessageType;
import pl.edu.agh.kis.timetracker.service.MessagePrinter;
import pl.edu.agh.kis.timetracker.service.MessagePrinterFactory;

public class HelloMessageSteps {

  private MessagePrinter messagePrinter;
  private ByteArrayOutputStream OUT = new ByteArrayOutputStream();

  @Given("^create hello message printer$")
  public void createHelloMessagePrinter() {
    messagePrinter = new MessagePrinterFactory().build(MessageType.HELLO);
    System.setOut(new PrintStream(OUT));
  }

  @When("^invoke message printer print$")
  public void invokeMessagePrinterPrint() {
    messagePrinter.print();
  }


  @Then("^print hello massage$")
  public void printHelloMassage() throws Throwable {
    OUT.flush();
    String allWrittenLines = new String(OUT.toByteArray());
    assertTrue(allWrittenLines.contains("Hello"));
  }
}
