package com.work.framework.Pages;

import com.work.framework.InitiateDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FrameMyraPage {
    private By myraText = By.xpath("//div[contains(text(),'Hi,')]");
    private By myraBot = By.id("myra-bot");
    private By chatWindow = By.xpath("//textarea[@placeholder=' Ask me anything']");
    private By chatSendIcon = By.xpath("//textarea[@placeholder=' Ask me anything']/../div");
    private WebDriver driver;
    private WebDriverWait wait;

    public FrameMyraPage(InitiateDriver initiateDriver) {
        this.driver = initiateDriver.getDriver();
        this.wait = initiateDriver.webDriverWait();
    }

    public void scrollToElement(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, 300);");
        wait.until(ExpectedConditions.visibilityOfElementLocated(myraBot));
        WebElement myraBotIcon = driver.findElement(myraBot);
        //myraBotIcon.click();
        js.executeScript("arguments[0].click();", myraBotIcon);
    }

    public void clickMyraIcon(){
        scrollToElement();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("travelPlex-assistive-app")));
        WebElement iframe = driver.findElement(By.id("travelPlex-assistive-app"));
        driver.switchTo().frame(iframe);

    }

    public void MyraWindowOpened(){
        WebElement myraIconElement = driver.findElement(myraText);
        if(myraIconElement.isDisplayed()){
            String welcomeText = myraIconElement.getText();
            System.out.println("--Myra --- :"+welcomeText);
        }

    }

    public void typeChatWindow(){
        WebElement myraChat = driver.findElement(chatWindow);
        WebElement myraSend = driver.findElement(chatSendIcon);
        myraChat.sendKeys("Hi Myra! Good morning");
        wait.until(ExpectedConditions.visibilityOf(myraSend));
        myraSend.click();
    }
}
