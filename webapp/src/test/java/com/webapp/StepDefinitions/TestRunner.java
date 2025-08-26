package com.webapp.StepDefinitions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/Features",
    glue = {"com.webapp.StepDefinitions"},
    monochrome = true,
    plugin = {
      "pretty",
      "html:target/htmlreport/cucumber-reports.html", // basic HTML
      "json:target/jsonreport/cucumber.json", // JSON report
      "junit:target/xmlreport/cucumber.xml" // JUnit XML report
    },
    tags = "@smoketest")
public class TestRunner {}
