package pl.edu.agh.kis.timetracker;

import java.util.List;
import java.util.Scanner;
import pl.edu.agh.kis.timetracker.domain.MessageType;
import pl.edu.agh.kis.timetracker.domain.Project;
import pl.edu.agh.kis.timetracker.repository.ProjectRepository;
import pl.edu.agh.kis.timetracker.repository.impl.ProjectRepositoryImpl;
import pl.edu.agh.kis.timetracker.service.CommandRouter;
import pl.edu.agh.kis.timetracker.service.MessagePrinter;
import pl.edu.agh.kis.timetracker.service.MessagePrinterFactory;
import pl.edu.agh.kis.timetracker.service.Printer;
import pl.edu.agh.kis.timetracker.service.TaskChooser;
import pl.edu.agh.kis.timetracker.service.TaskFormatter;
import pl.edu.agh.kis.timetracker.service.TaskPrintHandler;

public class Main {

  public static void main(String[] args) {
    ProjectRepository projectRepository = new ProjectRepositoryImpl();
    final Scanner scanner = new Scanner(System.in);
    final List<Project> projects = projectRepository.findAll();

    MessagePrinterFactory factory = new MessagePrinterFactory();
    MessagePrinter helloPrinter = factory.build(MessageType.HELLO);
    MessagePrinter goodbyePrinter = factory.build(MessageType.BYE);

    helloPrinter.print();

    new CommandRouter(scanner, new TaskChooser(projects), new Printer(), new TaskPrintHandler(),
        projects, new TaskFormatter(), projectRepository).route();


    factory.build(MessageType.REPORT).print();

    goodbyePrinter.print();
  }
}
