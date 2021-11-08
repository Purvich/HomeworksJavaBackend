package albums;

import imgur.Params;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static io.restassured.RestAssured.given;

public class BaseTestForAlbums {
    @BeforeAll()
    static void beforeAll() {
        RestAssured.baseURI = Params.BASE_URL + Params.ALBUM;
    }
}
