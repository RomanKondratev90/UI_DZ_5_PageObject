package components;

import org.openqa.selenium.By;

public enum UserField {
    USERNAME(By.id("username")),
    EMAIL(By.id("email")),
    PASSWORD(By.id("password")),
    CONFIRM_PASSWORD(By.id("confirm_password"));

    private final By locator;

    UserField(By locator) {
        this.locator = locator;
    }

    public By getLocator() {
        return locator;
    }
}
