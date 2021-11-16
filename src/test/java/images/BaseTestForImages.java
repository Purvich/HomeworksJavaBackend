package images;

import imgur.Params;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;

import static org.hamcrest.Matchers.*;

public class BaseTestForImages {
    @BeforeAll()
    static void beforeAll() {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .addHeader("Authorization", Params.TOKEN)
                .build();

        ResponseSpecification positiveResponseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectBody("success", is(true))
                .expectBody("status", is(200))
                .build();

        RestAssured.baseURI = Params.BASE_URL + Params.IMAGE;
        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = positiveResponseSpecification;
    }
}
