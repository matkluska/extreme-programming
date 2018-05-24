package pl.edu.agh.kis.timetracker.service;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.Test;

public class HelloMessagePrinterTest {

  @Test
  public void testHelloMessagePrint() throws IOException {
    // Given
    final String expectedMessage = "Hello user!";
    HelloMessagePrinter helloMessagePrinter = new HelloMessagePrinter();
    ByteArrayOutputStream bo = new ByteArrayOutputStream();
    System.setOut(new PrintStream(bo));

    // When
    helloMessagePrinter.print();

    // Then
    bo.flush();
    String allWrittenLines = new String(bo.toByteArray());
    assertTrue(allWrittenLines.contains(expectedMessage));
  }
}
