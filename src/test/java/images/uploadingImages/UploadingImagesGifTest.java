package images.uploadingImages;

import images.BaseTestForImages;
import imgur.Params;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@DisplayName("Загрузка изображений формата GIF")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UploadingImagesGifTest extends BaseTestForImages {

    String urlSmallImageGif = "https://pa1.narvii.com/6896/bb0d82b70714dae1488cfc087be4f0ae7048dfe5_128.gif";
    String urlMediumImageGif = "https://cdn.dribbble.com/users/1888604/screenshots/4275417/bart800.gif";
    String urlLargeImageGif =
            "https://camo.derpicdn.net/f5c8a9c59b23211d279fc0cdab37fba9db49e5c4?url=https%3A%2F%2Fi" +
                    ".imgur.com%2F4ulrjry.gif%3Fnoredirect";

    @DisplayName("Разрешение 320*240")
    @Test
    @Order(1)
    void downloadSmallGifImage() {
        given()
                .when()
                .auth()
                .oauth2(Params.TOKEN)
                .formParam("image", urlSmallImageGif)
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.type", is("image/gif"))
                .body("data.width", is(320))
                .body("data.height", is(240))
                .when()
                .post(Params.BASE_URL + Params.IMAGE)
                .prettyPrint();
    }

    @DisplayName("Разрешение 800*600")
    @Test
    @Order(2)
    void downloadMediumGifImage() {
        given()
                .when()
                .auth()
                .oauth2(Params.TOKEN)
                .formParam("image", urlMediumImageGif)
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.type", is("image/gif"))
                .body("data.width", is(800))
                .body("data.height", is(600))
                .when()
                .post(Params.BASE_URL + Params.IMAGE)
                .prettyPrint();
    }

    @DisplayName("Разрешение 1920*1080")
    @Test
    @Order(3)
    void downloadLargeGifImage() {
        given()
                .when()
                .auth()
                .oauth2(Params.TOKEN)
                .formParam("image", urlLargeImageGif)
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.type", is("image/gif"))
                .body("data.width", is(1920))
                .body("data.height", is(1080))
                .when()
                .post(Params.BASE_URL + Params.IMAGE)
                .prettyPrint();
    }
}
