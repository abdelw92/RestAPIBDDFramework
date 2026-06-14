package com.apple.Runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        plugin = {
                "pretty",
                "html:target/cucumber-reports.html",
                "rerun:target/rerun.txt",
                "json:target/jsonReports/cucumber-report.json"

        },
        features = "src/test/resources/features",
        glue =  "com/apple/stepDef",
        dryRun = false,
        publish = true,
        tags = "@DeletePlace"

)

public class CukesRunner {

}
