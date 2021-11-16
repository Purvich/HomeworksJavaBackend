package images.Scenario;

import imgur.Params;
import imgur.images.ImagesResponse;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DisplayName("Загрузка изображения, изменение данных и удаление.")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EndToEndTest {

    private final String urlImageJpeg = "https://www.pergaminovirtual.com.ar/como/imagenes/57-java-thumb.jpg";

    @DisplayName("Загрузка изображения")
    @Test
    @Order(1)
    void downloadImage() {
        RestAssured.baseURI = Params.BASE_URL + Params.IMAGE;

        ImagesResponse response =
                given()
                        .header("Authorization", Params.TOKEN)
                        .formParam("image", urlImageJpeg)
                        .when()
                        .post(Params.BASE_URL + Params.IMAGE)
                        .as(ImagesResponse.class);

        assertThat(response.getData().getTitle(), is(nullValue()));
        assertThat(response.getData().getDescription(), is(nullValue()));
        assertThat(response.getData().getName(), is(""));
        assertThat(response.getData().getType(), is("image/jpeg"));

        idImage = response.getData().getId();
    }

    static String idImage;
    private String urlImageId = Params.BASE_URL + Params.IMAGE + idImage;

    @DisplayName("Проверка, что изображение загружено")
    @Test
    @Order(2)
    void getImage() {
        RestAssured.baseURI = urlImageId;
                given()
                        .header("Authorization", Params.TOKEN)
                        .expect()
                        .body("data.id", is(idImage))
                        .when()
                        .get()
                        .prettyPrint();
    }

    @DisplayName("Обновление информации об изображении")
    @Test
    @Order(3)
    void updateInformation() {
        RestAssured.baseURI = urlImageId;
                given()
                        .header("Authorization", Params.TOKEN)
                        .formParam("title", Params.IMAGE_TITLE)
                        .formParam("description", Params.IMAGE_DESCRIPTION)
                        .expect()
                        .body("data", is(true))
                        .when()
                        .post()
                        .prettyPrint();
    }

    @DisplayName("Проверка информации")
    @Test
    @Order(4)
    void checkImageData() {
        RestAssured.baseURI = urlImageId;
                given()
                        .header("Authorization", Params.TOKEN)
                        .expect()
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
        RestAssured.baseURI = urlImageId;
        given()
                .header("Authorization", Params.TOKEN)
                .expect()
                .body("status", is(200))
                .when()
                .delete()
                .prettyPrint();
    }
}
