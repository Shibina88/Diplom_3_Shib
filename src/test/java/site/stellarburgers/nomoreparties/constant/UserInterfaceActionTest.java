package site.stellarburgers.nomoreparties.constant;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import site.stellarburgers.nomoreparties.constant.clients.UserClient;
import site.stellarburgers.nomoreparties.constant.pageObjects.AuthorizationPage;
import site.stellarburgers.nomoreparties.constant.pageObjects.Headers;
import site.stellarburgers.nomoreparties.constant.pageObjects.HomePage;
import site.stellarburgers.nomoreparties.constant.pageObjects.LoginPage;
import site.stellarburgers.nomoreparties.constant.serialRequestDeserialResponse.user.UserCreds;
import site.stellarburgers.nomoreparties.constant.serialRequestDeserialResponse.user.UserRequest;
import site.stellarburgers.nomoreparties.constant.serialRequestDeserialResponse.user.UserResponse;

import static org.junit.Assert.assertTrue;
import static site.stellarburgers.nomoreparties.constant.service.UriUserData.BASE_URI;

public class UserInterfaceActionTest {

    private WebDriver driver;
    private UserClient userClient;
    private UserRequest userRequest;
    private UserResponse userCreationResponse;
    private AuthorizationPage authorizationPage;
    private HomePage homePage;
    private LoginPage loginPage;
    private Headers headers;

    @Before
    public void setUp() {
        userRequest = UserRequest.userGenerator();
        userClient = new UserClient();
        userCreationResponse = userClient.userCreate(userRequest)
                .body()
                .as(UserResponse.class);
        driver = new ChromeDriver();  //driver = WebDriverCreator.createWebDriver();
        authorizationPage = new AuthorizationPage(driver);
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        headers = new Headers(driver);
    }

    @Test
    @DisplayName("Успешный переход в профиль пользователя")
    public void successGoToLoginFileTest() {
        driver.get(BASE_URI);
        headers.clickAccountLoginButton();
        authorizationPage.fillingInFields(userRequest);
        headers.clickAccountLoginButton();
        assertTrue(loginPage.isProfileButton());
        assertTrue(loginPage.isOrderHistoryButton());
        assertTrue(loginPage.isInfoText());
    }

    @Test
    @DisplayName("Успешный переход на главную страницу сайта при нажатии на \"Конструктор\" из личного кабинета")
    public void successfulGoToFoodBlocksTest() {
        driver.get(BASE_URI);
        headers.clickAccountLoginButton();
        authorizationPage.fillingInFields(userRequest);
        headers.clickAccountLoginButton();
        headers.clickConstructorButton();
        assertTrue(homePage.isPageTitle());
        assertTrue(homePage.isCrateOrderButton());
    }

    @Test
    @DisplayName("Успешный переход на главную страницу сайта при нажатии на лого из личного кабинета")
    public void successfulGoToHomePageUsingLogoTest() {
        driver.get(BASE_URI);
        headers.clickAccountLoginButton();
        authorizationPage.fillingInFields(userRequest);
        headers.clickAccountLoginButton();
        headers.clickLogo();
        assertTrue(homePage.isPageTitle());
        assertTrue(homePage.isCrateOrderButton());
    }

    @Test
    @DisplayName("Успешный выход пользователя из личного кабинета")
    public void successfulLogOutTest() {
        driver.get(BASE_URI);
        headers.clickAccountLoginButton();
        authorizationPage.fillingInFields(userRequest);
        headers.clickAccountLoginButton();
        loginPage.waitForLoadInfoText();
        loginPage.clickLogoutButton();
        authorizationPage.waitForLoadPageTitle();
        assertTrue(authorizationPage.isPageTitle());
    }

    @After
    public void tearDown() {
        userClient.userDelete(userRequest, UserCreds.getCredsFrom(userCreationResponse));
        driver.quit();
    }
}
