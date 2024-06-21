package site.stellarburgers.nomoreparties.constant;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import site.stellarburgers.nomoreparties.constant.clients.UserClient;
import site.stellarburgers.nomoreparties.constant.pageObjects.AuthorizationPage;
import site.stellarburgers.nomoreparties.constant.pageObjects.HomePage;
import site.stellarburgers.nomoreparties.constant.pageObjects.RegistrationPage;
import site.stellarburgers.nomoreparties.constant.serialRequestDeserialResponse.GeneralResponse;
import site.stellarburgers.nomoreparties.constant.serialRequestDeserialResponse.user.UserCreds;
import site.stellarburgers.nomoreparties.constant.serialRequestDeserialResponse.user.UserRequest;
import site.stellarburgers.nomoreparties.constant.serialRequestDeserialResponse.user.UserResponse;

import static org.junit.Assert.*;
import static site.stellarburgers.nomoreparties.constant.service.UriUserData.BASE_URI;

public class UserRegisterTest {
    private WebDriver driver;
    private UserClient userClient;
    private UserRequest userRequest;
    private UserResponse userCreationResponse;
    private AuthorizationPage authorizationPage;
    private RegistrationPage registrationPage;
    private HomePage homePage;

    @Before
    public void setUp() {
        userRequest = UserRequest.userGenerator();
        userClient = new UserClient();
        driver = new ChromeDriver();  // driver = WebDriverCreator.createWebDriver();
        authorizationPage = new AuthorizationPage(driver);
        registrationPage = new RegistrationPage(driver);
        homePage = new HomePage(driver);
    }

    @Test
    @DisplayName("Успешная регистрация пользователя")
    public void successRegisterTest() {
        driver.get(BASE_URI);
        homePage.clickSignInButton();
        authorizationPage.clickRegisterButton();
        registrationPage.fillingInFields(userRequest);
        Response response = userClient.userLogin(userRequest);
        userCreationResponse = response.body().as(UserResponse.class);
        response.then().assertThat().statusCode(200);
        assertTrue(authorizationPage.isPageTitle());
    }

    @Test
    @DisplayName("Неуспешная регистрация пользователя с паролем меньше 6 символов")
    public void errorRegisterTest() {
        driver.get(BASE_URI);
        homePage.clickSignInButton();
        authorizationPage.clickRegisterButton();
        userRequest.setPassword("12345");
        registrationPage.fillingInFields(userRequest);
        Response response = userClient.userLogin(userRequest);
        GeneralResponse generalResponse = response.body().as(GeneralResponse.class);
        response.then().assertThat().statusCode(401);
        assertTrue(registrationPage.isError());
        assertFalse(generalResponse.isSuccess());
        assertEquals(registrationPage.getTestError(), "Некорректный пароль");
    }

    @After
    public void tearDown() {
        if (userCreationResponse != null) {
            userClient.userDelete(userRequest, UserCreds.getCredsFrom(userCreationResponse));
        }
        driver.quit();
    }
}
