import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

public class CreateCourier {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    @DisplayName("Check create courier")
    public void createCourier() {
        var courierClient = new CourierClient("src/test/resources/Login.json");
        courierClient.testCreateCourier();
        courierClient.testDeleteCourier();
    }

    @Test
    @DisplayName("Check response body have ok:true")
    public void createCourierResponseHaveOkTrue() {
        var courier = new CourierClient("src/test/resources/Login.json");
        courier.testCreateCourierResponse();
        courier.testDeleteCourier();
    }

    @Test
    @DisplayName("Check error code when create courier with same login")
    public void createCourierWithSameLoginError() {
        var courierClient = new CourierClient("src/test/resources/Login.json");
        courierClient.testCreateCourierWithSameLogin();
        courierClient.testDeleteCourier();
    }

    @Test
    @DisplayName("Check error code when create courier without login")
    public void createCourierWithoutLoginError() {
        var courier = new CourierClient("src/test/resources/onlyPassword.json");
        courier.testCreateCourierWithoutRequiredField();
    }

    @Test
    @DisplayName("Check error code when create courier without password")
    public void createCourierWithoutPasswordError() {
        var courier = new CourierClient("src/test/resources/onlyLogin.json");
        courier.testCreateCourierWithoutRequiredField();
    }
}



