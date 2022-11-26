package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.cucumber.java.Before;

public class LoadMTDataSteps extends SpringConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(LoadMTDataSteps.class);

    /**
     * Need this method so the cucumber will recognize this class as glue and load spring context configuration
     */
    @Before
    public void setUp() {
      LOG.error("-------------- Spring Context Initialized For Executing Cucumber Tests --------------");
      System.out.println("something!!!");
    }
}
