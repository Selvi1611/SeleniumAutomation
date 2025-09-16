package com.work.framework.StepDefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.work.framework.InitiateDriver;
import com.work.framework.Pages.AlertHandlingPage;
import com.work.framework.Pages.FrameMyraPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class AlertStepDefinition
{
    private InitiateDriver initiateDriver;
    private WebDriver driver;
    private AlertHandlingPage alertHandlingPage;
    public Boolean isRobotScreenshot = true;

    public AlertStepDefinition(InitiateDriver initiateDriver, AlertHandlingPage alertHandlingPage) {
        this.initiateDriver = initiateDriver;
        this.alertHandlingPage = alertHandlingPage;
        this.driver = initiateDriver.getDriver();
    }


    @Given("The user has navigated to URL {string}")
    public void the_user_has_navigated_to_url(String string) {
       driver.get(string);
       ExtentCucumberAdapter.addTestStepLog("---URL has been loaded : "+string);
       initiateDriver.takeScreenshot("Alert Example Website");
       String pageTitle = alertHandlingPage.getTitle();
       ExtentCucumberAdapter.addTestStepLog("---Title of the page is : "+pageTitle);
    }
    @When("The user has clicked on {string}")
    public void the_user_has_clicked_on(String string) {
        alertHandlingPage.clickAlertButton(string);
        //initiateDriver.takeScreenshot(string+" Button");
        ExtentCucumberAdapter.addTestStepLog("---"+string+" has been clicked -------");
    }
    @Then("User has to click on the {string} button")
    public void user_has_to_click_on_the_confirm_button(String string) {
        ExtentCucumberAdapter.addTestStepLog("---"+string+" action has been performed in Alert");
       alertHandlingPage.handleAlert(string);
    }
}
