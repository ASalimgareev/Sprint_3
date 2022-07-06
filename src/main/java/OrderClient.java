import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import java.io.File;

public class OrderClient {
    private final String pathname;

    public OrderClient(String pathname) {
        this.pathname = pathname;
    }

    @Step("Test scooter creation")
    public void testCreateScooter() {
        var json = new File(pathname);
        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/orders")
                .then().statusCode(201);
    }

    @Step("Test scooter creation response body have track")
    public void testCreateScooterResponse() {
        var json = new File(pathname);
        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/orders")
                .then().assertThat().body("track", notNullValue());
    }
}
