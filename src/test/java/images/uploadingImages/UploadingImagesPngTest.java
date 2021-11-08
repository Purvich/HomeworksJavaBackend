package images.uploadingImages;

import images.BaseTestForImages;
import imgur.Params;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@DisplayName("Загрузка изображений формата Jpeg")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UploadingImagesPngTest extends BaseTestForImages {

    String urlSmallImagePng = "https://xn----7sbb5fc.xn--p1ai/asset/img/geekbrains.png";
    String urlMediumImagePng = "https://berserkon.com/images/roadrunner-clipart-animated-6.png";
    String urlLargeImagePng =
            "https://static.miraheze.org/closinglogosgroupwiki/e/ea/Warner_Bros._Animation_Presents_%282008%29_1.png";

    @DisplayName("Разрешение 320*240")
    @Test
    @Order(1)
    void downloadSmallPngImage() {
        given()
                .when()
                .auth()
                .oauth2(Params.TOKEN)
                .formParam("image", urlSmallImagePng)
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.type", is("image/png"))
                .body("data.width", is(320))
                .body("data.height", is(240))
                .when()
                .post(Params.BASE_URL + Params.IMAGE)
                .prettyPrint();
    }

    @DisplayName("Разрешение 800*600")
    @Test
    @Order(2)
    void downloadMediumPngImage() {
        given()
                .when()
                .auth()
                .oauth2(Params.TOKEN)
                .formParam("image", urlMediumImagePng)
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.type", is("image/png"))
                .body("data.width", is(800))
                .body("data.height", is(600))
                .when()
                .post(Params.BASE_URL + Params.IMAGE)
                .prettyPrint();
    }

    @DisplayName("Разрешение 1920*1080")
    @Test
    @Order(3)
    void downloadLargePngImage() {
        given()
                .when()
                .auth()
                .oauth2(Params.TOKEN)
                .formParam("image", urlLargeImagePng)
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.type", is("image/png"))
                .body("data.width", is(1920))
                .body("data.height", is(1080))
                .when()
                .post(Params.BASE_URL + Params.IMAGE)
                .prettyPrint();
    }
}
