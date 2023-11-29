package org.example.client;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class BaseApiClient {
    public static final String BASE_URL = "https://stellarburgers.nomoreparties.site/api/";

    public RequestSpecification getPostSpec() {
        return given()
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON);


    }
}
