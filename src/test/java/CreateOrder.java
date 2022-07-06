import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class CreateOrder {

    private final OrderClient orderClient;

    public CreateOrder(String color, OrderClient orderClient) {
        this.orderClient = orderClient;
    }

    @Parameterized.Parameters(name = "color={0}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"black", new OrderClient("src/test/resources/colorBlack.json")},
                {"grey", new OrderClient("src/test/resources/colorGrey.json")},
                {"black-grey", new OrderClient("src/test/resources/colorBlackGrey.json")},
                {"null", new OrderClient("src/test/resources/colorNull.json")},
        });
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = "https://qa-scooter.praktikum-services.ru/";
    }

    @Test
    @DisplayName("Check set scooter color ")
    public void createScooter() {
        orderClient.testCreateScooter();
    }

    @Test
    @DisplayName("Check response body have track")
    public void createScooterResponseHaveTrack() {
        orderClient.testCreateScooterResponse();
    }
}