package UI_Tests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

    @RunWith(Cucumber.class)
    @CucumberOptions(

            features = {"classpath:UI_Tests/UITests.feature"},
            glue = {"StepDefinitions"})

    public class TestRunner_UI {

    }


