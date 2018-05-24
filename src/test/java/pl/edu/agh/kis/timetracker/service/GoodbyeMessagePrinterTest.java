package pl.edu.agh.kis.timetracker.service;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.Test;

public class GoodbyeMessagePrinterTest {
  @Test
  public void testGoodbyeMessagePrint() throws IOException {
    // Given
    final String expectedMessage = "Goodbye user!";
    GoodbyeMessagePrinter goodbyeMessagePrinter = new GoodbyeMessagePrinter();
    ByteArrayOutputStream bo = new ByteArrayOutputStream();
    System.setOut(new PrintStream(bo));

    // When
    goodbyeMessagePrinter.print();

    // Then
    bo.flush();
    String allWrittenLines = new String(bo.toByteArray());
    assertTrue(allWrittenLines.contains(expectedMessage));
  }
}
