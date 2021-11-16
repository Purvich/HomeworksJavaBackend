package albums;

import imgur.Params;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import static org.hamcrest.Matchers.is;

public class BaseTestForAlbums {
    @BeforeAll()
    static void beforeAll() {
        ResponseSpecification positiveResponseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("success", is(true))
                .expectBody("status", is(200))
                .build();

        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", Params.TOKEN)
                .build();

        RestAssured.baseURI = Params.BASE_URL + Params.ALBUM;
        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = positiveResponseSpecification;
    }
}
