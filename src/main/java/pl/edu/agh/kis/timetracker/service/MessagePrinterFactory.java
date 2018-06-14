package pl.edu.agh.kis.timetracker.service;

import pl.edu.agh.kis.timetracker.domain.MessageType;

public class MessagePrinterFactory {
  public MessagePrinter build(MessageType messageType) {
    switch (messageType) {
      case HELLO:
        return new HelloMessagePrinter();
      case BYE:
        return new GoodbyeMessagePrinter();
      case REPORT:
        return new ReportMessagePrinter();
    }
    throw new IllegalArgumentException();
  }
}
