Feature: TaskPrinter

  Scenario: print hello message
    Given start task handler
    When handle user task start event
    Then should register start event
