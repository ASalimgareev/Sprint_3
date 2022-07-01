import io.restassured.RestAssured;
import org.junit.Before;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

import java.io.File;

import static io.restassured.RestAssured.given;

public class createOrder {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    public void createBlackScooter() {
        File json = new File("src/test/resources/colorBlack.json");

        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/courier")
                .then().statusCode(201);
    }

    @Test
    public void createGreyScooter() {
        File json = new File("src/test/resources/colorGrey.json");

        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/courier")
                .then().statusCode(201);
    }

    @Test
    public void createBlackGreyScooter() {
        File json = new File("src/test/resources/colorBlackGrey.json");

        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/courier")
                .then().statusCode(201);
    }

    @Test
    public void createScooterWithoutColor() {
        File json = new File("src/test/resources/colorNull.json");

        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/courier")
                .then().statusCode(201);
    }

    @Test
    public void createScooterResponseHaveTrack() {
        File json = new File("src/test/resources/colorNull.json");

        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/courier")
                .then().assertThat().body("track", notNullValue());
    }


}