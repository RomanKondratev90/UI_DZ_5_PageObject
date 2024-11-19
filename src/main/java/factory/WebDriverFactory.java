package factory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;

public class WebDriverFactory {
    private WebDriver driver;

    public WebDriver createDriver(String browser, BrowserMode mode) {
        ModeSettings modeSettings = new ModeSettings(mode);
        String seleniumGridUrl = System.getenv("SELENIUM_GRID_URL");
        if (seleniumGridUrl == null || seleniumGridUrl.isEmpty()) {
            throw new IllegalArgumentException("SELENIUM_GRID_URL не указан.");
        }
        try {
            switch (browser.toLowerCase()) {
                case "chrome":
                    ChromeOptions chromeOptions = modeSettings.getChromeOptions();
                    driver = new RemoteWebDriver(new URL(seleniumGridUrl), chromeOptions);
                    break;
                case "firefox":
                    FirefoxOptions firefoxOptions = modeSettings.getFirefoxOptions();
                    driver = new RemoteWebDriver(new URL(seleniumGridUrl), firefoxOptions);
                    break;
                default:
                    throw new IllegalArgumentException("Browser не поддерживается: " + browser);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Некорректный URL для Selenium Grid: "+ seleniumGridUrl, e);
        }
        return driver;
    }
}