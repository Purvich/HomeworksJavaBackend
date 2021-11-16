package images.uploadingImages;

import images.BaseTestForImages;
import imgur.Params;
import imgur.images.ImagesResponse;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("Загрузка изображений формата GIF")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UploadingImagesGifTest extends BaseTestForImages {

    private final String urlSmallImageGif = "https://stihi.ru/pics/2021/02/18/2937.gif";
    private final String urlMediumImageGif = "https://cdn.dribbble.com/users/1888604/screenshots/4275417/bart800.gif";
    private final String urlLargeImageGif = "https://i.yapx.ru/GuYGg.gif";

    @DisplayName("Разрешение 320*240")
    @Test
    @Order(1)
    void downloadSmallGifImage() {
        ImagesResponse smallGifImage =
                given()
                        .formParam("image", urlSmallImageGif)
                        .when()
                        .post(Params.BASE_URL + Params.IMAGE)
                        .then()
                        .extract()
                        .as(ImagesResponse.class);
        assertThat(smallGifImage.getData().getType(), is("image/gif"));
        assertThat(smallGifImage.getData().getWidth(), is(320));
        assertThat(smallGifImage.getData().getHeight(), is(240));
    }

    @DisplayName("Разрешение 800*600")
    @Test
    @Order(2)
    void downloadMediumGifImage() {
        ImagesResponse mediumGifImage =
                given()
                        .formParam("image", urlMediumImageGif)
                        .post(Params.BASE_URL + Params.IMAGE)
                        .then()
                        .extract()
                        .as(ImagesResponse.class);
        assertThat(mediumGifImage.getData().getType(), is("image/gif"));
        assertThat(mediumGifImage.getData().getWidth(), is(800));
        assertThat(mediumGifImage.getData().getHeight(), is(600));
    }

    @DisplayName("Разрешение 1920*1080")
    @Test
    @Order(3)
    void downloadLargeGifImage() {
        ImagesResponse largeGifImage =
                given()
                        .formParam("image", urlLargeImageGif)
                        .when()
                        .post(Params.BASE_URL + Params.IMAGE)
                        .then()
                        .extract()
                        .as(ImagesResponse.class);
        assertThat(largeGifImage.getData().getType(), is("image/gif"));
        assertThat(largeGifImage.getData().getWidth(), is(1920));
        assertThat(largeGifImage.getData().getHeight(), is(1080));
    }
}
