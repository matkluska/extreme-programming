Feature: TaskRegistration

  Scenario: should register task
    Given select first project and register task
    When route commands
    Then print finished tasks