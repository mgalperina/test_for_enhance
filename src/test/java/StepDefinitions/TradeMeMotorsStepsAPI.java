package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.Is.is;

public class TradeMeMotorsStepsAPI {


    // 1. search results in used cars category are returned on the page - Verify response payload
    // 2. kia make is returned in used cars cat - Verify response payload
    // 3. check is those attr are returned by api - Verify response payload

    @Given("I perform GET operation for {string}")
    public void iPerformGETOperationFor(String arg0) {

    }

    @When("I query Used cars category")
    public void i_query_Used_cars_category() {

        APIWithRestAssuredTests.test_Used_Cars_Returns_Results();

    }

    @Then("At least one search result is returned")
    public void at_least_one_search_result_is_returned() {

    }


    @When("I query Used cars category search")
    public void iQueryUsedCarsCategorySearch() {
    }

    @Then("I can see Kia make is returned")
    public void iCanSeeKiaMakeIsReturned() {
    }

    @When("I query a listing in Used cars category")
    public void iQueryAListingInUsedCarsCategory() {
    }

    @Then("The attributes are returned")
    public void theAttributesAreReturned() {
    }


}
