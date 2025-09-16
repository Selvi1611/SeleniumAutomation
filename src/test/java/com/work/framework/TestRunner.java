package com.work.framework;

import com.work.framework.StepDefinitions.TestDataStepDef;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.List;

@CucumberOptions(
        features = {"src/test/resources/features"}, ///TestDataUnderstanding.feature
        glue = {"com.work.framework"},  //tags = "@SmokeTest"
        plugin = {"pretty", "com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
)
public class TestRunner extends AbstractTestNGCucumberTests {
//    @Override
//    @DataProvider(parallel = true)
//    public Object[][] scenarios() {
//        return super.scenarios();
//}
}

