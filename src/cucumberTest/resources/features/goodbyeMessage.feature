Feature: GoodbyeMessage

  Scenario: print goodbye message
    Given create goodbye message printer
    When invoke goodbye message printer print
    Then print goodbye massage