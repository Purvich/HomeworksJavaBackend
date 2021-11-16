package albums.Scenario;

import albums.BaseTestForAlbums;
import imgur.Params;
import imgur.albums.AlbumsResponse;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@DisplayName("Создание альбома и удаление альбома")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EndToEndTest extends BaseTestForAlbums {

    @DisplayName("Создание альбома")
    @Test
    @Order(1)
    void createAlbum() {
        System.out.println("1) Создание альбома");

        AlbumsResponse response =
                given()
                        .formParam("title", Params.ALBUM_TITLE)
                        .when()
                        .post(Params.BASE_URL + Params.ALBUM)
                        .as(AlbumsResponse.class);
        assertThat(response.getStatus(), is(200));

        albumDeleteHash = response.getData().deletehash;
        albumHash = response.getData().id;
    }

    static String albumHash;
    static String albumDeleteHash;

    @DisplayName("Добавление описания к альбому")
    @Test
    @Order(2)
    void updateDescriptionAlbum() {
        RestAssured.baseURI = Params.BASE_URL + Params.ALBUM + albumHash;

        System.out.println("2) Добавление описания альбому");

        AlbumsResponse response =
                given()
                        .formParam("description", Params.ALBUM_DESCRIPTION)
                        .when()
                        .put(Params.BASE_URL + Params.ALBUM + albumHash)
                        .as(AlbumsResponse.class);
        assertThat(response.getStatus(), is(200));
    }

    @DisplayName("Удаление альбома")
    @Test
    @Order(4)
    void deleteAlbum() {
        RestAssured.baseURI = Params.BASE_URL + Params.ALBUM + albumDeleteHash;

        System.out.println("3) Удаление альбома");

        AlbumsResponse response =
                given()
                        .delete(Params.BASE_URL + Params.ALBUM + albumDeleteHash)
                        .as(AlbumsResponse.class);
        assertThat(response.getStatus(), is(200));
    }
}
