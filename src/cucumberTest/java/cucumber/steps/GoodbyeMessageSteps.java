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

public class GoodbyeMessageSteps {

  private MessagePrinter messagePrinter;
  private ByteArrayOutputStream OUT = new ByteArrayOutputStream();

  @Given("^create goodbye message printer$")
  public void createGoodbyeMessagePrinter() {
    messagePrinter = new MessagePrinterFactory().build(MessageType.BYE);
    System.setOut(new PrintStream(OUT));
  }

  @When("^invoke goodbye message printer print$")
  public void invokeGoodbyeMessagePrinterPrint() {
    messagePrinter.print();
  }

  @Then("^print goodbye massage$")
  public void printGoodbyeMassage() throws IOException {
    OUT.flush();
    String allWrittenLines = new String(OUT.toByteArray());
    assertTrue(allWrittenLines.contains("Goodbye"));
  }

}
