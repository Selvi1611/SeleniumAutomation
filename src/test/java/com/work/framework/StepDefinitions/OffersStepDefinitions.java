package com.work.framework.StepDefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.work.framework.InitiateDriver;
import com.work.framework.Pages.LanguageSelection;
import com.work.framework.Pages.OffersWindowHandlingPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class OffersStepDefinitions {
    private InitiateDriver initiateDriver;
    private WebDriver driver;
    private OffersWindowHandlingPage offersWindowHandlingPage;


    public OffersStepDefinitions(InitiateDriver initiateDriver, OffersWindowHandlingPage offersWindowHandlingPage) {
        this.initiateDriver = initiateDriver;
        this.offersWindowHandlingPage = offersWindowHandlingPage;
        this.driver = initiateDriver.getDriver();
    }

    @Given("The user has navigated to Offers section")
    public void the_user_has_navigated_to_offers_section() {
        offersWindowHandlingPage.navigateToOffers();

    }
    @Given("The user has clicked on Trains tab")
    public void the_user_has_clicked_on_tab() {
        String offerText = offersWindowHandlingPage.clickOnOffer();
        ExtentCucumberAdapter.addTestStepLog("Book Now has been clicked");
        initiateDriver.takeScreenshot("New Window opened for popup");
    }
    @When("The user clicks on an Book Now in Offer")
    public void the_user_clicks_on_an_book_now_in_offer() {
        offersWindowHandlingPage.handleNewTab();
    }
    @Then("The Offer has to load in a new tab")
    public void the_offer_has_to_load_in_a_new_tab() {
        ExtentCucumberAdapter.addTestStepLog("New Tab has been opened for Offers");
        initiateDriver.takeScreenshot("OffersTab");
    }
}
