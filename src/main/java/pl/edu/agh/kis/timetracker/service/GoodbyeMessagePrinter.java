package pl.edu.agh.kis.timetracker.service;

public class GoodbyeMessagePrinter implements MessagePrinter {

  @Override
  public void print() {
    System.out.println("Goodbye user!");
  }
}
