import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import io.qameta.allure.junit4.DisplayName;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class createCourier {


    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    public void createNewCourier() {
        Courier courier = new Courier("Nigol",
                "1234",
                "Jack");
        File json = new File("src/test/resources/Login.json");

        given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then().statusCode(201);

            String courierId = given()
                    .header("Content-type", "application/json")
                    .and()
                    .body(json)
                    .post("/api/v1/courier/login")
                    .then().extract().body().path("id");

            given()
                    .delete("/api/v1/courier/{courierId}")
                    .then().assertThat().statusCode(200);
        }

    @Test
    public void checkSuccessMessage() {
        Courier courier = new Courier("Nigol",
                "1234",
                "Jack");
        File json = new File("src/test/resources/Login.json");


        given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then().assertThat().body("ok", equalTo("true"));

        String courierId = given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/courier/login")
                .then().extract().body().path("id");

        given()
                .delete("/api/v1/courier/{courierId}")
                .then().assertThat().statusCode(200);
    }


    @Test
    public void createCourierWithSameLogin() {
        Courier courier = new Courier("Nigol",
                "1234",
                "Jack");
        given()

                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier");

        given()

                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then().statusCode(409);

    }
    @Test
    public void createCourierWithoutLogin() {
        Courier courier = new Courier("",
                "1234",
                "Jack");

        given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then().statusCode(400);
    }

    @Test
    public void createCourierWithoutPassword() {
        Courier courier = new Courier("Nigol",
                "",
                "Jack");

        given()
                .header("Content-type", "application/json")
                .and()
                .body(courier)
                .when()
                .post("/api/v1/courier")
                .then().statusCode(400);
    }
}
