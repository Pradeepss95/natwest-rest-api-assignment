package org.example.restapitest.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        glue = "org/example/restapitest/stepdefinitions",
        //tags = "@ObjectItem",
        features = "src/test/resources/features",
        plugin = {"pretty",
                "html:target/cucumber-reports/html-report.html",
                "json:target/cucumber-reports/Cucumber.json"})

public class TestRunnerObjectFeature extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
