import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;

import io.qameta.allure.junit4.DisplayName;

public class CheckOrder {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    @DisplayName("Check response body contains orders ")
    public void createScooterResponseHaveTrack() {

        given()
                .header("Content-type", "application/json")
                .when()
                .get("/api/v1/orders")
                .then().assertThat().body("orders", notNullValue());
    }

}
