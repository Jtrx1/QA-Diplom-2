package org.example.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.model.Ingredients;

public class OrderApiClient extends BaseApiClient{

    @Step("получаем список ингридиентов")
    public Response getIngredients(){
        return getPostSpec()
                .get("ingredients");
    }

    @Step("Создаем заказ")
    public Response createOrder(Ingredients ingredients, String accessToken){
        return getPostSpec()
                .header("Authorization", accessToken)
                .body(ingredients)
                .post("orders");
    }
    @Step("Создаем заказ, без авторизации")
    public Response createOrderWithoutAutorization(Ingredients ingredients){
        return getPostSpec()
                .body(ingredients)
                .post("orders");
    }
    @Step("получаем заказ")
    public Response receinvgOrder(String accessToken){
        return getPostSpec()
                .header("Authorization", accessToken)
                .get("orders");
    }

}
