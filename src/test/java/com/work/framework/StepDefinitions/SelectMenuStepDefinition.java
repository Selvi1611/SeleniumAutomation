package com.work.framework.StepDefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.work.framework.InitiateDriver;
import com.work.framework.Pages.FileUploadPage;
import com.work.framework.Pages.SelectMenuPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class SelectMenuStepDefinition {
    private InitiateDriver initiateDriver;
    private WebDriver driver;
    private SelectMenuPage selectMenuPage;

    public SelectMenuStepDefinition(InitiateDriver initiateDriver, SelectMenuPage selectMenuPage) {
        this.initiateDriver = initiateDriver;
        this.selectMenuPage = selectMenuPage;
        this.driver = initiateDriver.getDriver();
    }

    @When("The user is able to see the {string} title")
    public void the_user_is_able_to_see_the_title(String string) {
        String title = selectMenuPage.getTitle(string);
        ExtentCucumberAdapter.addTestStepLog("Page has been loaded successfully : "+title);
        initiateDriver.takeScreenshot(title+"Page");
    }
    @Then("User should be able to select the values from dropdown {string} {string} {string}")
    public void user_should_be_able_to_select_the_values_from_dropdown(String string, String string2, String string3) {
        selectMenuPage.selectValueFromDropdown(string, string2, string3);
        ExtentCucumberAdapter.addTestStepLog(" ----- Selected the Values : "+string +": " +string2 +": " + string3);
        initiateDriver.takeScreenshot("Select Options");
    }
}
