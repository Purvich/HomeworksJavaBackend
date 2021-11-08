package albums.creatingAlbum;

import albums.BaseTestForAlbums;
import imgur.Params;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class AlbumCreationTest extends BaseTestForAlbums {
    @DisplayName("Создание нового альбома")
    @Test
    public void albumCreation() {
        given()
                .auth()
                .oauth2(Params.TOKEN)
                .formParam("title", Params.ALBUM_TITLE)
                .formParam("description", Params.ALBUM_DESCRIPTION)
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("status", is(200))
                .when()
                .post(Params.BASE_URL + Params.ALBUM)
                .prettyPrint();
    }
}
