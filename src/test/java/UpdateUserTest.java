import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.example.client.UserApiClient;
import org.example.helper.CreateUserFaker;
import org.example.model.UpdateUserResponse;
import org.example.model.User;
import org.example.model.UserResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNAUTHORIZED;
@DisplayName("Изменение пользователя")
public class UpdateUserTest {
    private User user;
    private UserApiClient userApiClient;

    private String accessToken;

    @Before
    @Step("Создаем нового пользователя для теста")
    public void init(){
        userApiClient=new UserApiClient();
        user = CreateUserFaker.getFakerUser();
        Response resp = userApiClient.createUser(user);
        UserResponse userResponse = resp.as(UserResponse.class);
        accessToken = userResponse.getAccessToken();
    }
    @After
    @DisplayName("Удаляем пользователя")
    @Step("Удаляем пользователя")
    public void exit(){
        userApiClient.deleteUser(accessToken);
    }
    @Test
    @DisplayName("Изменяем Email пользователя")
    @Step("Изменяем Email пользователя")
    public void updateEmailUserTest(){
        String expected=CreateUserFaker.getFakerUser().getEmail();
        user.setEmail(expected);
        Response response = userApiClient.updateUser(accessToken, user);
        UpdateUserResponse updateUserResponse =response.as(UpdateUserResponse.class);
        String actual=updateUserResponse.user.getEmail();
        Assert.assertEquals(SC_OK, response.statusCode());
        Assert.assertEquals(expected, actual);
    }
    @Test
    @DisplayName("Изменяем Имя пользователя")
    @Step("Изменяем Имя пользователя")
    public void updatePasswordUserTest(){
        String expected=CreateUserFaker.getFakerUser().getName();
        user.setName(expected);
        Response response = userApiClient.updateUser(accessToken, user);
        UpdateUserResponse updateUserResponse =response.as(UpdateUserResponse.class);
        String actual=updateUserResponse.user.getName();
        Assert.assertEquals(SC_OK, response.statusCode());
        Assert.assertEquals(expected, actual);
    }
    @Test
    @DisplayName("Попытка изменения пользователя без авторизации")
    @Step("Попытка изменения пользователя без авторизации")
    public void updateWithoutAutorizationUserTest(){
        user.setName(CreateUserFaker.getFakerUser().getName());
        Response response = userApiClient.updateUser("", user);
        UpdateUserResponse updateUserResponse =response.as(UpdateUserResponse.class);
        Assert.assertEquals(SC_UNAUTHORIZED, response.statusCode());
        Assert.assertEquals("You should be authorised", updateUserResponse.getMessage());

    }




}
