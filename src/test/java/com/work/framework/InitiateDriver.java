package com.work.framework;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InitiateDriver {

    private WebDriver driver;
    private WebDriverWait wait;
    public boolean isRobotScreenshot = true;

    // This method creates a new WebDriver instance based on the specified browser
    public WebDriver createDriver(String browser) {
        if (driver == null) {
            if ("firefox".equalsIgnoreCase(browser)) {
                System.setProperty("webdriver.gecko.driver", "C:\\Users\\muthuselvi.rajapandi\\Downloads\\geckodriver.exe"); // Update with your actual path
                FirefoxOptions options = new FirefoxOptions();
               String browserName =  options.getBrowserName();
               options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                //options.addArguments("--headless"); // Optional
                driver = new FirefoxDriver(options);
            } else if ("chrome".equalsIgnoreCase(browser)) {
                System.setProperty("webdriver.chrome.driver", "path/to/chromedriver.exe"); // Update with your actual path
                driver = new ChromeDriver();
            }

        }
        return driver;
    }

    public void setBrowserCapabilities(String browser){

    }

    public WebDriver getDriver() {
        return driver;
    }

    public WebDriverWait webDriverWait(){
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        return wait;
    }

    public void takeScreenshot(String screenshotName) {
        try {

        TakesScreenshot ts = (TakesScreenshot) driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String destination = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";
        File finalDestination = new File(destination);
        FileUtils.copyFile(source, finalDestination);
        System.out.println("Screenshot saved at: " + destination);

        } catch (IOException e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
        }
    }

    public void alertScreenshot(String screenshotName){

    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}