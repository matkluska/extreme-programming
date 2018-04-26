package pl.edu.agh.kis.timetracker.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import org.junit.Test;
import pl.edu.agh.kis.timetracker.domain.Project;

public class ProjectFormatterTest {

  private static final String TEST_PROJECT_NAME = "testProjectName";

  @Test
  public void testIfFormatReturnsCorrectlyFormattedProjectText() {
    //given
    ProjectFormatter projectFormatter = new ProjectFormatter();
    Project project = new Project(TEST_PROJECT_NAME, new ArrayList<>());

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
    String result = projectFormatter.format(null);

    //then throw IllegalArgumentException
  }
}