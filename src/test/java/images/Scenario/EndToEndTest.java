package images.Scenario;

import imgur.Params;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@DisplayName("Загрузка изображения, изменение данных и удаление.")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EndToEndTest {

    String urlImageJpeg = "https://www.pergaminovirtual.com.ar/como/imagenes/57-java-thumb.jpg";

    @DisplayName("Загрузка изображения")
    @Test
    @Order(1)
    void downloadImage() {
        RestAssured.baseURI = Params.BASE_URL + Params.IMAGE;
        idImage = given()
                .auth()
                .oauth2(Params.TOKEN)
                .formParam("image", urlImageJpeg)
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.title", is(nullValue()))
                .body("data.description", is(nullValue()))
                .body("data.name", is(""))
                .body("data.type", is("image/jpeg"))
                .when()
                .post(Params.BASE_URL + Params.IMAGE)
                .then()
                .extract()
                .jsonPath()
                .getString("data.id");
    }

    static String idImage;

    @DisplayName("Проверка, что изображение загружено")
    @Test
    @Order(2)
    void getImage() {
        RestAssured.baseURI = Params.BASE_URL + Params.IMAGE + idImage;
        given()
                .auth()
                .oauth2(Params.TOKEN)
                .expect()
                .statusCode(200)
                .body("data.id", is(idImage))
                .body("success", is(true))
                .when()
                .get()
                .prettyPrint();
    }

    @DisplayName("Обновление информации об изображении")
    @Test
    @Order(3)
    void updateInformation() {
        RestAssured.baseURI = Params.BASE_URL + Params.IMAGE + idImage;
        given()
                .auth()
                .oauth2(Params.TOKEN)
                .formParam("title", Params.IMAGE_TITLE)
                .formParam("description", Params.IMAGE_DESCRIPTION)
                .expect()
                .statusCode(200)
                .body("data", is(true))
                .body("success", is(true))
                .when()
                .post()
                .prettyPrint();
    }

    @DisplayName("Проверка информации")
    @Test
    @Order(4)
    void checkImageData() {
        RestAssured.baseURI = Params.BASE_URL + Params.IMAGE + idImage;
        given()
                .auth()
                .oauth2(Params.TOKEN)
                .expect()
                .statusCode(200)
                .body("data.title", is(Params.IMAGE_TITLE))
                .body("data.description", is(Params.IMAGE_DESCRIPTION))
                .when()
                .get()
                .prettyPrint();
    }

    @DisplayName("Удаление изображения")
    @Test
    @Order(5)
    void deleteImage() {
        RestAssured.baseURI = Params.BASE_URL + Params.IMAGE + idImage;
        given()
                .auth()
                .oauth2(Params.TOKEN)
                .expect()
                .statusCode(200)
                .when()
                .get()
                .prettyPrint();
    }
}
