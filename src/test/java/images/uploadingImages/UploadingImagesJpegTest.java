package images.uploadingImages;

import images.BaseTestForImages;
import imgur.Params;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@DisplayName("Загрузка изображений формата Jpeg")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UploadingImagesJpegTest extends BaseTestForImages {

    String urlSmallImageJpeg = "https://www.pergaminovirtual.com.ar/como/imagenes/57-java-thumb.jpg";
    String urlMediumImageJpeg = "https://sttheme.com/demosd/examin1/wp-content/uploads/2019/02/7.jpg";
    String urlLargeImageJpeg = "https://toghr.com/wp-content/uploads/2019/08/1.jpg";

    @DisplayName("Разрешение 320*240")
    @Test
    @Order(1)
    void downloadSmallJpegImage() {
        given()
                .when()
                .auth()
                .oauth2(Params.TOKEN)
                .formParam("image", urlSmallImageJpeg)
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.type", is("image/jpeg"))
                .body("data.width", is(320))
                .body("data.height", is(240))
                .when()
                .post(Params.BASE_URL + Params.IMAGE)
                .prettyPrint();
    }

    @DisplayName("Разрешение 800*600")
    @Test
    @Order(2)
    void downloadMediumJpegImage() {
        given()
                .when()
                .auth()
                .oauth2(Params.TOKEN)
                .formParam("image", urlMediumImageJpeg)
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.type", is("image/jpeg"))
                .body("data.width", is(800))
                .body("data.height", is(600))
                .when()
                .post(Params.BASE_URL + Params.IMAGE)
                .prettyPrint();
    }

    @DisplayName("Разрешение 1920*1080")
    @Test
    @Order(3)
    void downloadLargeJpegImage() {
        given()
                .when()
                .auth()
                .oauth2(Params.TOKEN)
                .formParam("image", urlLargeImageJpeg)
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.type", is("image/jpeg"))
                .body("data.width", is(1920))
                .body("data.height", is(1080))
                .when()
                .post(Params.BASE_URL + Params.IMAGE)
                .prettyPrint();
    }
}
