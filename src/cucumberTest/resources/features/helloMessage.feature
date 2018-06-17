Feature: HelloMessage

  Scenario: print hello message
    Given create hello message printer
    When invoke message printer print
    Then print hello massage