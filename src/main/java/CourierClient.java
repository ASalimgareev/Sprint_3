import io.qameta.allure.Step;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import java.io.File;

public class CourierClient {
    private final String pathname;

    public CourierClient(String pathname) {
        this.pathname = pathname;
    }

    @Step("Test courier creation")
    public void testCreateCourier() {
        File json = new File(pathname);
        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/courier")
                .then().assertThat().statusCode(201);
    }

    @Step("Test courier creation response body have ok:true")
    public void testCreateCourierResponse() {
        File json = new File(pathname);
        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/courier")
                .then().assertThat().body("ok", equalTo(true));
    }

    @Step("Test error code when create courier with same Login")
    public void testCreateCourierWithSameLogin() {
        File json = new File(pathname);
        given()

                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/courier");

        given()

                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/courier")
                .then().statusCode(409);
    }

    @Step("Test error code when create courier without required field")
    public void testCreateCourierWithoutRequiredField() {
        File json = new File(pathname);
        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .when()
                .post("/api/v1/courier")
                .then().statusCode(400);
    }

    @Step("Delete courier")
    public void testDeleteCourier() {
        File json = new File(pathname);
        int courierId = given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .post("/api/v1/courier/login")
                .then().extract().body().path("id");

        given()
                .delete(String.format("/api/v1/courier/%d", courierId))
                .then().assertThat().statusCode(200);
    }

    @Step("Test auth courier")
    public void testAuthCourier() {
        File json = new File(pathname);
        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .post("/api/v1/courier/login")
                .then().assertThat().statusCode(200);
    }

    @Step("Test error when auth courier without required field")
    public void testAuthCourierWithoutRequiredField() {
        File json = new File(pathname);
        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .post("/api/v1/courier/login")
                .then().assertThat().statusCode(400);
    }

    @Step("Test error when auth with uncreated courier")
    public void testAuthWithNotFoundCourierCredentials() {
        File json = new File(pathname);
        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .post("/api/v1/courier/login")
                .then().assertThat().statusCode(404);
    }

    @Step("Test courier auth response body have id")
    public void testAuthResponse() {
        File json = new File(pathname);
        given()
                .header("Content-type", "application/json")
                .and()
                .body(json)
                .post("/api/v1/courier/login")
                .then().assertThat().body("id", notNullValue());
    }

}