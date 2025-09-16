package com.work.framework.StepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.work.framework.InitiateDriver;
import com.work.framework.Pages.OffersWindowHandlingPage;
import com.work.framework.Pages.FrameMyraPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.io.IOException;

public class FrameStepDefinition {
    private InitiateDriver initiateDriver;
    private WebDriver driver;
    private FrameMyraPage frameMyraPage;

    public FrameStepDefinition(InitiateDriver initiateDriver, FrameMyraPage frameMyraPage) {
        this.initiateDriver = initiateDriver;
        this.frameMyraPage = frameMyraPage;
        this.driver = initiateDriver.getDriver();
    }

    @Given("The user is able to see Myra icon")
    public void the_user_is_able_to_see_myra_icon() throws IOException {
        frameMyraPage.scrollToElement();
        ExtentCucumberAdapter.addTestStepLog("Myra Icon displayed");
        initiateDriver.takeScreenshot("Myra Icon");
        ExtentCucumberAdapter.addTestStepScreenCaptureFromPath("C:\\Users\\muthuselvi.rajapandi\\IdeaProjects\\TestNGSeleniumProject\\screenshots");
    }
    @Given("The user has clicked on it")
    public void the_user_has_clicked_on_it() {
        frameMyraPage.clickMyraIcon();
    }
    @When("The Chat window is opened")
    public void the_chat_window_is_opened() {
        frameMyraPage.MyraWindowOpened();
    }
    @Then("user should be able to type in any questions")
    public void user_should_be_able_to_type_in_any_questions() {
        frameMyraPage.typeChatWindow();
    }
}
