package albums.Scenario;

import imgur.Params;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@DisplayName("Создание альбома и удаление альбома")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EndToEndTest {

    @DisplayName("Создание альбома")
    @Test
    @Order(1)
    void createAlbum() {
        RestAssured.baseURI = Params.BASE_URL + Params.ALBUM;
        System.out.println("1) Создание альбома");
        deleteHashAlbum = given()
                .auth()
                .oauth2(Params.TOKEN)
                .formParam("title", Params.ALBUM_TITLE)
                .expect()
                .statusCode(200)
                .when()
                .post(Params.BASE_URL + Params.ALBUM)
                .then()
                .extract()
                .jsonPath()
                .getString("data.deletehash");
    }

    static String deleteHashAlbum;

    @DisplayName("Удаление альбома")
    @Test
    @Order(2)
    void deleteAlbum() {
        RestAssured.baseURI = Params.BASE_URL + Params.ALBUM + deleteHashAlbum;
        System.out.println("2) Удаление альбома");
        given()
                .auth()
                .oauth2(Params.TOKEN)
                .formParam("title", Params.ALBUM_TITLE)
                .expect()
                .statusCode(200)
                .body("data", is(true))
                .body("success", is(true))
                .when()
                .delete(Params.BASE_URL + Params.ALBUM + deleteHashAlbum)
                .prettyPrint();
    }
}
