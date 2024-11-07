package pages;

import components.LanguageLevel;
import components.UserField;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class PageRegistrationUser {
    private WebDriver driver;
    private String username = System.getProperty("username");
    private String password = System.getProperty("password");
    private String confirmPassword = password;
    private String email = System.getProperty("email");
    private String url = System.getProperty("base.url");
    private static final Logger logger = LogManager.getLogger(PageRegistrationUser.class);

    private static final By LANGUAGE_LEVEL_SELECT = By.id("language_level");
    private static final By REGISTER_BUTTON = By.xpath("//input[@type='submit']");
    private static final By RESULT = By.id("output");

    public PageRegistrationUser(WebDriver driver) {
        this.driver = driver;
    }
    public PageRegistrationUser openRegistrationPage() {
        logger.info("Переход по URL");
        driver.get(url);
        return this;
    }
    public PageRegistrationUser inputField(UserField field, String value) {
        logger.info("Ввод данных в поле: " + field);
        driver.findElement(field.getLocator()).sendKeys(value);
        return this;
    }
    public PageRegistrationUser inputUserData() {
        inputField(UserField.USERNAME, username);
        inputField(UserField.EMAIL, email);
        inputField(UserField.PASSWORD, password);
        if (!password.equals(confirmPassword)) {
            logger.error("Пароли не совпадают");
            throw new IllegalArgumentException("Пароли не совпадают");
        }
        inputField(UserField.CONFIRM_PASSWORD, confirmPassword);
        return this;
    }
    public PageRegistrationUser inputDateOfBirth(String date) {
        logger.info("Ввод даты рождения: " + date);
        driver.findElement(By.id("birthdate")).sendKeys(date);
        return this;
    }
    public PageRegistrationUser selectLanguageLevel(LanguageLevel level) {
        logger.info("Выбор уровня языка: " + level.getDisplayValue());
        Select languageSelect = new Select(driver.findElement(LANGUAGE_LEVEL_SELECT));
        languageSelect.selectByVisibleText(level.getDisplayValue());
        return this;
    }
    public PageRegistrationUser clickRegisterButton() {
        logger.info("Клик по кнопке 'Зарегистрироваться'");
        driver.findElement(REGISTER_BUTTON).click();
        return this;
    }
    public String getOutputResult() {
        return driver.findElement(RESULT).getText();
    }
}
