Feature: TaskPrinter

  Scenario: print hello message
    Given init handler
    When handle task
    Then shoudl set time ranges
