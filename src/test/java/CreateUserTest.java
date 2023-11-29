import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.example.client.UserApiClient;

import org.example.helper.CreateUserFaker;
import org.example.model.DeleteUserResponse;
import org.example.model.User;
import org.example.model.UserResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@DisplayName("Тесты на создание пользователя")
public class CreateUserTest {
    protected User user;
    protected UserApiClient userApiClient;
    @Before
    public void init(){
        userApiClient=new UserApiClient();
        user = CreateUserFaker.getFakerUser();
    }
    @Test
    @DisplayName("Создание пользователя, успешное")
    @Step("Создание пользователя, успешное")
    public void createCourierSuccess() {
        Response response = userApiClient.createUser(user);
        assertEquals(SC_OK, response.statusCode());
        UserResponse userResponse = response.as(UserResponse.class);
        assertTrue(userResponse.isSuccess());

        String accessToken = userResponse.getAccessToken();

        Response deleteUser = userApiClient.deleteUser(accessToken);
        assertEquals(SC_ACCEPTED, deleteUser.statusCode());
        DeleteUserResponse deleteUserResponse = deleteUser.as(DeleteUserResponse.class);
        assertTrue(deleteUserResponse.isSuccess());
    }
    @Test
    @DisplayName("Создание пользователя, одинаковые пользователи")
    @Step("Создание пользователя, одинаковые пользователи")
    public void createCourierFailIdenticalUsers() {
        Response response = userApiClient.createUser(user);
        assertEquals(SC_OK, response.statusCode());
        UserResponse userResponse = response.as(UserResponse.class);
        assertTrue(userResponse.isSuccess());

        String accessToken = userResponse.getAccessToken();

        Response response2 = userApiClient.createUser(user);
        assertEquals(SC_FORBIDDEN, response2.statusCode());

        Response deleteUser = userApiClient.deleteUser(accessToken);
        assertEquals(SC_ACCEPTED, deleteUser.statusCode());
        DeleteUserResponse deleteUserResponse = deleteUser.as(DeleteUserResponse.class);
        assertTrue(deleteUserResponse.isSuccess());
    }

    @Test
    @DisplayName("Создание пользователя, без имени")
    @Step("Создание пользователя, без имени")
    public void createCourierFailNotName() {
        user.setName(null);
        Response response = userApiClient.createUser(user);
        assertEquals(SC_FORBIDDEN, response.statusCode());
        UserResponse userResponse = response.as(UserResponse.class);
        Assert.assertFalse(userResponse.isSuccess());
    }
    @Test
    @DisplayName("Создание пользователя, без Email")
    @Step("Создание пользователя, без Email")
    public void createCourierFailNotEmail() {
        user.setEmail(null);
        Response response = userApiClient.createUser(user);
        assertEquals(SC_FORBIDDEN, response.statusCode());
        UserResponse userResponse = response.as(UserResponse.class);
        Assert.assertFalse(userResponse.isSuccess());
    }
    @Test
    @DisplayName("Создание пользователя, без пароля")
    @Step("Создание пользователя, без пароля")
    public void createCourierFailNotPassword() {
        user.setPassword(null);
        Response response = userApiClient.createUser(user);
        assertEquals(SC_FORBIDDEN, response.statusCode());
        UserResponse userResponse = response.as(UserResponse.class);
        Assert.assertFalse(userResponse.isSuccess());
    }


}
