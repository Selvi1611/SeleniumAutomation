package com.work.framework.Pages;

import com.work.framework.InitiateDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SelectMenuPage {

    private By pageTitle = By.xpath("//h1[text()='Select Menu']");
    private By dropdown1 = By.id("withOptGroup");
    private By dropdown2 = By.id("selectOne");
    private By dropdown3 = By.id("oldSelectMenu");
    private By dropdown1Option = By.xpath("//div[contains(text(),'%s')]");

    private WebDriver driver;
    private WebDriverWait wait;

    public SelectMenuPage(InitiateDriver initiateDriver) {
        this.driver = initiateDriver.getDriver();
        this.wait = initiateDriver.webDriverWait();
    }

    public String getTitle(String titleText){
        WebElement element = driver.findElement(pageTitle);
        String title="";
        wait.until(ExpectedConditions.visibilityOf(element));
        if(element.isEnabled()){
            title = element.getText();
            Assert.assertEquals(title,titleText,"Title is Equal");
        }
        else{
            Assert.assertFalse(false,"Title is not loaded");
            System.out.println("Title is not loaded");
        }
        return title;
    }

    public void selectValueFromDropdown(String d1, String d2, String d3){
        WebElement drop1 = driver.findElement(dropdown1);
        WebElement drop2 = driver.findElement(dropdown2);
        WebElement drop3 = driver.findElement(dropdown3);
        //String dropOption1 = d.toLowerCase();
        drop1.click();

        String dropOption1 = String.format("//div[text()='%s']", d1);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dropOption1)));
        WebElement dropdownOption1 = driver.findElement(By.xpath(dropOption1));
        dropdownOption1.click();

        drop2.click();

        String dropOption2 = String.format("//div[text()='%s']", d2);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(dropOption2)));
        WebElement dropdownOption2 = driver.findElement(By.xpath(dropOption2));
        dropdownOption2.click();

        WebElement dropdownOption3 = driver.findElement(dropdown3);
        Select select = new Select(dropdownOption3);
        select.selectByVisibleText(d3);


    }
}
