package API_Tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = {"classpath:API_Tests/APITests.feature"},
        glue = {"StepDefinitions"})

public class TestRunner_API {

}


