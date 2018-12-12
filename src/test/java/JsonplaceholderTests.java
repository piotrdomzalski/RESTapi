import io.restassured.http.ContentType;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;

public class JsonplaceholderTests {

    @Test
    public void IsThereHundredRecords() {
        given()
                .header("Content-Type", "application/json");
        when()
                .get("https://jsonplaceholder.typicode.com/posts?100").
                then()
                .contentType(ContentType.JSON)
                .statusCode(200).log().all()
                .body("id", hasSize(100));
    }

    @Test
    public void EncodingSetToGzip() {
        given()
                .header("Content-Type", "application/json").
                when()
                .get("https://jsonplaceholder.typicode.com/users").
                then()
                .header("Content-Encoding", "gzip")
                .statusCode(200).log().all()
                .contentType(ContentType.JSON);
    }

    @Test
    public void userCanSeePosts() {
        given()
                .header("Content-Type", "application/json");
        when()
                .get("https://jsonplaceholder.typicode.com/posts?userId=2").
                then()
                .statusCode(200).log().all();
    }

    @Test
    public void IsThereUserWithGivenZipCode() {
        given()
                .header("Content-Type", "application/json").
                when()
                .get("https://jsonplaceholder.typicode.com/users/6").
                then()
                .contentType(ContentType.JSON)
                .statusCode(200).log().all()
                .body("address.zipcode", equalTo("23505-1337"));
    }

    @Test
    public void CanUserSeePostComments() {
        given()
                .header("Content-Type", "application/json").
                when()
                .get("https://jsonplaceholder.typicode.com/posts/1/comments").
                then()
                .statusCode(200).log().all();

    }

}