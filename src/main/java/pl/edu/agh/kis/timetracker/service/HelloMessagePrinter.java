package pl.edu.agh.kis.timetracker.service;

public class HelloMessagePrinter implements MessagePrinter {

  @Override
  public void print() {
    System.out.println("Hello user!");
  }
}
