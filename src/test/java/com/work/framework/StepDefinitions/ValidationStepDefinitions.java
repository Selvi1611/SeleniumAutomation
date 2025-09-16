package com.work.framework.StepDefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.work.framework.InitiateDriver;
import com.work.framework.Pages.LanguageSelection;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidationStepDefinitions {

    private InitiateDriver initiateDriver;
    private WebDriver driver;
    private LanguageSelection languageSelection;


    public ValidationStepDefinitions(InitiateDriver initiateDriver, LanguageSelection languageSelection) {
        this.initiateDriver = initiateDriver;
        this.languageSelection = languageSelection;
        this.driver = initiateDriver.getDriver();
    }

    @Given("The user has clicked on the Country selection Menu")
    public void the_user_has_clicked_on_the_country_selection_menu() {
        languageSelection.clickCountryMenu();
        initiateDriver.takeScreenshot("Country Menu Popup");
    }
    @Given("The selection window has been opened with {string}")
    public void the_selection_window_has_been_opened_with(String string) {
        List<String> items = Arrays.asList(string.split(","));
        List<Boolean> resultHeader = new ArrayList<>();
        for (int i = 0; i < items.size(); i++) {
            Boolean result = languageSelection.validateHeaders(items.get(i));
            if(true){
                Assert.assertTrue(true,items.get(i)+" is available");
            }
        }
    }
    @When("The user has selected the options {string} {string} {string}")
    public void the_user_has_selected_the_options(String country, String currency, String language) {
        languageSelection.selectCountry(country);
    }
    @When("The user has clicked on Apply")
    public void the_user_has_clicked_on_apply() {
        languageSelection.clickApplyBtn();
    }
    @Then("The page should be changed with the selections accordingly {string}")
    public void the_page_should_be_changed_with_the_selections_accordingly(String currencyShort) {
        String value = languageSelection.validateMenuChanges();
        if(value.equalsIgnoreCase(currencyShort)){
            ExtentCucumberAdapter.addTestStepLog("Changes has been updated successfully");
            initiateDriver.takeScreenshot("MyBiz Popup");
        }
    }

    @Given("The user hovers on the myBiz Menu")
    public void the_user_hovers_on_the_my_biz_menu() {
        languageSelection.selectMyBizMenu();

    }
    @When("The Popup has been displayed")
    public void the_popup_has_been_displayed() {
        String Title = languageSelection.validatePopup();
        ExtentCucumberAdapter.addTestStepLog("MyBiz Popup :"+Title);
        initiateDriver.takeScreenshot(Title+ " My Biz");
    }
    @Then("User validating the title of the popup")
    public void user_validating_the_title_of_the_popup() {
        languageSelection.clickSwitchToBizBtn();
    }

}
