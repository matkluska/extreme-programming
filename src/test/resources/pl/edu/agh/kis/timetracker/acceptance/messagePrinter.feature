Feature: MessagePrinter

  Scenario: print hello message
    Given create hello message printer
    When invoke message printer print
    Then print hello massage

  Scenario: print goodbye message
    Given create goodbye message printer
    When invoke goodbye message printer print
    Then print goodbye massage

  Scenario: print report
    Given create report
    When invoke report
    Then print report