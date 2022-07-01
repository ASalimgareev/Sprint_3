import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.notNullValue;
import io.qameta.allure.junit4.DisplayName;
public class checkOrder {

    @Test
    public void createScooterResponseHaveTrack() {

        given()
                .header("Content-type", "application/json")
                .when()
                .get("/api/v1/orders")
                .then().assertThat().body("orders", notNullValue());
    }

}
