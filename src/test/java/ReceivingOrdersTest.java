import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.example.client.OrderApiClient;
import org.example.client.UserApiClient;
import org.example.helper.CreateIngredient;
import org.example.helper.CreateUserFaker;
import org.example.model.GetOrders;
import org.example.model.User;
import org.example.model.UserResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;


@DisplayName("Тесты на получение списка заказов")
public class ReceivingOrdersTest {
    private OrderApiClient orderApiClient;
    private UserApiClient userApiClient;
    private String accessToken;
    @Before
    public void init(){
        orderApiClient=new OrderApiClient();
        userApiClient=new UserApiClient();
        User user = CreateUserFaker.getFakerUser();
        Response resp = userApiClient.createUser(user);
        UserResponse userResponse = resp.as(UserResponse.class);
        accessToken = userResponse.getAccessToken();
        orderApiClient.createOrderWithoutAutorization(CreateIngredient.someIngredients());
    }

    @Step("Получаем заказы авторизованного пользователя")
    @Test
    public void ReceivingOrdersWithAutorization(){
        Response response = orderApiClient.receinvgOrder(accessToken);
        GetOrders getOrders = response.as(GetOrders.class);
        Assert.assertEquals(SC_OK, response.statusCode());
        Assert.assertTrue(getOrders.isSuccess());
    }
    @Test
    @Step("Пользователь не авторизован")
    public void ReceivingOrdersWithoutAutorization(){
        Response response = orderApiClient.receinvgOrder("");
        GetOrders getOrders = response.as(GetOrders.class);
        Assert.assertEquals(SC_UNAUTHORIZED, response.statusCode());
        Assert.assertFalse(getOrders.isSuccess());
    }


    @After
    public void exit(){
        userApiClient.deleteUser(accessToken);
    }

}
