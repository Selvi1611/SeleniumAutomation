package com.work.framework.Pages;


import com.work.framework.InitiateDriver;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebDriver driver;

    public HomePage(InitiateDriver initiateDriver) {
        this.driver = initiateDriver.getDriver();
    }

    private By mobileNumberField = By.xpath("//input[@data-cy='userName']");
    private By closeModal = By.xpath("//span[@data-cy='closeModal']");

    public boolean validateMobileNumberField() {
        WebElement mobileNumField = driver.findElement(mobileNumberField);
        if(mobileNumField.isDisplayed()) {
            System.out.println("Account creation page is displayed");
            return true;
        }
            else{
                return false;
            }
        }

    public void closePopup() {
        WebElement closeIcon = driver.findElement(closeModal);
        closeIcon.click();
    }
}
