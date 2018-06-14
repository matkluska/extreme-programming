Feature: TaskPrinter

  Scenario: register start task event
    Given start task handler
    When handle user task start event
    Then should register start event
