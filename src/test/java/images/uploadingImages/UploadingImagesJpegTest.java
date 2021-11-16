package images.uploadingImages;

import images.BaseTestForImages;
import imgur.Params;
import imgur.images.ImagesResponse;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("Загрузка изображений формата Jpeg")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UploadingImagesJpegTest extends BaseTestForImages {

    private final String urlSmallImageJpeg = "https://www.pergaminovirtual.com.ar/como/imagenes/57-java-thumb.jpg";
    private final String urlMediumImageJpeg = "https://sttheme.com/demosd/examin1/wp-content/uploads/2019/02/7.jpg";
    private final String urlLargeImageJpeg = "https://toghr.com/wp-content/uploads/2019/08/1.jpg";

    @DisplayName("Разрешение 320*240")
    @Test
    @Order(1)
    void downloadSmallJpegImage() {
        ImagesResponse smallJpegImage =
                given()
                        .formParam("image", urlSmallImageJpeg)
                        .when()
                        .post(Params.BASE_URL + Params.IMAGE)
                        .as(ImagesResponse.class);
        assertThat(smallJpegImage.getData().getType(), is("image/jpeg"));
        assertThat(smallJpegImage.getData().getWidth(), is(320));
        assertThat(smallJpegImage.getData().getHeight(), is(240));
    }

    @DisplayName("Разрешение 800*600")
    @Test
    @Order(2)
    void downloadMediumJpegImage() {
        ImagesResponse mediumJpegImage =
                given()
                        .formParam("image", urlMediumImageJpeg)
                        .when()
                        .post(Params.BASE_URL + Params.IMAGE)
                        .as(ImagesResponse.class);
        assertThat(mediumJpegImage.getData().getType(), is("image/jpeg"));
        assertThat(mediumJpegImage.getData().getWidth(), is(800));
        assertThat(mediumJpegImage.getData().getHeight(), is(600));
    }

    @DisplayName("Разрешение 1920*1080")
    @Test
    @Order(3)
    void downloadLargeJpegImage() {
        ImagesResponse largeJpegImage =
                given()
                        .formParam("image", urlLargeImageJpeg)
                        .when()
                        .post(Params.BASE_URL + Params.IMAGE)
                        .as(ImagesResponse.class);
        assertThat(largeJpegImage.getData().getType(), is("image/jpeg"));
        assertThat(largeJpegImage.getData().getWidth(), is(1920));
        assertThat(largeJpegImage.getData().getHeight(), is(1080));
    }
}
