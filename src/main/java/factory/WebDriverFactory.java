package factory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WebDriverFactory {
    private WebDriver driver;

    public WebDriver createDriver(String browser, BrowserMode mode) {
        ModeSettings modeSettings = new ModeSettings(mode);

        switch (browser.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = modeSettings.getChromeOptions();

                String gridUrl = System.getProperty("SELENIUM_GRID_URL");
                if (gridUrl != null && !gridUrl.isEmpty()) {
                    chromeOptions.setCapability("selenoid:options", getSelenoidOptions());
                    try {
                        driver = new RemoteWebDriver(new URL(gridUrl), chromeOptions);
                    } catch (MalformedURLException e) {
                        throw new RuntimeException("Некорректный URL для Selenium Grid: " + gridUrl, e);
                    }
                } else {
                    driver = new org.openqa.selenium.chrome.ChromeDriver(chromeOptions);
                }
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = modeSettings.getFirefoxOptions();
                gridUrl = System.getProperty("SELENIUM_GRID_URL");
                if (gridUrl != null && !gridUrl.isEmpty()) {
                    firefoxOptions.setCapability("selenoid:options", getSelenoidOptions());
                    try {
                        driver = new RemoteWebDriver(new URL(gridUrl), firefoxOptions);
                    } catch (MalformedURLException e) {
                        throw new RuntimeException("Некорректный URL для Selenium Grid: " + gridUrl, e);
                    }
                } else {
                    driver = new org.openqa.selenium.firefox.FirefoxDriver(firefoxOptions);
                }
                break;
            default:
                throw new IllegalArgumentException("Browser не поддерживается: " + browser);
        }
        return driver;
    }

    private Map<String, Object> getSelenoidOptions() {
        Map<String, Object> options = new HashMap<>();
        options.put("name", "Test badge");
        options.put("sessionTimeout", "15m");
        options.put("env", new ArrayList<>(List.of("TZ=UTC")));
        options.put("labels", Map.of("manual", "true"));
        options.put("enableVideo", true);
        return options;
    }
}