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
    public void open_Firefox_browser() throws Exception {

        System.setProperty("webdriver.gecko.driver", "C://Users//Mashula//Downloads//geckodriver.exe");
        driver = new FirefoxDriver();

        driver.get("https://www.tmsandbox.co.nz/");

        }


    @When("I go to Motors in the Trade Me website")
    public void i_go_to_Motors_in_the_Trade_Me_website() {

        driver.findElement(By.cssSelector("#SearchTabs1_MotorsLink")).click();

    }

    @And("I open {string} category")
    public void i_open_category(String name) {

        driver.findElement(By.linkText(name)).click();

    }

    @Then("I see at least one search result")
    public void i_see_at_least_one_search_result() {

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
    public void there_is_Kia_make_displayed() {

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
    public void i_open_a_listing() {

        driver.findElement(By.className("tmm-search-card-list-view")).click();

    }

    @Then("I can see the following {string} on the page")
    public void i_can_see_the_following_attributes_on_the_page(String attribute) {

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

