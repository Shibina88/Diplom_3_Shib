package site.stellarburgers.nomoreparties.constant.clients;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import site.stellarburgers.nomoreparties.constant.serialRequestDeserialResponse.user.UserCreds;
import site.stellarburgers.nomoreparties.constant.serialRequestDeserialResponse.user.UserRequest;
import site.stellarburgers.nomoreparties.constant.service.UriUserData;

import static io.restassured.RestAssured.given;

public class UserClient {
    private static final String CREATE_USER_PATH = "api/auth/register";
    private static final String LOGIN_USER_PATH = "api/auth/login";
    private static final String USER_PATH = "api/auth/user";

    public UserClient() {
        RestAssured.baseURI = UriUserData.BASE_URI;
    }

    @Step("Create user")
    public Response userCreate(UserRequest user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .post(CREATE_USER_PATH);
    }

    @Step("Login user")
    public Response userLogin(UserRequest user) {
        return given()
                .header("Content-type", "application/json")
                .body(user)
                .when()
                .post(LOGIN_USER_PATH);
    }

    @Step("Delete user")
    public Response userDelete(UserRequest user, UserCreds userCreds) {
        return given()
                .header("Content-type", "application/json")
                .header("Authorization", userCreds.getAccessToken())
                .body(user)
                .when()
                .delete(USER_PATH);
    }
}
