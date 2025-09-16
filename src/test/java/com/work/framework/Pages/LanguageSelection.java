package com.work.framework.Pages;

import com.work.framework.InitiateDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LanguageSelection {

    private By langMenu = By.xpath("//span[@data-cy='country-lang-switcher']");
    private By langMenuPopup = By.xpath("//div[@data-cy='country-currency-popup']");
    private By countryMenu = By.xpath("//div[@data-cy='country-dropdown']");
    private By countryMenuDropdown = By.xpath("//div[@data-testid='dropdown-list-country']");
    String countryMenuOptions = "//div[@data-testid='dropdown-list-country']/p//span[text()='%s']";
    String headerText= "//div[text()='%s']";
    private By applybtn = By.xpath("//button[@data-testid='country-lang-submit']");
    private By cntryShort = By.xpath("//span[text()='INR']");

    private By myBizMenu = By.xpath("//span[@data-cy='myBizText']");
    private By myBizPopup = By.className("mybizNudgeWrap");
    private By popupTitle = By.xpath("//div[@class=\"mybizNudgeWrap\"]//p");
    private By switchBtn = By.className("upgrade__btn");
    private By myBizAcct = By.className("mBizAccount");


    private WebDriver driver;
    private WebDriverWait wait;

    public LanguageSelection(InitiateDriver initiateDriver) {
        this.driver = initiateDriver.getDriver();
        this.wait = initiateDriver.webDriverWait();
    }
    public void clickCountryMenu(){
        WebElement element = driver.findElement(langMenu);
        if(element.isDisplayed()){
            element.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(langMenuPopup));
            WebElement popup = driver.findElement(langMenuPopup);
            Assert.assertTrue(true,"Country Currency Popup has been displayed");
        }
    }

    public void selectCountry(String country){
        WebElement countryMenuElement = driver.findElement(countryMenu);
        if(countryMenuElement.isDisplayed()){
            countryMenuElement.click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(countryMenuDropdown));
            String selectOption = String.format(countryMenuOptions,country);
            WebElement selectCountry = driver.findElement(By.xpath(selectOption));
            selectCountry.click();
        }

    }

    public Boolean validateHeaders(String header){
        String headerOption = String.format(headerText,header);
        if(driver.findElement(By.xpath(headerOption)).isDisplayed()){
            return true;
        }
        else{
            return false;
        }

    }

    public void clickApplyBtn(){
        WebElement applyButton = driver.findElement(applybtn);
        applyButton.click();
    }

    public String validateMenuChanges(){
        WebElement element = driver.findElement(cntryShort);
        return element.getText();
    }

    public void selectMyBizMenu(){
        WebElement myBizElement = driver.findElement(myBizMenu);
        Actions action = new Actions(driver);
        action.moveToElement(myBizElement).build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(myBizPopup));
        WebElement popupElement = driver.findElement(myBizPopup);
        if(popupElement.isDisplayed()){
            System.out.println("Popup is displayed");
        }

        String popupText = popupElement.getText();

    }

    public String validatePopup(){
        WebElement popupTit = driver.findElement(popupTitle);
        String popupTitleStr = popupTit.getText();
        return popupTitleStr;
    }

    public void clickSwitchToBizBtn(){
        WebElement element = driver.findElement(switchBtn);
        if(element.isEnabled()){
            element.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(myBizAcct));
            Assert.assertTrue(true,"Popup has been displayed to enter the credentials of MyBiz");
        }
    }
}
