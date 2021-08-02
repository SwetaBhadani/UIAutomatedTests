package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = {"src/test/java/features"}, plugin = {"json:target/cucumber.json", "pretty"},
        glue = "stepDefinition")
public class TestRunner extends AbstractTestNGCucumberTests {

}