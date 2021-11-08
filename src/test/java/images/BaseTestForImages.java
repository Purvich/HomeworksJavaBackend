package images;

import imgur.Params;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTestForImages {
    @BeforeAll()
    static void beforeAll() {
        RestAssured.baseURI = Params.BASE_URL + Params.IMAGE;
    }
}
