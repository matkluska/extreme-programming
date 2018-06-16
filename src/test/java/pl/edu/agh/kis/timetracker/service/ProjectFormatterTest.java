package pl.edu.agh.kis.timetracker.service;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import org.junit.Test;
import pl.edu.agh.kis.timetracker.domain.Project;
import pl.edu.agh.kis.timetracker.service.project.ProjectFormatter;

public class ProjectFormatterTest {

  private static final String TEST_PROJECT_NAME = "testProjectName";

  @Test
  public void testIfFormatReturnsCorrectlyFormattedProjectText() {
    //given
    ProjectFormatter projectFormatter = new ProjectFormatter();
    Project project = new Project(TEST_PROJECT_NAME, Collections.emptySet());

    //when
    String result = projectFormatter.format(project);

    //then
    assertEquals(result, String.format("Project: %s", TEST_PROJECT_NAME));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testIfFormatThrowsIllegalArgumentExceptionWhenProjectIsNull() {
    //given
    ProjectFormatter projectFormatter = new ProjectFormatter();

    //when
    projectFormatter.format(null);

    //then throw IllegalArgumentException
  }
}