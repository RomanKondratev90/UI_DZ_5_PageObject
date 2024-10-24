package pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageRegistrationUser {
    private WebDriver driver;
    private String username = System.getProperty("username");
    private String password = System.getProperty("password");
    private String email = System.getProperty("email");
    private String url = System.getProperty("base.url");
    private static final Logger logger = LogManager.getLogger(PageRegistrationUser.class);

    private By userNameField = By.id(("username"));             //поле ввода имени пользователя
    private By EmailField = By.id(("email"));                   //поле ввода почты
    private By PasswordField = By.id(("password"));             //поле ввода пароля
    private By confirmPassword = By.id(("confirm_password"));   //поле ввода проверки пароля
    private By languageLevelSelect = By.id(("language_level")); //select - уровень языка
    private By languageLevelIntermediate = By.xpath(("//select[@id='language_level']/option[normalize-space(text())='Средний']")); //выбор языка - средний
    private By registerButton = By.xpath("//input[@type='submit']"); //кнопка "Зарегистрироваться"
    private By outputResult = By.id("output");                                   //элемент с результатом отправки данных

    public PageRegistrationUser(WebDriver driver) {
        this.driver = driver;
    }

    public PageRegistrationUser openRegistrationPage() {
        logger.info("Переход по URL");
        driver.get(url);
        return this;
    }

    public PageRegistrationUser inputUserName() {
        logger.info("Ввод имени");
        driver.findElement(userNameField).sendKeys(username);
        return this;
    }

    public PageRegistrationUser inputEmail() {
        logger.info("Ввод email");
        driver.findElement(EmailField).sendKeys(email);
        return this;
    }

    public PageRegistrationUser inputPassword() {
        logger.info("Ввод пароля");
        driver.findElement(PasswordField).sendKeys(password);
        return this;
    }

    public PageRegistrationUser inputСonfirmPassword() {
        logger.info("Ввод повторно пароля");
        driver.findElement(confirmPassword).sendKeys(password);
        return this;
    }

    public PageRegistrationUser clickLanguageLevelSelect() {
        logger.info("Клик по выпадающему списку с уровнем языка");
        driver.findElement(languageLevelSelect).click();
        return this;
    }

    public PageRegistrationUser clickLanguageLevelIntermediate() {
        logger.info("Выбор уровня языка - средний");
        driver.findElement(languageLevelIntermediate).click();
        return this;
    }

    public PageRegistrationUser clickRegisterButton() {
        logger.info("Клик по кнопке 'Зарегистрироваться'");
        driver.findElement(registerButton).click();
        return this;
    }

    //календарь
    public PageRegistrationUser birthdayСalendar() {
        logger.info("Ввод даты рождения в календарь - 1990-05-30");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.getElementById('birthdate').value='1990-05-30';");
        return this;
    }
    //закрытие select уровня языков
    public PageRegistrationUser closeLanguageLevelSelect() {
        logger.info("Закрытие выпадающего списка с выбором уровня языка");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement languageLevelElement = driver.findElement(languageLevelSelect);
        js.executeScript("arguments[0].blur();", languageLevelElement);
        return this;
    }
    //получение текст элемента
    public String getOutputResult() {
        return driver.findElement(outputResult).getText();
    }
}