import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.*;
import io.qameta.allure.junit4.DisplayName;
import java.io.File;

import static io.restassured.RestAssured.given;

public class authCourier {
    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";

        Courier courier = new Courier("Nigol",
                "1234",
                "Jack");
        given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then().statusCode(201);

    }

    @After
    public void deleteCreatedCourier()
    {
        File json = new File("src/test/resources/Login.json");
        String courierId = given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/courier/login")
                .then().extract().body().path("id");
        if(courierId != null) {
            given()
                    .delete("/api/v1/courier/{courierId}");
        }
        else System.out.println("Курьер не был создан");
    }

    @Test
    public void authCourier() {

        File json = new File("src/test/resources/Login.json");

        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/courier/login")
                .then().assertThat().statusCode(200);
    }

    @Test
    public void authCourierWithoutPassword() {

        File json = new File("src/test/resources/onlyLogin.json");

        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/courier/login")
                .then().assertThat().statusCode(400);
    }

    @Test
    public void authCourierWithoutLogin() {

        File json = new File("src/test/resources/onlyPassword.json");

        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/courier/login")
                .then().assertThat().statusCode(400);
    }

    @Test
    public void authCourierWithWrongLogin() {

        File json = new File("src/test/resources/wrongLogin.json");

        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/courier/login")
                .then().assertThat().statusCode(400);
    }

    @Test
    public void authCourierWithWrongPassword() {

        File json = new File("src/test/resources/wrongPassword.json");

        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/courier/login")
                .then().assertThat().statusCode(400);
    }

    @Test
    public void authNotCreatedCourier() {

        File json = new File("src/test/resources/notCreated.json");

        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/courier/login")
                .then().assertThat().statusCode(404);
    }

    @Test
    public void authCourierResponseContainsId() {

        File json = new File("src/test/resources/Login.json");

        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/courier/login")
                .then().assertThat().body("id", notNullValue());
    }
}