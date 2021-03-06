package API_Tests;



import io.restassured.authentication.OAuthSignature;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.apache.http.auth.AuthenticationException;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class RestAssuredTests_API {

    static String consumerKey = "7398B39A465F4B8863B0FFA73B1763DC";
    static String consumerSecret = "8184D84AAF68A330EA3440EC48C352BA";

    static String requestTokenUrl = "https://secure.tmsandbox.co.nz/Oauth/RequestToken?scope=MyTradeMeRead";

    public static String requestToken() throws AuthenticationException {

        Response response = given()
                .auth()
                .oauth(consumerKey, consumerSecret, "", "", OAuthSignature.HEADER)
                .post(requestTokenUrl)
                .thenReturn();

        if(response.getStatusCode() != 200) {
            throw new AuthenticationException("Error trying to get OAuth token");

        }

        return response.body().prettyPrint();

    }

    public static String getHeader(String oAuthToken, String oAuthTokenSecret) {

        String prefix = "oauth_version=\"1.0\",oauth_signature_method=\"PLAINTEXT\"";

        return String.format(
                "OAuth %s,oauth_consumer_key=\"%s\",oauth_signature=\"%s&\",oauth_timestamp=\"%s\",oauth_nonce=\"%s\"",
                prefix,
                consumerKey,
                consumerSecret,
                (System.currentTimeMillis() / 1000),
                (Math.random() * 100000000));
    }

    public static Response queryUsedCars (String oAuthToken, String oAuthTokenSecret) throws AuthenticationException {

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", getHeader(oAuthToken, oAuthTokenSecret))
                .when()
                .get("https://api.tmsandbox.co.nz/v1/Search/Motors/Used.json");
    }

    public static void testReturnsResults (Response response) throws AuthenticationException {

        response.then()
                .assertThat()
                .statusCode(200)
                .body("TotalCount", greaterThanOrEqualTo(1));
    }

    public static Response queryUsedCarsCategory(String oAuthToken, String oAuthTokenSecret) throws AuthenticationException {

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", getHeader(oAuthToken, oAuthTokenSecret))
                .when()
                .get("https://api.tmsandbox.co.nz/v1/Categories/UsedCars.json");
    }

    public static void testReturnsKia (Response response) throws AuthenticationException {

        response.then()
                .statusCode(200)
                .body(containsString("Kia"));
    }

    public static Response queryListingAttributes(String oAuthToken, String oAuthTokenSecret) throws AuthenticationException {

        return given()
                .contentType(ContentType.JSON)
                .header("Authorization", getHeader(oAuthToken, oAuthTokenSecret))
                .when()
                .get("https://api.tmsandbox.co.nz/v1/Listings/2149189245.json");
    }

    public static void testListingReturnsAttributes (Response response) throws AuthenticationException {

        response.then()
                .statusCode(200)
                .using()
                .defaultParser(Parser.JSON)
                .assertThat()
                .body(containsString("on_road_costs"))
                .body(containsString("year"))
                .body(containsString("kilometres"))
                .body(containsString("fuel_type"))
                .body(containsString("fuel_economy_stars"));
    }
}
