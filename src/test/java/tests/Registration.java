package tests;

import base.BaseSettings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.PageRegistrationUser;

public class Registration extends BaseSettings {
    @Test
    @DisplayName("Проверка регистрации")
    public void verificationRegistration()  {
        new PageRegistrationUser(driver)
                .openRegistrationPage()
                .inputUserName()
                .inputEmail()
                .inputPassword()
                .inputСonfirmPassword()
                .birthdayСalendar()
                .clickLanguageLevelSelect()
                .clickLanguageLevelIntermediate()
                .closeLanguageLevelSelect()
                .clickRegisterButton();
        String resultText = new PageRegistrationUser(driver).getOutputResult();
        Assertions.assertEquals("Имя пользователя: Roman\nЭлектронная почта: Roman@mail.ru\nДата рождения: 1990-05-30\nУровень языка: intermediate", resultText);
    }
}