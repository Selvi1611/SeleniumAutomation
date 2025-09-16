package com.work.framework.StepDefinitions;

import com.work.framework.InitiateDriver;
import com.work.framework.Pages.BusBookingPage;
import com.work.framework.Pages.HomePage;
import com.work.framework.Pages.HotelBookingPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.testng.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

public class StepDef {

    private InitiateDriver initiateDriver;
    private HomePage homePage;
    private WebDriver driver;
    private HotelBookingPage hotelBookingPage;
    private BusBookingPage busBookingPage;

    // The constructor will get the instances from PicoContainer
    public StepDef(InitiateDriver initiateDriver, HomePage homePage, HotelBookingPage hotelBookingPage, BusBookingPage busBookingPage) {
        this.initiateDriver = initiateDriver;
        this.homePage = homePage;
        this.hotelBookingPage = hotelBookingPage;
        this.busBookingPage = busBookingPage;

        this.driver = initiateDriver.getDriver();
    }


    @Given("The browser is launched with {string}")
    public void the_browser_launched_with_url(String url) {

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Navigating to URL: " + url);
        driver.get(url);
    }

    @When("The Account Creation page is loaded")
    public void account_creation_page_loaded() throws IOException {
        //WebElement mobileNumberField = driver.findElement(By.xpath("//input[@data-cy='userName']"));
        if(homePage.validateMobileNumberField()){
            File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(src, new File("AccountCreation.png"));
        }
        System.out.println("User is able to view Account Creation page");
        homePage.closePopup();
    }

    @Then("Validate the header of the page")
    public void validate_header_of_page() {
        Assert.assertTrue(driver.getTitle().contains("MakeMyTrip"));
    }

    @And("User has navigated to {string} tab")
    public void user_has_navigated_to_tab(String string) throws IOException {
        homePage.closePopup();
        hotelBookingPage.clickTabOption(string);
        initiateDriver.takeScreenshot("Tab");
        System.out.println("User has navigated to Hotels Page");
    }
    @When("The User enters {string} as City")
    public void the_user_enters_as_city(String string) {

        hotelBookingPage.selectCity(string);
    }
    @Then("Click on Search Button")
    public void click_on_search_button() throws InterruptedException {
        hotelBookingPage.selectCheckINOUTDate();
        initiateDriver.takeScreenshot("CheckIn_Checkout Date");
        hotelBookingPage.clickApplyAndSearchBtn();
    }

    @When("The user enters {string} location and {string} location")
    public void the_user_enters_location_and_location(String string, String string2) {
        busBookingPage.fromCitySelection(string);
        busBookingPage.ToCitySelection(string2);
        initiateDriver.takeScreenshot("Bus Booking From_To");
        busBookingPage.travelDate();
    }
    @When("I click on Search")
    public void i_click_on_search() {

        busBookingPage.clickSearch();
    }

    @Then("User should be able to view the list of available buses")
    public void user_should_be_able_to_view_the_list_of_available_buses() {
        initiateDriver.takeScreenshot("Search Results");
        busBookingPage.searchResultsValidation();
    }

}
