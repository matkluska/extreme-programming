Feature: MessagePrinter

  Scenario: user modifies project in file
    Given user choose project to modify
    When save to file
    Then project successfully retrieved