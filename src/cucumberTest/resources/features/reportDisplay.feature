Feature: ReportDisplay

  Scenario: Display report with current project file state
    Given Initialize application state with test data
    When Exit application and invoke report generation
    Then Check displayed report