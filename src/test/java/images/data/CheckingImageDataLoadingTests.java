package images.data;

import images.BaseTestForImages;
import imgur.Params;
import imgur.images.ImagesResponse;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@DisplayName("Проверка загрузки данных изображения")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CheckingImageDataLoadingTests extends BaseTestForImages {

    String urlImageJpeg = "https://www.pergaminovirtual.com.ar/como/imagenes/57-java-thumb.jpg";

    @DisplayName("Без данных")
    @Test
    @Order(1)
    void withoutData() {
        ImagesResponse imagesWithoutData =
                given()
                        .formParam("image", urlImageJpeg)
                        .when()
                        .post(Params.BASE_URL + Params.IMAGE)
                        .as(ImagesResponse.class);
        assertThat(imagesWithoutData.getData().getTitle(), is(nullValue()));
        assertThat(imagesWithoutData.getData().getDescription(), is(nullValue()));
        assertThat(imagesWithoutData.getData().getName(), is(""));
    }

    @DisplayName("С названием")
    @Test
    @Order(2)
    void withTitle() {
        ImagesResponse imagesWithTitle =
                given()
                        .formParam("image", urlImageJpeg)
                        .formParam("title", Params.IMAGE_TITLE)
                        .when()
                        .post(Params.BASE_URL + Params.IMAGE)
                        .as(ImagesResponse.class);
        assertThat(imagesWithTitle.getData().getTitle(), is(Params.IMAGE_TITLE));
        assertThat(imagesWithTitle.getData().getDescription(), is(nullValue()));
        assertThat(imagesWithTitle.getData().getName(), is(""));
    }

    @DisplayName("С описанием")
    @Test
    @Order(3)
    void withDescription() {
        ImagesResponse imagesWithDescription =
                given()
                        .formParam("image", urlImageJpeg)
                        .formParam("description", Params.IMAGE_DESCRIPTION)
                        .when()
                        .post(Params.BASE_URL + Params.IMAGE)
                        .as(ImagesResponse.class);
        assertThat(imagesWithDescription.getData().getTitle(), is(nullValue()));
        assertThat(imagesWithDescription.getData().getDescription(), is(Params.IMAGE_DESCRIPTION));
        assertThat(imagesWithDescription.getData().getName(), is(""));
    }

    @DisplayName("С именем")
    @Test
    @Order(4)
    void withName() {
        ImagesResponse imagesWithName =
                given()
                        .formParam("image", urlImageJpeg)
                        .formParam("name", Params.IMAGE_NAME)
                        .when()
                        .post(Params.BASE_URL + Params.IMAGE)
                        .as(ImagesResponse.class);
        assertThat(imagesWithName.getData().getTitle(), is(nullValue()));
        assertThat(imagesWithName.getData().getDescription(), is(nullValue()));
        assertThat(imagesWithName.getData().getName(), is(Params.IMAGE_NAME));
    }
}
