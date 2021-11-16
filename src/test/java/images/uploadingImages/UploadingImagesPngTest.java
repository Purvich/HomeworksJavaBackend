package images.uploadingImages;

import images.BaseTestForImages;
import imgur.Params;
import imgur.images.ImagesResponse;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("Загрузка изображений формата Png")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UploadingImagesPngTest extends BaseTestForImages {

    private final String urlSmallImagePng = "https://xn----7sbb5fc.xn--p1ai/asset/img/geekbrains.png";
    private final String urlMediumImagePng = "https://berserkon.com/images/roadrunner-clipart-animated-6.png";
    private final String urlLargeImagePng = "https://images6.alphacoders.com/895/thumb-1920-895062.png";

    @DisplayName("Разрешение 320*240")
    @Test
    @Order(1)
    void downloadSmallPngImage() {
        ImagesResponse smallPngImage =
                given()
                        .formParam("image", urlSmallImagePng)
                        .when()
                        .post(Params.BASE_URL + Params.IMAGE)
                        .as(ImagesResponse.class);
        assertThat(smallPngImage.getData().getType(), is("image/png"));
        assertThat(smallPngImage.getData().getWidth(), is(320));
        assertThat(smallPngImage.getData().getHeight(), is(240));
    }

    @DisplayName("Разрешение 800*600")
    @Test
    @Order(2)
    void downloadMediumPngImage() {
        ImagesResponse mediumPngImage =
                given()
                        .formParam("image", urlMediumImagePng)
                        .when()
                        .post(Params.BASE_URL + Params.IMAGE)
                        .as(ImagesResponse.class);
        assertThat(mediumPngImage.getData().getType(), is("image/png"));
        assertThat(mediumPngImage.getData().getWidth(), is(800));
        assertThat(mediumPngImage.getData().getHeight(), is(600));
    }

    @DisplayName("Разрешение 1920*1080")
    @Test
    @Order(3)
    void downloadLargePngImage() {
        ImagesResponse largePngImage =
                given()
                        .formParam("image", urlLargeImagePng)
                        .when()
                        .post(Params.BASE_URL + Params.IMAGE)
                        .as(ImagesResponse.class);
        assertThat(largePngImage.getData().getType(), is("image/png"));
        assertThat(largePngImage.getData().getWidth(), is(1920));
        assertThat(largePngImage.getData().getHeight(), is(1080));
    }
}
