package pl.edu.agh.kis.timetracker.factory;

import pl.edu.agh.kis.timetracker.domain.Project;
import pl.edu.agh.kis.timetracker.domain.Task;
import pl.edu.agh.kis.timetracker.service.TimeService;

import java.time.Clock;
import java.util.stream.Collectors;
import java.util.stream.Stream;



public class ProjectFactory {

    private TimeService timeService = new TimeService(Clock.systemUTC());

    public Project createProject(String projectName, String taskName1){

        Task task1 = new Task(taskName1);
        timeService.start(task1);
        timeService.finish(task1);

        return new Project(projectName, Stream.of(task1).collect(Collectors.toSet()));
    }
}
