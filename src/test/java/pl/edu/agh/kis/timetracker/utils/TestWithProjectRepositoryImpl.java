package pl.edu.agh.kis.timetracker.utils;

import org.junit.After;
import org.junit.Before;

public class TestWithProjectRepositoryImpl {

  @Before
  public void setUp() {
    DatabaseHandler.instance().restoreDatabase();
  }

  @After
  public void tearDown() {
    DatabaseHandler.instance().restoreDatabase();
  }
}
