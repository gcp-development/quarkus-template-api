package org.microservice.api;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import javax.ws.rs.core.HttpHeaders;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class BookResourceTest {

    @Test
    @Order(1)
    public void testAddBookEndpoint() {
        final String testValue = "{\"id\": 0,\"title\":\"Moby Dick\",\"author\":\"Herman Melville\"}";

        given()
                .header(HttpHeaders.ACCEPT, "application/json")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(testValue)
                .when().post("/books")
                .then()
                .statusCode(201);
    }

    @Test
    @Order(2)
    public void testGetBookEndpoint() {
        final String insertValue = "{\"id\": 0,\"title\":\"Moby Dick\",\"author\":\"Herman Melville\"}";
        final int key = 1;
        final String testValue = "{\"id\":1,\"title\":\"Moby Dick\",\"author\":\"Herman Melville\"}";

        given()
                .header(HttpHeaders.ACCEPT, "application/json")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(insertValue)
                .when().post("/books")
                .then()
                .statusCode(201);

        given()
                .header(HttpHeaders.ACCEPT, "application/json")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .pathParam("key", key)
                .when().get("/books/{key}")
                .then()
                .statusCode(200)
                .body(is(testValue));
    }

    @Test
    @Order(3)
    public void testGetBooksEndpoint() {
        final String insertTestValue = "{\"id\": 0,\"title\":\"Moby Dick\",\"author\":\"Herman Melville\"}";
        final String testValue = "[{\"id\":1,\"title\":\"Moby Dick\",\"author\":\"Herman Melville\"}]";

        given()
                .header(HttpHeaders.ACCEPT, "application/json")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(insertTestValue)
                .when().post("/books")
                .then()
                .statusCode(201);

        given()
                .header(HttpHeaders.ACCEPT, "application/json")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .when().get("/books")
                .then()
                .statusCode(200)
                .body(is(testValue));
    }

    @Test
    @Order(4)
    public void testUpdateBookEndpoint() {
        final String insertTestValue = "{\"id\": 0,\"title\":\"Moby Dick\",\"author\":\"Herman Melville\"}";
        final int key = 1;
        final String updateValue = "{\"id\":1,\"title\":\"Title updated\",\"author\":\"Author updated\"}";

        given()
                .header(HttpHeaders.ACCEPT, "application/json")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(insertTestValue)
                .when().post("/books")
                .then()
                .statusCode(201);

        given()
                .header(HttpHeaders.ACCEPT, "application/json")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(updateValue)
                .pathParam("key", key)
                .when().put("/books/{key}")
                .then()
                .statusCode(204);
    }

    @Test
    @Order(5)
    public void testBulkBookEndpoint() {
        final String insertTestValue = "[{\"id\": 0,\"title\":\"Raven\",\"author\":\"John Jacobs\"},{\"id\": 0,\"title\":\"In Cold Blood\",\"author\":\"Truman Capote\"},{\"id\": 0,\"title\":\"Brave New World\",\"author\":\"Aldous Huxley\"},{\"id\": 0,\"title\":\"Nineteen Eighty-Four\",\"author\":\"George Orwell\"},{\"id\": 0,\"title\":\"The Art of War\",\"author\":\"Sun-Tzu\"}]";
        final int key = 2;

        given()
                .header(HttpHeaders.ACCEPT, "application/json")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(insertTestValue)
                .when().post("/books/bulk")
                .then()
                .statusCode(201);
    }

    @Test
    @Order(6)
    public void testDeleteBookEndpoint() {
        final String insertTestValue1 = "{\"id\": 0,\"title\":\"Moby Dick\",\"author\":\"Herman Melville\"}";
        final String insertTestValue2 = "{\"id\": 0,\"title\":\"Moby Dick\",\"author\":\"Herman Melville\"}";
        final int key = 2;

        given()
                .header(HttpHeaders.ACCEPT, "application/json")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(insertTestValue1)
                .when().post("/books")
                .then()
                .statusCode(201);

        given()
                .header(HttpHeaders.ACCEPT, "application/json")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(insertTestValue2)
                .when().post("/books")
                .then()
                .statusCode(201);

        given()
                .pathParam("key", key)
                .when().delete("/books/{key}")
                .then()
                .statusCode(204);
    }

    @Test
    @Order(7)
    public void testDeleteAllBookEndpoint() {
        final String insertTestValue = "{\"id\": 0,\"title\":\"Moby Dick\",\"author\":\"Herman Melville\"}";

        given()
                .header(HttpHeaders.ACCEPT, "application/json")
                .header(HttpHeaders.CONTENT_TYPE, "application/json")
                .body(insertTestValue)
                .when().post("/books")
                .then()
                .statusCode(201);

        given()
                .when().delete("/books")
                .then()
                .statusCode(204);
    }

    @Test
    @Order(8)
    public void testEnhancedMediaTypeVersion1Endpoint() {
        final String testValue = "{\"id\":99,\"title\":\"title-version1\",\"author\":\"author-version1\"}";

        given()
                .header(HttpHeaders.ACCEPT, "application/org.microservice.api.v1.book+json;qs=0.5")
                .header(HttpHeaders.CONTENT_TYPE, "application/org.microservice.api.v1.book+json;qs=0.5")
                .when().get("books/enhancedmediatype")
                .then()
                .statusCode(200)
                .body(is(testValue));
    }

    @Test
    @Order(9)
    public void testEnhancedMediaTypeVersion2Endpoint() {
        final String testValue = "{\"id\":99,\"title\":\"title-version2\",\"author\":\"author-version2\"}";

        given()
                .header(HttpHeaders.ACCEPT, "application/org.microservice.api.v2.book+json;qs=0.9")
                .header(HttpHeaders.CONTENT_TYPE, "application/org.microservice.api.v2.book+json;qs=0.9")
                .when().get("books/enhancedmediatype")
                .then()
                .statusCode(200)
                .body(is(testValue));
    }
}