package site.stellarburgers.nomoreparties.constant;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import site.stellarburgers.nomoreparties.constant.pageObjects.HomePage;

import static org.junit.Assert.assertTrue;
import static site.stellarburgers.nomoreparties.constant.service.UriUserData.BASE_URI;

public class CheckFoodBlocksTest {
    private WebDriver driver;
    private HomePage homePage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();  // driver = WebDriverCreator.createWebDriver();
        homePage = new HomePage(driver);
    }

    @Test
    @DisplayName("Успешный переход в раздел \"Булки\"")
    public void successEnterToSectionBuns() {
        driver.get(BASE_URI);
        homePage.waitForLoadPageTitleTitle();
        homePage.clickSaucesButton();
        homePage.clickBunsButton();
        assertTrue(homePage.isBuns());
    }

    @Test
    @DisplayName("Успешный переход в раздел \"Соусы\"")
    public void successEnterToSectionSauces() {
        driver.get(BASE_URI);
        homePage.waitForLoadPageTitleTitle();
        homePage.clickSaucesButton();
        assertTrue(homePage.isSauces());
    }

    @Test
    @DisplayName("Успешный переход в раздел \"Начинки\"")
    public void successEnterToToppingsSection() {
        driver.get(BASE_URI);
        homePage.waitForLoadPageTitleTitle();
        homePage.clickToppingsButton();
        assertTrue(homePage.isToppings());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
