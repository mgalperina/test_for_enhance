package StepDefinitions;


import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;


public class APIWithRestAssuredTests {

    public static void test_Used_Cars_Returns_Results () {

//        given()
//                .contentType(ContentType.JSON).
//                auth().
//                oauth2("CC1FA4A2243A26427DE716D275DFF302").
//                when().
//                get("https://api.tmsandbox.co.nz/v1/Search/Motors/Used.json").
//                then().
//                body('Total count', greaterThanOrEqualTo(1));

        given()
                .contentType(ContentType.JSON)
                .auth()
                .oauth2("CC1FA4A2243A26427DE716D275DFF302")
                .when()
                .get("https://api.tmsandbox.co.nz/v1/Search/Motors/Used.json")
                .then()
                .body("'Total count'", is(1));


    }

//    int movieId = given().contentType("application/json")
//            .body(request)
//            .when()
//            .post(uri + "/movie")
//            .then()
//            .assertThat()
//            .statusCode(HttpStatus.CREATED.value())
//            .extract()
//            .path("id");
//    assertThat(movieId).isEqualTo(11);

    public static void test_Used_Cars_Returns_Kia_Make() {

        given()
                .contentType(ContentType.JSON).

                when()
                .get("https://api.tmsandbox.co.nz/v1/Search/Motors/Used.json").
                then().body("", is("Kia"));
    }

    public static void test_Listing_Returns_Attributes() {

        given()
                .contentType(ContentType.JSON).

                when()
                .get("https://api.tmsandbox.co.nz/v1/Listings/2149189245.json").
                then().body("Attributes",containsInAnyOrder("Kilometres", "Body style",
                "Fuel type", "Fuel economy stars", "On road costs", "Year"));

    }
}
