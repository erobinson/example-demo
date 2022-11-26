package com.example.demo;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/**/*.feature", glue="com.example.demo", dryRun=false, plugin = {"pretty",
                                                            "json:target/cucumber-report.json"}, tags="@dev")
public class CucumberTest {

    private static final Logger LOG = LoggerFactory.getLogger(CucumberSpringContextConfiguration.class);
    static {
        LOG.info("-------------- Spring Context Initialized For Executing Cucumber Tests --------------");        
    }
}
