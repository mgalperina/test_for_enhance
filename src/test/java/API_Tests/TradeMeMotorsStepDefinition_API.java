package API_Tests;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import kotlin.text.Regex;
import org.apache.http.auth.AuthenticationException;


public class TradeMeMotorsStepDefinition_API {
    private String oAuthTmpToken;
    private String oAuthTmpTokenSecret;
    private Boolean oAuthTmpCallbackConfirmed;
    private Response response;

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

        oAuthTmpToken = getValueOf("oauth_token", tokenStr);
        oAuthTmpTokenSecret = getValueOf("oauth_token_secret", tokenStr);
        oAuthTmpCallbackConfirmed = Boolean.parseBoolean(getValueOf("oauth_callback_confirmed", tokenStr));

    }

    @When("I query Used cars category")
    public void iQueryUsedCarCategory() throws AuthenticationException {

        this.response = RestAssuredTests_API.queryUsedCars(oAuthTmpToken, oAuthTmpTokenSecret);

    }

    @Then("At least one search result is returned")
    public void atLeastOneSearchResultIsReturned() throws AuthenticationException {

        RestAssuredTests_API.testReturnsResults(response);

    }


    @When("I query Used cars category search")
    public void iQueryUsedCarsCategorySearch() throws AuthenticationException {

        this.response = RestAssuredTests_API.queryUsedCarsCategory(oAuthTmpToken, oAuthTmpTokenSecret);

    }

    @Then("I can see Kia make is returned")
    public void iCanSeeKiaMakeIsReturned() throws AuthenticationException {

        RestAssuredTests_API.testReturnsKia(response);

    }

    @When("I query a listing in Used cars category")
    public void iQueryAListingInUsedCarsCategory()  throws AuthenticationException {

        this.response = RestAssuredTests_API.queryListingAttributes(oAuthTmpToken, oAuthTmpTokenSecret);

    }

    @Then("The attributes are returned")
    public void theAttributesAreReturned()throws AuthenticationException {

        RestAssuredTests_API.testListingReturnsAttributes(response);

    }


}
