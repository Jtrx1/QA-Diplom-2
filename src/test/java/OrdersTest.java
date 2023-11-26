import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.example.client.OrderApiClient;
import org.example.client.UserApiClient;

import org.example.helper.CreateIngredient;
import org.example.helper.CreateUserFaker;
import org.example.model.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;


@DisplayName("Тесты на создание заказа")
public class OrdersTest {
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
    }
    @Before
    public void exit(){
        userApiClient.deleteUser(accessToken);
    }
    @Test
    @Step("Заказ с несколькими ингридиетами")
    @DisplayName("Заказ с несколькими ингридиетами")
    public void orderSomeIngredientsTest(){
        Response response=orderApiClient.createOrder(CreateIngredient.someIngredients(), accessToken);
        Assert.assertEquals(SC_OK, response.statusCode());
        OrderResponse orderResponse=response.as(OrderResponse.class);
        Assert.assertTrue(orderResponse.isSuccess());
    }
    @Test
    @Step("Заказ с одним ингридиентом")
    @DisplayName("Заказ с одним ингридиентом")
    public void orderIngredientsTest(){
        Response response=orderApiClient.createOrder(CreateIngredient.ingredient(), accessToken);
        Assert.assertEquals(SC_OK, response.statusCode());
        OrderResponse orderResponse=response.as(OrderResponse.class);
        Assert.assertTrue(orderResponse.isSuccess());
    }
    @Test
    @Step("Заказ неавторизованного пользователя")
    @DisplayName("Заказ неавторизованного пользователя")
    public void orderWithoutAutorezationTest(){
        Response response=orderApiClient.createOrderWithoutAutorization(CreateIngredient.someIngredients());
        Assert.assertEquals(SC_OK, response.statusCode());
        OrderResponse orderResponse=response.as(OrderResponse.class);
        Assert.assertTrue(orderResponse.isSuccess());
    }
    @Test
    @Step("Заказ без ингридиентов")
    @DisplayName("Заказ без ингридиентов")
    public void orderWithoutIngridientTest(){
        String[] strings=new String[0];
        Ingredients ingredients=new Ingredients(strings);
        Response response=orderApiClient.createOrder(ingredients, accessToken);
        Assert.assertEquals(SC_BAD_REQUEST, response.statusCode());
        OrderResponse orderResponse=response.as(OrderResponse.class);
        Assert.assertFalse(orderResponse.isSuccess());
        Assert.assertEquals("Ingredient ids must be provided", orderResponse.getMessage());
    }
    @Test
    @Step("Заказ с неверным хешем ингридиентов")
    @DisplayName("Заказ без ингридиентов")
    public void orderInvalidHashTest(){
        Ingredients ingredients=CreateIngredient.ingredientInvalidHash();
        Response response=orderApiClient.createOrderWithoutAutorization(ingredients);
        Assert.assertEquals(SC_INTERNAL_SERVER_ERROR, response.statusCode());
    }

}
