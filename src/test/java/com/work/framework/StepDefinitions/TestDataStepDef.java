package com.work.framework.StepDefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
//import com.fasterxml.jackson.core.type.TypeReference;
import com.work.framework.InitiateDriver;
import com.work.framework.Pages.OrangeHRMTestData;
import com.work.framework.UserData;
import io.cucumber.core.internal.com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.core.internal.com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class TestDataStepDef {

    private InitiateDriver initiateDriver;
    private WebDriver driver;
    private OrangeHRMTestData orangeHRMTestData;

    public TestDataStepDef(InitiateDriver initiateDriver, OrangeHRMTestData orangeHRMTestData) {
        this.initiateDriver = initiateDriver;
        this.orangeHRMTestData = orangeHRMTestData;
        this.driver = initiateDriver.getDriver();
    }


    @Given("User is logged in to OrangeHRM portal")
    public void user_is_logged_in_to_orange_hrm_portal() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("Navigating to URL: https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        ExtentCucumberAdapter.addTestStepLog("--- Navigating to URL: https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        orangeHRMTestData.enterCredentials();

    }
    @When("The user has navigated to Admin tab")
    public void the_user_has_navigated_to_admin_tab() {
        orangeHRMTestData.clickAdminTab();
        ExtentCucumberAdapter.addTestStepLog("--- Navigating to PIM Tab");
        initiateDriver.takeScreenshot("PIM Tab");
    }
    @Given("The user clicked on Add Users button")
    public void the_user_clicked_on_add_users_button() {
        orangeHRMTestData.clickAddUserIcon();
        ExtentCucumberAdapter.addTestStepLog("--- Navigating to Add Employee Page");
        initiateDriver.takeScreenshot("Add Employee Page");
    }
    @When("The Add User page is loaded")
    public void the_add_user_page_is_loaded() {
        String title = orangeHRMTestData.verifyAddUserPage();
        ExtentCucumberAdapter.addTestStepLog("--- User is at Add Employee Page");
        initiateDriver.takeScreenshot(title);
    }
    @When("User has to enter the details {string} {string} {string} {string}")
    public void user_has_to_enter(String string, String string2, String string3, String string4) throws IOException, InterruptedException {
        ObjectMapper objectMapper = new ObjectMapper();
        File jsonFile = new File("src/test/resources/testdata/testdata.json");
        //UserData userProfile = objectMapper.readValue(jsonFile, UserData.class);
        List<UserData> userProfileList = objectMapper.readValue(jsonFile, new TypeReference<List<UserData>>() {});
        for (UserData userProfile : userProfileList) {
            // Your test automation code to enter the details for the current user
            System.out.println("Entering details for user: " + userProfile.getFirstname());
            orangeHRMTestData.enterEmployeeInfo(userProfile.getFirstname(), userProfile.getLastname(), userProfile.getMiddlename(), userProfile.getEmpid());
            Thread.sleep(2000);
        }
        //orangeHRMTestData.enterEmployeeInfo(UserData.getFirstname(), userProfile.getLastname(), userProfile.getMiddlename(), userProfile.getEmpid());
    }
    @Then("User has to click on Save button")
    public void user_has_to_click_on_save_button() {
        orangeHRMTestData.clickSaveIcon();
        ExtentCucumberAdapter.addTestStepLog("--- Employee Added");
        initiateDriver.takeScreenshot("Employee Added");
    }
}
