package site.stellarburgers.nomoreparties.constant.pageObjects;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {
    private final WebDriver driver;
    private final By signInButton = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By crateOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    private final By pageTitle = By.xpath(".//h1[text()='Соберите бургер']");
    private final By bunsButton = By.xpath(".//span[text()='Булки']");
    private final By saucesButton = By.xpath(".//span[text()='Соусы']");
    private final By toppingsButton = By.xpath(".//span[text()='Начинки']");
    private final By bunsSection = By.xpath(
            ".//div[contains(@class, 'tab_type_current') and contains(span/text(), 'Булки')]");
    private final By saucesSection = By.xpath(
            ".//div[contains(@class, 'tab_type_current') and contains(span/text(), 'Соусы')]");
    private final By toppingsSection = By.xpath(
            ".//div[contains(@class, 'tab_type_current') and contains(span/text(), 'Начинки')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ожидание доступности кнопки Войти в аккаунт")
    public void waitForLoadSignInButton() {
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(driver ->
                driver.findElement(signInButton).isEnabled());
    }

    @Step("Ожидание отображения заголовка страницы")
    public void waitForLoadPageTitleTitle() {
        new WebDriverWait(driver, Duration.ofSeconds(8)).until(driver ->
                driver.findElement(pageTitle).isDisplayed());
    }

    @Step("Нажать на кнопку Булки")
    public void clickBunsButton() {
        driver.findElement(bunsButton).click();
    }

    @Step("Нажать на кнопку Соусы")
    public void clickSaucesButton() {
        driver.findElement(saucesButton).click();
    }

    @Step("Нажать на кнопку Начинки")
    public void clickToppingsButton() {
        driver.findElement(toppingsButton).click();
    }

    @Step("Проверка отображения ингредиентов из раздела Булки")
    public boolean isBuns() {
        return driver.findElement(bunsSection).isEnabled();
    }

    @Step("Проверка отображения ингредиентов из раздела Соусы")
    public boolean isSauces() {
        return driver.findElement(saucesSection).isEnabled();
    }

    @Step("Проверка отображения ингредиентов из раздела Начинки")
    public boolean isToppings() {
        return driver.findElement(toppingsSection).isEnabled();
    }


    @Step("Проверка отображения заголовка страницы")
    public boolean isPageTitle() {
        waitForLoadPageTitleTitle();
        return driver.findElement(pageTitle).isDisplayed();
    }

    @Step("Нажать кнопку Войти в аккаунт")
    public void clickSignInButton() {
        waitForLoadSignInButton();
        driver.findElement(signInButton).click();
    }

    @Step("Проверка доступности кнопки Оформить заказ")
    public boolean isCrateOrderButton() {
        waitForLoadPageTitleTitle();
        return driver.findElement(crateOrderButton).isEnabled();
    }
}
