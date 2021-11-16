package albums.creatingAlbum;

import albums.BaseTestForAlbums;
import imgur.Params;
import imgur.albums.AlbumsResponse;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class AlbumCreationTest extends BaseTestForAlbums {
    @DisplayName("Создание нового альбома")
    @Test
    public void albumCreation() {
        AlbumsResponse response =
        given()
                .formParam("title", Params.ALBUM_TITLE)
                .formParam("description", Params.ALBUM_DESCRIPTION)
                .when()
                .post(Params.BASE_URL + Params.ALBUM)
                .as(AlbumsResponse.class);
        assertThat(response.getStatus(), is(200));
    }
}
