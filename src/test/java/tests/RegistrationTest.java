package tests;

import base.BaseSettings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.PageRegistrationUser;

public class RegistrationTest extends BaseSettings {
    @Test
    @DisplayName("Проверка регистрации")
    public void verificationRegistration() {
        PageRegistrationUser registrationPage = new PageRegistrationUser(driver)
                .openRegistrationPage()
                .inputUserData()
                .inputDateOfBirth("30-05-1990")
                .selectLanguageLevel("Средний")
                .clickRegisterButton();
        Assertions.assertEquals("Имя пользователя: Roman\nЭлектронная почта: Roman@mail.ru\nДата рождения: 1990-05-30\nУровень языка: intermediate", registrationPage.getOutputResult());
    }
}
