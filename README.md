# UIAutomatedTests

This java maven project automates tests for the given website using Selenium webdriver and Cucumber/BDD. It's implemented through Page Object Model. There is a Cucumber Hook class, which has required methods to be executed before and after scenarios/steps. It takes Screenshot for the failed scenarios which is getting saved is Output/Failed_Screenshot It publishes Cucumber report after the test completes.

To run the test follow the steps below:

Through IDE:

    1. Import the project to Eclipse/IntelliJ
    2. Right click on src/test/java/runner/TestRunner or src/test/java/features/Feature.feature and select 'Run TestRunner' or 'Run Feature.feature' correspondingly.
Through Command line:

    1. Go to project path
    2. Run " mvn clean test "
