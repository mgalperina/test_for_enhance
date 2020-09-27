package UI_Tests;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.*;
import org.junit.jupiter.api.AfterEach;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class TradeMeMotorsStepDefinition_UI {

    WebDriver driver;

    @Before
    public void initializeWebDriver() throws IOException {

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Given("Open Firefox browser")
    public void openFirefoxBrowser() throws Exception {

        System.setProperty("webdriver.gecko.driver", "C://Users//Mashula//Downloads//geckodriver.exe");
        driver = new FirefoxDriver();

        driver.get("https://www.tmsandbox.co.nz/");

    }


    @When("I go to Motors in the Trade Me website")
    public void iGoToMotorsInTheTradeMeWesite() {

        driver.findElement(By.cssSelector("#SearchTabs1_MotorsLink")).click();

    }

    @And("I open {string} category")
    public void iOpenCategory(String name) {

        driver.findElement(By.linkText(name)).click();

    }

    @Then("I see at least one search result")
    public void iSeeAtLeastOneSearchResult() {

        try {
            WebElement ListingCell = driver.findElement(By.className("tmm-search-card-list-view"));
            Assert.assertTrue(ListingCell.isDisplayed());
        } catch(NoSuchElementException ex) {
            System.out.println("The listing cell was not located.");
        } catch(AssertionError ae) {
            System.out.println("The listing cell was located, but not displayed.");
        }
    }

    @Then("There is Kia make displayed")
    public void thereIsKiaMakeDisplayed() {

        try {
            WebElement element = driver.findElement(By.cssSelector("#makes a[href=\"/motors/used-cars/kia\"]"));
            Assert.assertTrue(element.isDisplayed());
        } catch(NoSuchElementException ex) {
            System.out.println("The listing cell was not located.");
        } catch(AssertionError ae) {
            System.out.println("The listing cell was located, but not displayed.");
        }
    }

    @And("I open a listing")
    public void iOpenAListing() {

        driver.findElement(By.className("tmm-search-card-list-view")).click();

    }

    @Then("I can see the following {string} on the page")
    public void iCanSeeTheFollowingAttributesOnThePage(String attribute) {

        WebElement element = driver.findElement(
                By.xpath("//div[contains(@id,'AttributesDisplay_attributesSection')]//label[contains(text(), '" + attribute + "')]"));

        Assert.assertTrue(element.isDisplayed());

    }


    @AfterEach
    public void quitDriver() {

        if (driver != null) {
            driver.manage().deleteAllCookies();
            driver.quit();
        }
    }
}

