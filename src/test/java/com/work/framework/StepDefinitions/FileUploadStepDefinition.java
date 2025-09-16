package com.work.framework.StepDefinitions;

import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.work.framework.InitiateDriver;
import com.work.framework.Pages.FileUploadPage;
import com.work.framework.Pages.FrameMyraPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

public class FileUploadStepDefinition {
    private InitiateDriver initiateDriver;
    private WebDriver driver;
    private FileUploadPage fileUploadPage;

    public FileUploadStepDefinition(InitiateDriver initiateDriver, FileUploadPage fileUploadPage) {
        this.initiateDriver = initiateDriver;
        this.fileUploadPage = fileUploadPage;
        this.driver = initiateDriver.getDriver();
    }

    @Given("The user has navigated to {string}")
    public void the_user_has_navigated_to(String string) {
        driver.get(string);
        ExtentCucumberAdapter.addTestStepLog("-----Navigated to File Upload/Download Site : "+string);
        initiateDriver.takeScreenshot("File Upload");

    }
    @When("The user has clicked on Choose File button")
    public void the_user_has_clicked_on_choose_file_button() {
        fileUploadPage.clickChooseFile();
        ExtentCucumberAdapter.addTestStepLog("-----Choose File button has been clicked");
        initiateDriver.takeScreenshot("Choose File");
    }
    @When("The path of the file has been entered")
    public void the_path_of_the_file_has_been_entered() throws InterruptedException {
        fileUploadPage.enterPath();
        ExtentCucumberAdapter.addTestStepLog("-----File Path has been entered");
        initiateDriver.takeScreenshot("Uploaded File");
    }
    @Then("Uploaded file path should be displayed on the UI")
    public void uploaded_file_path_should_be_displayed_on_the_ui() {
        String pathName = fileUploadPage.getUploadedPath();
        if(pathName.isEmpty()){
            ExtentCucumberAdapter.addTestStepLog("-----File is not uploaded in UI");
            initiateDriver.takeScreenshot("FileUpload Failure");
        }
        else {
            ExtentCucumberAdapter.addTestStepLog("-----File is uploaded in UI : "+pathName);
            initiateDriver.takeScreenshot("FileUpload Success");
        }
    }
}
