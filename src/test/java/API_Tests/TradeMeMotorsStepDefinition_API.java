package API_Tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kotlin.text.Regex;
import org.apache.http.auth.AuthenticationException;


public class TradeMeMotorsStepDefinition_API {
    private String oAuthToken;
    private String oAuthTokenSecret;
    private Boolean oAuthCallbackConfirmed;

    public TradeMeMotorsStepDefinition_API() throws AuthenticationException {

       setupTempTokens();

   }

    private static String getValueOf(String key, String text) {
        return new Regex("(" + key + "=)([a-zA-Z0-9]+)")
                .find(text, 0)
                .getGroups()
                .get(2) // the value
                .getValue();
    }

    private void setupTempTokens() throws AuthenticationException {
        String tokenStr = RestAssuredTests_API.requestToken();

        oAuthToken = getValueOf("oauth_token", tokenStr);
        oAuthTokenSecret = getValueOf("oauth_token_secret", tokenStr);
        oAuthCallbackConfirmed = Boolean.parseBoolean(getValueOf("oauth_callback_confirmed", tokenStr));
    }

    @Given("I perform GET operation for {string}")
    public void iPerformGETOperationFor(String arg0) {

    }

    @When("I query Used cars category")
    public void i_query_Used_cars_category() throws AuthenticationException {

        RestAssuredTests_API.test_Used_Cars_Returns_Results(oAuthToken, oAuthTokenSecret);

    }

    @Then("At least one search result is returned")
    public void at_least_one_search_result_is_returned() {

    }


    @When("I query Used cars category search")
    public void iQueryUsedCarsCategorySearch() throws AuthenticationException {

        RestAssuredTests_API.test_Used_Cars_Returns_Kia_Make(oAuthToken, oAuthTokenSecret);
    }

    @Then("I can see Kia make is returned")
    public void iCanSeeKiaMakeIsReturned() {
    }

    @When("I query a listing in Used cars category")
    public void iQueryAListingInUsedCarsCategory()  throws AuthenticationException {

        RestAssuredTests_API.test_Listing_Returns_Attributes(oAuthToken, oAuthTokenSecret);
    }

    @Then("The attributes are returned")
    public void theAttributesAreReturned() {
    }


}
