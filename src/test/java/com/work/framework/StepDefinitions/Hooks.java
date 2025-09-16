package com.work.framework.StepDefinitions;

import com.work.framework.InitiateDriver;
import com.work.framework.Pages.AlertHandlingPage;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hooks {

    private InitiateDriver initiateDriver;
    private AlertStepDefinition alertPage;
    //private WebDriver driver;

    public Hooks(InitiateDriver initiateDriver) {
        this.initiateDriver = initiateDriver;
        //this.driver = initiateDriver.getDriver();
    }

    @Before
    public void setup(Scenario scenario) {
        System.out.println("Starting scenario: " + scenario.getName());
        initiateDriver.createDriver("firefox");
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {

        }
        System.out.println("Ending scenario: " + scenario.getName());
        initiateDriver.quitDriver();
    }
//    @AfterStep
//    public void attachScreenshots(Scenario scenario) throws IOException {
//        WebDriver driver = initiateDriver.getDriver();
//
//        if (alertPage.isRobotScreenshot) {
//            File robotScreenshot = new File("test-output/SparkReport/Screenshots.png");
//            if (robotScreenshot.exists()) {
//                byte[] fileContent = FileUtils.readFileToByteArray(robotScreenshot);
//                scenario.attach(fileContent, "image/png", "robot_screenshot");
//                // Delete after attaching so it's not reused
//                if (!robotScreenshot.delete()) {
//                    System.out.println("Warning: Robot screenshot file could not be deleted.");
//                }
//            } else {
//                System.out.println("Robot screenshot flag set, but file not found.");
//            }
//            //  Reset flag so next step doesn’t falsely use it
//            alertPage.isRobotScreenshot = false;
//            return;
//        }
//        // Attach WebDriver screenshot only if no alert is present
//        try {
//            driver.switchTo().alert();
//            System.out.println("Alert is present — skipping WebDriver screenshot.");
//        } catch (NoAlertPresentException e) {
//            try {
//                LocalDateTime now = LocalDateTime.now();
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
//                String timestamp = now.format(formatter);
//                String screenshotName = "failure_" + timestamp + ".png";
//
//                // Capture screenshot as bytes
//                byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
//
//                // Define destination file and write the bytes
//                String destinationPath = "test-output/screenshots/" + screenshotName;
//                File destinationFile = new File(destinationPath);
//                destinationFile.getParentFile().mkdirs();
//                FileUtils.writeByteArrayToFile(destinationFile, screenshotBytes);
//
//                // Attach to the Cucumber scenario
//                scenario.attach(screenshotBytes, "image/png", screenshotName);
//            } catch (Exception ex) {
//                System.out.println("Error capturing WebDriver screenshot: " + ex.getMessage());
//            }
//        }
//    }

    @AfterStep
    public void attachScreenshots(Scenario scenario) throws IOException {
        WebDriver driver = initiateDriver.getDriver();
        //  Check if Robot screenshot was expected (flag set in step definition)
        if (initiateDriver.isRobotScreenshot) {
            File robotScreenshot = new File("test-output/SparkReport/Screenshots.png");
            if (robotScreenshot.exists()) {
                byte[] fileContent = FileUtils.readFileToByteArray(robotScreenshot);
                scenario.attach(fileContent, "image/png", "robot_screenshot");
                // Delete after attaching so it's not reused
                if (!robotScreenshot.delete()) {
                    System.out.println("Warning: Robot screenshot file could not be deleted.");
                }
            } else {
                System.out.println("Robot screenshot flag set, but file not found.");
            }
            //  Reset flag so next step doesn’t falsely use it
            initiateDriver.isRobotScreenshot = false;
            return;
        }
        // Attach WebDriver screenshot only if no alert is present
        try {
            driver.switchTo().alert();
            System.out.println("Alert is present — skipping WebDriver screenshot.");
        } catch (NoAlertPresentException e) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
                LocalDateTime now = LocalDateTime.now();
                String timestamp = now.format(formatter); // e.g., "2025-09-03_17-41-51"
                String baseDir = "test-output/SparkReport/Screenshots/";
                String folderPath = baseDir + timestamp + "/";
//            File folder = new File(folderPath);
                File sourcePath = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                File destFile = new File(folderPath);
                FileUtils.copyFile(sourcePath, destFile);
                byte[] fileContent = FileUtils.readFileToByteArray(sourcePath);
                scenario.attach(fileContent, "image/png", "webdriver_screenshot");
            } catch (Exception ex) {
                System.out.println("Error capturing WebDriver screenshot: " + ex.getMessage());
            }
        }
    }
}