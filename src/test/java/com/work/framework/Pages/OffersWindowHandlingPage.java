package com.work.framework.Pages;

import com.work.framework.InitiateDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.Set;

public class OffersWindowHandlingPage {

    private By moveElement = By.xpath("//ul[@data-cy=\"tertiaryRowItemsContainer\"]");
    private By offersHeader = By.xpath("//h2/font[text()='Offers']");
    private By offersTrain = By.id("superOffersTab_RAILS");
    private By offerDiwali = By.xpath("//p[contains(text(),'Diwali Train')]");
    private By bookNow = By.xpath("//a[@data-cy=\"superOfferCtaText0\"]");
    private By newElement = By.xpath("//img[@alt=\"Banner Image\" and @class=\"dt\"]");

    private WebDriver driver;
    private WebDriverWait wait;

    public OffersWindowHandlingPage(InitiateDriver initiateDriver) {
        this.driver = initiateDriver.getDriver();
        this.wait = initiateDriver.webDriverWait();
    }


    public void navigateToOffers(){
        JavascriptExecutor  js = (JavascriptExecutor)driver;
        WebElement element = driver.findElement(moveElement);
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public String clickOnOffer(){
        String diwaliText = "";
        wait.until(ExpectedConditions.visibilityOfElementLocated(offersTrain));
        WebElement trainElement = driver.findElement(offersTrain);
        trainElement.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(offerDiwali));
        WebElement diwaliElement = driver.findElement(offerDiwali);
        if(diwaliElement.isDisplayed()){
            diwaliText = diwaliElement.getText();
            WebElement bookNowElement = driver.findElement(bookNow);
            bookNowElement.click();
        }
        return diwaliText;
    }

    public void handleNewTab(){
        Set<String> windowHandles = driver.getWindowHandles();
        String originalTabHandle = driver.getWindowHandle();

        for (String handle : windowHandles) {
            if (!handle.equals(originalTabHandle)) {
                driver.switchTo().window(handle);
                wait.until(ExpectedConditions.urlContains("https://www.makemytrip.com/promos/trains-booking-offer"));
                WebElement newWindowEle = driver.findElement(newElement);
                if(newWindowEle.isDisplayed()){
                    Assert.assertTrue(true,"New Tab has been opened");
                }
            }
        }
    }

}
