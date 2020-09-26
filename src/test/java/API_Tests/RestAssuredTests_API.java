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

    public static void test_Used_Cars_Returns_Results (String oAuthToken, String oAuthTokenSecret) throws AuthenticationException {
       given()
                .contentType(ContentType.JSON)
                .header("Authorization", getHeader(oAuthToken, oAuthTokenSecret))
                .when()
                .get("https://api.tmsandbox.co.nz/v1/Search/Motors/Used.json")
                .then()
                .assertThat()
                .statusCode(200)
                .body("TotalCount", equalTo(1));

    }

    public static void test_Used_Cars_Returns_Kia_Make(String oAuthToken, String oAuthTokenSecret) throws AuthenticationException {

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", getHeader(oAuthToken, oAuthTokenSecret))
                .when()
                .get("https://api.tmsandbox.co.nz/v1/Categories/UsedCars.json")
                .then()
                .body(containsString("Kia"));

    }

    public static void test_Listing_Returns_Attributes(String oAuthToken, String oAuthTokenSecret) {

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", getHeader(oAuthToken, oAuthTokenSecret))
                .when()
                .get("https://api.tmsandbox.co.nz/v1/Listings/2149189245.json")
                .then()
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
