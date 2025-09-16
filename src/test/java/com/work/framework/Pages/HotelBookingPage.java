package com.work.framework.Pages;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.work.framework.InitiateDriver;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelBookingPage {
    private By hotelLink = By.xpath("//a[contains(@href,'%s')]");
    private By cityField = By.id("city");
    private By cityTextBox = By.xpath("//input[contains(@placeholder,'Where do you want to stay')]");
    private By cityList = By.xpath("//ul[@role='listbox']/li//span/b[text()='Bangalore'][1]");
    private By checkIn = By.xpath("//p[@data-cy='checkInDate']/span");
    private By clickCheckInDate = By.xpath("//p[@data-cy='checkInDate']/span");
    private By roomApplyBtn = By.xpath("//button[text()='APPLY']");
    private By hotelSubmitBtn = By.xpath("//*[text()='Search']");//By.linkText("Search");
    private By landingTitle = By.xpath("//h1[contains(text(),'Properties in')]");

    private WebDriver driver;
    private WebDriverWait wait;


    public HotelBookingPage(InitiateDriver initiateDriver) {
        this.driver = initiateDriver.getDriver();
        this.wait = initiateDriver.webDriverWait();
    }

    public void clickTabOption(String tabName){
        String tab = tabName.toLowerCase();
        String tabSelection = String.format("//a[contains(@href,'%s')]", tab);
        WebElement hotelElement = driver.findElement(By.xpath(tabSelection));
        if(hotelElement.isDisplayed()){
            
            hotelElement.click();
        }
    }

    public void selectCity(String city){
        WebElement cityElement = driver.findElement(cityField);

        if(cityElement.isDisplayed()){
            cityElement.click();
            WebElement cityTextElement = driver.findElement(cityTextBox);
            wait.until(ExpectedConditions.visibilityOfElementLocated(cityTextBox));
            cityTextElement.sendKeys(city);
            ExtentCucumberAdapter.addTestStepLog("%s city has been selected....");

        }
        WebElement cityListElement = driver.findElement(cityList);
        cityListElement.click();
    }

    public void selectCheckINOUTDate() throws InterruptedException {
        WebElement checkInDate = driver.findElement(checkIn);
        WebElement checkInDateClick = driver.findElement(clickCheckInDate);
        //wait.until(ExpectedConditions.visibilityOfElementLocated(checkIn));
        //checkInDate.click();
        System.out.println("Check In Date........");
        wait.until(ExpectedConditions.visibilityOf(checkInDateClick));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkInDateClick);

        String dateToSelect = "27";
        String dateXPath = String.format("//div[@class='DayPicker-Day'][text()='%s']", dateToSelect);

        WebElement selectedDate = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dateXPath)));
        selectedDate.click();

        String checkOutSelect = "30";
        String checkOutDateXPath = String.format("//div[@class='DayPicker-Day'][text()='%s']", checkOutSelect);

        WebElement checkOut = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(checkOutDateXPath)));
        checkOut.click();

        Thread.sleep(1000);
    }

    public void clickApplyAndSearchBtn(){
        WebElement applyBtn = driver.findElement(roomApplyBtn);
        WebElement submitBtn = driver.findElement(hotelSubmitBtn);

        applyBtn.click();

        if(submitBtn.isDisplayed()){
            submitBtn.click();
            WebElement newTitle = driver.findElement(landingTitle);
            wait.until(ExpectedConditions.visibilityOf(newTitle));
            String title = newTitle.getText();
            System.out.println("Title is : "+title);
            if(!title.isEmpty()){
                Assert.assertTrue(true,"Search Results are displayed");
            }

        }
    }
}
