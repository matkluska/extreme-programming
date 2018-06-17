Feature: MessagePrinter

  Scenario: user modifies project in file
    Given user choose project to modify
    When save and read from file
    Then project successfully retrieved