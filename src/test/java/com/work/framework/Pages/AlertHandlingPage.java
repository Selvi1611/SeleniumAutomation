package com.work.framework.Pages;

import com.work.framework.InitiateDriver;
import org.apache.poi.ss.formula.atp.Switch;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class AlertHandlingPage {

    private By title = By.xpath("//h1[text()='Dialog boxes']");
    String buttonAlert = "//button[text()='%s']";
    private By modalSave = By.xpath("//button[@class=\"btn btn-primary model-button\"]");


    private WebDriver driver;
    private WebDriverWait wait;

    public AlertHandlingPage(InitiateDriver initiateDriver) {
        this.driver = initiateDriver.getDriver();
        this.wait = initiateDriver.webDriverWait();
    }

    public String getTitle(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(title));
        WebElement titleElement = driver.findElement(title);
        return  titleElement.getText();
    }

    public void clickAlertButton(String btnName){
        String btnLocator = String.format(buttonAlert,btnName);
        WebElement element = driver.findElement(By.xpath(btnLocator));
        if(element.isEnabled()){
            element.click();
        }
        else{
            System.out.println("Button not available");
            Assert.assertFalse(false,"Button not available : "+btnName);
        }
    }


    public void handleAlert(String actionToPerform) {
        Alert alert = null;
        String alertText = "";

        try {
            alert = driver.switchTo().alert();
            alertText = alert.getText();
            System.out.println("Alert text: " + alertText);

            switch (actionToPerform) {
                case "accept":
                    alert.accept();
                    System.out.println("Alert accepted.");
                    break; // Use break to prevent fall-through

                case "dismiss":
                    alert.dismiss();
                    System.out.println("Alert dismissed.");
                    break; // Use break to prevent fall-through

                default:
                    System.out.println("Invalid action specified: " + actionToPerform);
                    break;
            }

        } catch (NoAlertPresentException e) {
            System.out.println("No alert is present. Performing another action instead.");
            wait.until(ExpectedConditions.presenceOfElementLocated(modalSave));
            WebElement element = driver.findElement(modalSave);
            if(element.isEnabled()){
                element.click();
            }
        }
    }
}
