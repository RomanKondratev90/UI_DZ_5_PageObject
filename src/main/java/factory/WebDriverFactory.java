package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class WebDriverFactory {
    private WebDriver driver;

    public WebDriver createDriver(String browser, BrowserMode mode) {
        ModeSettings modeSettings = new ModeSettings(mode);

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = modeSettings.getChromeOptions();
                driver = new org.openqa.selenium.chrome.ChromeDriver(chromeOptions);
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = modeSettings.getFirefoxOptions();
                driver = new org.openqa.selenium.firefox.FirefoxDriver(firefoxOptions);
                break;

            default:
                throw new IllegalArgumentException("Browser не поддерживается: " + browser);
        }

        return driver;
    }
}