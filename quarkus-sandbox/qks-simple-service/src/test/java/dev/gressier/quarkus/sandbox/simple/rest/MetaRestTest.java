package dev.gressier.quarkus.sandbox.simple.rest;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusTest
class MetaRestTest {

    @Test
    void testHostnameEndpoint() {
        given()
                .when().get("/meta/hostname")
                .then()
                .statusCode(200);
    }
}
