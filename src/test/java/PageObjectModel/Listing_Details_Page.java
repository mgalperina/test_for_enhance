package PageObjectModel;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class Listing_Details_Page extends Page_Object{

    //I'm going  to verify the following attributes of a listing details page in the test
    // since some from the task are not displayed in that only one listing on trade me sandbox.

    @FindBy(xpath="//*[@id='AttributesDisplay_attributesSection']/ul/li[1]/div[1]/label")
    private WebElement kilometres;


    @FindBy(xpath="//*[@id='AttributesDisplay_attributesSection']/ul/li[2]/div[1]/label")
    private WebElement body_style;

    @FindBy(name="Fuel type")
    private WebElement fuel_type;

    @FindBy(name="Background check")
    private WebElement background_check;

    @FindBy(xpath="//*[@id='VehicleStarRatings_fuelEconomy_fuelEconomyRating']/div[1]/text()")
    private WebElement fuel_economy_info;

    @FindBy(name="Safety rating")
    private WebElement safety_rating;

    @FindBy(name="Description")
    private WebElement description;



    public Listing_Details_Page (WebDriver driver) {
        super(driver);
    }

    public boolean isKilometersDisplayed() {
        return kilometres.isDisplayed();
    }

    public boolean isBodyStyleDisplayed() {
        return body_style.isDisplayed();
    }

    public boolean isFuelTypeDisplayed() {
        return fuel_type.isDisplayed();
    }

    public boolean isBackgroundCheckDisplayed() {
        return background_check.isDisplayed();
    }

    public boolean isFuelEconomyInfoDisplayed() {
        return fuel_economy_info.isDisplayed();
    }

    public boolean isSafetyRatingDisplayed() {
        return safety_rating.isDisplayed();
    }

    public boolean isDescriptionDisplayed() {
        return description.isDisplayed();
    }





}
