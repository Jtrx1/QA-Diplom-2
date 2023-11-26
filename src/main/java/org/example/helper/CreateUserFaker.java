package org.example.helper;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;
import org.example.model.User;


public class CreateUserFaker {

    @Step("Генерируем фейкового пользователя")
    public static User getFakerUser() {

        Faker faker = new Faker();

        String login = faker.internet().emailAddress();
        String password = faker.internet().password();
        String name = faker.name().firstName();

        return new User(login, password, name);
    }

}





