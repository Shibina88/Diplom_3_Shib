package site.stellarburgers.nomoreparties.constant;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import site.stellarburgers.nomoreparties.constant.clients.UserClient;
import site.stellarburgers.nomoreparties.constant.pageObjects.*;
import site.stellarburgers.nomoreparties.constant.serialRequestDeserialResponse.user.UserCreds;
import site.stellarburgers.nomoreparties.constant.serialRequestDeserialResponse.user.UserRequest;
import site.stellarburgers.nomoreparties.constant.serialRequestDeserialResponse.user.UserResponse;

import static org.junit.Assert.assertTrue;
import static site.stellarburgers.nomoreparties.constant.service.UriUserData.BASE_URI;

public class AccountLoginTest {
    private WebDriver driver;
    private UserClient userClient;
    private UserRequest userRequest;
    private UserResponse userCreationResponse;
    private AuthorizationPage authorizationPage;
    private RegistrationPage registrationPage;
    private HomePage homePage;
    private PasswordRecoveryPage passwordRecoveryPage;
    private Headers headers;

    @Before
    public void setUp() {
        userRequest = UserRequest.userGenerator();
        userClient = new UserClient();
        userCreationResponse = userClient.userCreate(userRequest)
                .body()
                .as(UserResponse.class);
        driver = new ChromeDriver(); //driver = WebDriverCreator.getWebDriver(false//true);
        authorizationPage = new AuthorizationPage(driver);
        registrationPage = new RegistrationPage(driver);
        homePage = new HomePage(driver);
        passwordRecoveryPage = new PasswordRecoveryPage(driver);
        headers = new Headers(driver);

    }

    @Test
    @DisplayName("Успешный логин через кнопку \"Войти в аккаунт\"")
    public void successUserSignInButtonTest() {
        driver.get(BASE_URI);
        homePage.clickSignInButton();
        authorizationPage.fillingInFields(userRequest);
        assertTrue(homePage.isCrateOrderButton());
    }

    @Test
    @DisplayName("Успешный логин  через ссылку \"Личный кабинет\"")
    public void successUserUsingAccountLoginTest() {
        driver.get(BASE_URI);
        headers.clickAccountLoginButton();
        authorizationPage.fillingInFields(userRequest);
        assertTrue(homePage.isCrateOrderButton());
    }

    @Test
    @DisplayName("Успешный логин  через форму регистрации")
    public void successLoginUserSignInLinkToRegistrationPageTest() {
        driver.get(BASE_URI);
        homePage.clickSignInButton();
        authorizationPage.clickRegisterButton();
        registrationPage.clickSignInLink();
        authorizationPage.fillingInFields(userRequest);
        assertTrue(homePage.isCrateOrderButton());
    }

    @Test
    @DisplayName("Успешный логин  через страницу восстановленя пароля")
    public void successLoginUserPassRecoveryPageTest() {
        driver.get(BASE_URI);
        homePage.clickSignInButton();
        authorizationPage.clickRestorePasswordLink();
        passwordRecoveryPage.clickSignInLink();
        authorizationPage.fillingInFields(userRequest);
        assertTrue(homePage.isCrateOrderButton());
    }

    @Test
    @DisplayName("Неверные email или пароль для входа")
    public void errorLoginTest() {
        driver.get(BASE_URI);
        homePage.clickSignInButton();
        authorizationPage.fillingInFields(UserRequest.userGenerator());
        assertTrue(authorizationPage.isPageTitle());
    }

    @After
    public void tearDown() {
        userClient.userDelete(userRequest, UserCreds.getCredsFrom(userCreationResponse));
        driver.quit();
    }
}
