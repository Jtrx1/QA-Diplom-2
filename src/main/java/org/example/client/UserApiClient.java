package org.example.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.example.model.User;
import org.example.model.UserLogin;


public class UserApiClient extends BaseApiClient{
    @Step("Создаем пользователя")
    public Response createUser(User user) {
        return getPostSpec()

                .body(user)
                .when()
                .post("auth/register");
    }

    @Step("Удаляем пользователя")
    public Response deleteUser(String accessToken){
       return  getPostSpec()
                .header("Authorization", accessToken)
                .delete("auth/user");
    }
    @Step("Вход пользователя")
    public Response userLogin(UserLogin userLogin){
        return getPostSpec()
                .body(userLogin)
                .post("auth/login");

    }

    @Step("Получение и обновление информации о пользователе")
    public Response updateUser(String accessToken, User user){
        return  getPostSpec()
                .header("Authorization", accessToken)
                .body(user)
                .patch("auth/user");
    }


}
