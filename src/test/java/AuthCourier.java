import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

public class AuthCourier {

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    @DisplayName("Check auth courier")
    public void authCourier() {
        var courierClient = new CourierClient("src/test/resources/Login.json");
        courierClient.testCreateCourier();
        courierClient.testAuthCourier();
        courierClient.testDeleteCourier();
    }

    @Test
    @DisplayName("Test error code when auth courier without required field login ")
    public void authCourierWithoutLogin() {
        var courierClient = new CourierClient("src/test/resources/onlyPassword.json");
        courierClient.testAuthCourierWithoutRequiredField();
    }

    @Test
    @DisplayName("Test error code when auth courier without required field password ")
    public void authCourierWithoutPassword() {
        var courierClient = new CourierClient("src/test/resources/onlyLogin.json");
        courierClient.testAuthCourierWithoutRequiredField();
    }

    @Test
    @DisplayName("Test error code when auth with uncreated courier")
    public void authWithUncreatedCourier() {
        var courierClient = new CourierClient("src/test/resources/notCreated.json");
        courierClient.testAuthWithNotFoundCourierCredentials();
    }

    @Test
    @DisplayName("Test error code when auth with wrong login")
    public void authWithWrongCourierLogin() {
        var courierClient = new CourierClient("src/test/resources/wrongLogin.json");
        courierClient.testAuthWithNotFoundCourierCredentials();
    }

    @Test
    @DisplayName("Test error code when auth with wrong password")
    public void authWithWrongCourierPassword() {
        var courierClient = new CourierClient("src/test/resources/wrongPassword.json");
        courierClient.testAuthWithNotFoundCourierCredentials();
    }

    @Test
    @DisplayName("Check that success auth courier response contains id")
    public void authCourierResponseBodyContainsId() {
        var courierClient = new CourierClient("src/test/resources/Login.json");
        courierClient.testCreateCourier();
        courierClient.testAuthResponse();
        courierClient.testDeleteCourier();
    }
}

