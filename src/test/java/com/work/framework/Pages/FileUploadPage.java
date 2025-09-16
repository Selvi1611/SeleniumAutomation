package com.work.framework.Pages;

import com.work.framework.InitiateDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FileUploadPage {

    private By UpldDwnldBtn = By.xpath("//span[text()='Upload and Download']");
    private By title = By.xpath("//h1[text()='Upload and Download']");
    private By chooseFile = By.id("uploadFile");
    private By filePath = By.id("uploadedFilePath");

    private WebDriver driver;
    private WebDriverWait wait;

    public FileUploadPage(InitiateDriver initiateDriver) {
        this.driver = initiateDriver.getDriver();
        this.wait = initiateDriver.webDriverWait();
    }

    public void clickUploadFunction(){
        WebElement element = driver.findElement(UpldDwnldBtn);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    public void clickChooseFile(){
        wait.until(ExpectedConditions.presenceOfElementLocated(title));
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("window.scrollTo(0, 500);");
        wait.until(ExpectedConditions.presenceOfElementLocated(title));
        WebElement titleElement = driver.findElement(title);
        wait.until(ExpectedConditions.presenceOfElementLocated(chooseFile));

    }

    public void enterPath() throws InterruptedException {
        WebElement chooseFileBtn = driver.findElement(chooseFile);
        chooseFileBtn.sendKeys("C:\\Users\\muthuselvi.rajapandi\\Desktop\\UploadExample.txt");
        //wait.until(ExpectedConditions.)
        Thread.sleep(10000);
    }

    public String getUploadedPath(){
        WebElement path = driver.findElement(filePath);
        String pathText = "";
        if(path.isDisplayed()){
            pathText = path.getText();
            System.out.println("File is uploaded");
        }
        else{
            System.out.println("File is not uploaded");
        }
        return pathText;
    }
}
