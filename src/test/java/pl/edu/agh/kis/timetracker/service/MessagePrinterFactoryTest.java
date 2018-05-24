package pl.edu.agh.kis.timetracker.service;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import pl.edu.agh.kis.timetracker.domain.MessageType;

public class MessagePrinterFactoryTest {

  private MessagePrinterFactory factory;

  @Before
  public void setup() {
    factory = new MessagePrinterFactory();
  }

  @Test
  public void testHelloMessagePrinterBuild() {
    // Given
    MessageType messageType = MessageType.HELLO;

    // When
    MessagePrinter helloPrinter = factory.build(messageType);

    // Then
    assertEquals(HelloMessagePrinter.class, helloPrinter.getClass());
  }

  @Test
  public void testGoodbyeMessagePrinterBuild() {
    // Given
    MessageType messageType = MessageType.BYE;

    // When
    MessagePrinter helloPrinter = factory.build(messageType);

    // Then
    assertEquals(GoodbyeMessagePrinter.class, helloPrinter.getClass());
  }
}

