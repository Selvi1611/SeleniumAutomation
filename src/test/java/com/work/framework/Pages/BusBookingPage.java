package com.work.framework.Pages;

import com.work.framework.InitiateDriver;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BusBookingPage {

    private By fromCity = By.id("fromCity");
    private By fromTextBox = By.xpath("//input[@title='From']");
    private By fromSelection = By.xpath("//span[contains(@class,'sr_city')][text()='Chennai, Tamil Nadu']");
    private By toCity = By.id("toCity");
    private By toTextBox = By.xpath("//input[@title='To']");
    private By toSelection = By.xpath("//span[contains(@class,'sr_city')][text()='Bangalore, Karnataka']");
    private By searchBtn = By.xpath("//*[text()='Search']");
    private By resultsPage = By.xpath("//span[text()='Filters']");

    private WebDriver driver;
    private WebDriverWait wait;

    public BusBookingPage(InitiateDriver initiateDriver) {
        this.driver = initiateDriver.getDriver();
        this.wait = initiateDriver.webDriverWait();
    }

    public void fromCitySelection(String from) {
        WebElement fromCityElement = driver.findElement(fromCity);
        wait.until(ExpectedConditions.visibilityOf(fromCityElement));
        fromCityElement.click();
        WebElement cityTextElement = driver.findElement(fromTextBox);
        if(cityTextElement.isDisplayed()){
            cityTextElement.sendKeys(from);
            WebElement citySelectElement = driver.findElement(fromSelection);
            wait.until(ExpectedConditions.visibilityOf(citySelectElement));
            citySelectElement.click();
        }
    }

    public void ToCitySelection(String To) {
//        WebElement toCityElement = driver.findElement(toCity);
//        wait.until(ExpectedConditions.visibilityOf(toCityElement));
//        toCityElement.click();
        WebElement toCityTextElement = driver.findElement(toTextBox);
        if(toCityTextElement.isDisplayed()){
            toCityTextElement.sendKeys(To);
            WebElement toCitySelectElement = driver.findElement(toSelection);
            wait.until(ExpectedConditions.visibilityOf(toCitySelectElement));
            toCitySelectElement.click();
        }
    }

    public void clickSearch(){
        WebElement searchElement = driver.findElement(searchBtn);
        searchElement.click();
    }

    public void travelDate(){
        String travelDateSelect = "7";
        String travelDateXPath = String.format("//div[@class='DayPicker-Day'][text()='%s']", travelDateSelect);

        WebElement travel = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(travelDateXPath)));
        travel.click();
    }

    public void searchResultsValidation(){
        //wait.until(ExpectedConditions.visibilityOfElementLocated(resultsPage));
        WebElement searchRslt = driver.findElement(resultsPage);
        if (searchRslt.isDisplayed()){
            Assert.assertTrue(true,"User has navigated to Search Results Page");
        }
        else{
            Assert.assertFalse(false,"User is not navigated to Search Results Page");
        }

    }

}
