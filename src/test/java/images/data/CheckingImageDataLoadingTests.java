package images.data;

import images.BaseTestForImages;
import imgur.Params;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@DisplayName("Проверка загрузки данных изображения")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CheckingImageDataLoadingTests extends BaseTestForImages {

    String urlImageJpeg = "https://www.pergaminovirtual.com.ar/como/imagenes/57-java-thumb.jpg";

    @DisplayName("Без данных")
    @Test
    @Order(1)
    void withoutData() {
        given()
                .when()
                .auth()
                .oauth2(Params.TOKEN)
                .formParam("image", urlImageJpeg)
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.title", is(nullValue()))
                .body("data.description", is(nullValue()))
                .body("data.name", is(""))
                .when()
                .post(Params.BASE_URL + Params.IMAGE)
                .prettyPrint();
    }

    @DisplayName("С названием")
    @Test
    @Order(2)
    void withTitle() {
        given()
                .when()
                .auth()
                .oauth2(Params.TOKEN)
                .formParam("image", urlImageJpeg)
                .formParam("title", Params.IMAGE_TITLE)
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.title", is(Params.IMAGE_TITLE))
                .body("data.description", is(nullValue()))
                .body("data.name", is(""))
                .when()
                .post(Params.BASE_URL + Params.IMAGE)
                .prettyPrint();
    }

    @DisplayName("С описанием")
    @Test
    @Order(3)
    void withDescription() {
        given()
                .when()
                .auth()
                .oauth2(Params.TOKEN)
                .formParam("image", urlImageJpeg)
                .formParam("description", Params.IMAGE_DESCRIPTION)
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.title", is(nullValue()))
                .body("data.description", is(Params.IMAGE_DESCRIPTION))
                .body("data.name", is(""))
                .when()
                .post(Params.BASE_URL + Params.IMAGE)
                .prettyPrint();
    }

    @DisplayName("С именем")
    @Test
    @Order(4)
    void withName() {
        given()
                .when()
                .auth()
                .oauth2(Params.TOKEN)
                .formParam("image", urlImageJpeg)
                .formParam("name", Params.IMAGE_NAME)
                .expect()
                .statusCode(200)
                .body("success", is(true))
                .body("data.title", is(nullValue()))
                .body("data.description", is(nullValue()))
                .body("data.name", is(Params.IMAGE_NAME))
                .when()
                .post(Params.BASE_URL + Params.IMAGE)
                .prettyPrint();
    }
}
