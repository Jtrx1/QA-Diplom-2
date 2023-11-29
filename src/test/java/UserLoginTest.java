import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.example.client.UserApiClient;
import org.example.helper.CreateUserFaker;
import org.example.model.User;
import org.example.model.UserLogin;
import org.example.model.UserResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.junit.Assert.assertEquals;
@DisplayName("Тесты на авторизацию пользователя")
public class UserLoginTest {
    private UserLogin userLogin;
    private UserApiClient userApiClient;

    private String accessToken;

    @Before
    @DisplayName("Создаем нового пользователя для теста")
    @Step("Создаем нового пользователя для теста")
    public void init(){
        userApiClient=new UserApiClient();
        User user = CreateUserFaker.getFakerUser();
        Response response = userApiClient.createUser(user);
        UserResponse userResponse = response.as(UserResponse.class);
        accessToken = userResponse.getAccessToken();
        userLogin=new UserLogin(user.getEmail(), user.getPassword());
    }
    @After

    @Step("Удаляем пользователя")
    public void exit(){
        userApiClient.deleteUser(accessToken);
    }

    @Test
    @DisplayName("Успешный вход")
    @Step("Успешный вход")
    public void loginSuccessTest(){
        Response response = userApiClient.userLogin(userLogin);
        assertEquals(SC_OK,response.statusCode());
    }
    @Test
    @DisplayName("Вход неправильным логино")
    @Step("Вход неправильным логином")
    public void loginTestFailIncorrectEmail(){
        userLogin.setEmail("htrz@mail.ru");
        Response response = userApiClient.userLogin(userLogin);
        assertEquals(SC_UNAUTHORIZED,response.statusCode());
    }
    @Test
    @DisplayName("Вход неправильным паролем")
    @Step("Вход неправильным паролем")
    public void loginTestFailIncorrectPassword(){
        userLogin.setPassword(userLogin.getPassword()+"1");
        Response response = userApiClient.userLogin(userLogin);
        assertEquals(SC_UNAUTHORIZED,response.statusCode());
    }
}
