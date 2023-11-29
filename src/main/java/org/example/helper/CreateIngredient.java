package org.example.helper;
import io.qameta.allure.Step;
import org.example.client.OrderApiClient;
import org.example.model.GetIngredients;
import org.example.model.Ingredients;

import java.util.Random;

public class CreateIngredient {

    @Step("Создаем заказ с рандомным количество ингридиентов")
    public static Ingredients someIngredients(){
      OrderApiClient orderApiClient=new OrderApiClient();
        Random random = new Random();
        int j =random.nextInt(orderApiClient.getIngredients().as(GetIngredients.class).getData().size());
        String[] ingredient = new String[j];
        for (int i = 0; i < j; i++) {
            ingredient[i] = orderApiClient.getIngredients().as(GetIngredients.class).getData().get(i).get_id();
        }
        return new Ingredients(ingredient);
    }
    @Step("Создаем заказ с одним случайным ингридиентом")
    public static Ingredients ingredient(){
        OrderApiClient orderApiClient=new OrderApiClient();
        Random random = new Random();
        int j =random.nextInt(orderApiClient.getIngredients().as(GetIngredients.class).getData().size());
        String[] ingredient = new String[j];
        ingredient[0] = orderApiClient.getIngredients().as(GetIngredients.class).getData().get(j).get_id();
        return new Ingredients(ingredient);
    }
    @Step("Создаем заказ с неверным хешем")
    public static Ingredients ingredientInvalidHash(){
        OrderApiClient orderApiClient=new OrderApiClient();
        Random random = new Random();
        int j =random.nextInt(orderApiClient.getIngredients().as(GetIngredients.class).getData().size());
        String[] ingredient = new String[j];
        ingredient[0] = orderApiClient.getIngredients().as(GetIngredients.class).getData().get(j).get_id()+"asdasdasd";
        return new Ingredients(ingredient);
    }

}
