package com.arun.cucumber.hello.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * To run cucumber test
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/specifications", plugin = {"pretty",
                                                            "json:target/cucumber/cucumber-report.json"})
public class CucumberTest {

}
