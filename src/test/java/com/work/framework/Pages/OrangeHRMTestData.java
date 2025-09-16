package com.work.framework.Pages;

import com.work.framework.InitiateDriver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrangeHRMTestData {
    private By userName = By.name("username");
    private By password = By.name("password");
    private By loginBtn = By.xpath("//button[@type=\"submit\"]");
    private By PIMTab = By.xpath("//a[contains(@href,'viewPimModule')]");
    private By systemUserTitle = By.xpath("//h5[text()='Employee Information']");

    private By addUserIcon = By.xpath("//i[contains(@class, 'bi-plus')]");
    private By addUserTitle = By.xpath("//h6[text()='Add Employee']");

    private By firstName = By.name("firstName");
    private By lastName = By.name("lastName");
    private By middleName = By.name("middleName");
    private By empid = By.xpath("//label[text()='Employee Id']/../following-sibling::div/input");

    private By saveBtn = By.xpath("//button[@type=\"submit\"]");

    private WebDriver driver;
    private WebDriverWait wait;

    public OrangeHRMTestData(InitiateDriver initiateDriver) {
        this.driver = initiateDriver.getDriver();
        this.wait = initiateDriver.webDriverWait();
    }

    public void enterCredentials(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement userElement = driver.findElement(userName);
        WebElement passElement = driver.findElement(password);
        WebElement loginElement = driver.findElement(loginBtn);

        if(userElement.isDisplayed()){
            userElement.sendKeys("Admin");
            passElement.sendKeys(("admin123"));
            loginElement.click();
        }

    }

    public void clickAdminTab(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(PIMTab));
        WebElement adminTabElement = driver.findElement(PIMTab);
        adminTabElement.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(systemUserTitle));
    }

    public void clickAddUserIcon(){
        WebElement addElement = driver.findElement(addUserIcon);
        if (addElement.isEnabled()){
            addElement.click();
        }
    }

    public String verifyAddUserPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(addUserTitle));
        WebElement addTitle = driver.findElement(addUserTitle);
        String titleText = addTitle.getText();
        return titleText;
    }

    public void enterEmployeeInfo(String fname, String lname, String mname, String empid1){

        WebElement firstEle = driver.findElement(firstName);
        WebElement lastEle = driver.findElement(lastName);
        WebElement middleEle = driver.findElement(middleName);
        WebElement empEle = driver.findElement(empid);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        JavascriptExecutor js = (JavascriptExecutor)driver;
        //js.executeScript("arguments[0].value = '';", firstEle);
        //firstEle.sendKeys(Keys.BACK_SPACE);
        firstEle.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        firstEle.sendKeys(Keys.DELETE);
        firstEle.sendKeys(fname);
        lastEle.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        lastEle.sendKeys(Keys.DELETE);
        lastEle.sendKeys(lname);
        middleEle.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        middleEle.sendKeys(Keys.DELETE);
        middleEle.sendKeys(mname);
        empEle.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        empEle.sendKeys(Keys.DELETE);
        empEle.sendKeys(empid1);
    }

    public void clickSaveIcon(){
        WebElement saveElement = driver.findElement(saveBtn);
        saveElement.click();
    }
}
