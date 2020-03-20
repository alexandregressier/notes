package dev.gresier.quarkus.sandbox;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.HttpHeaders;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.lessThan;

@QuarkusTest
public class GreetingRestTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello!"));
    }

    @Test
    public void testHelloEndpointContentLength() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello!"))
                .and().header(HttpHeaders.CONTENT_LENGTH, "6");
    }

    @Test
    public void testHelloEndpointNamePathParam() {
        given()
                .pathParam("name", "John")
                .when().get("/hello/{name}")
                .then()
                .statusCode(200)
                .body(is("Hello John!"));
    }

    @Test
    public void testHelloEndpointTiming() {
        given()
                .pathParam("name", "John")
                .when().get("/hello/{name}")
                .then()
                .time(lessThan(1000L));
    }
}
