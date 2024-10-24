package factory;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.AbstractDriverOptions;

public class ModeSettings {
    private BrowserMode mode;

    public ModeSettings(BrowserMode mode) {
        this.mode = mode;
    }

    public ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        switch (mode) {
            case HEADLESS:
                chromeOptions.addArguments("--headless");
                break;
            case KIOSK:
                chromeOptions.addArguments("--kiosk");
                break;
            case FULLSCREEN:
                chromeOptions.addArguments("--start-fullscreen");
                break;
            default:
                throw new IllegalArgumentException("Неподдерживаемый режим браузера: " + mode);
        }
        return chromeOptions;
    }

    public FirefoxOptions getFirefoxOptions() {
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        switch (mode) {
            case HEADLESS:
                firefoxOptions.addArguments("--headless");
                break;
            case KIOSK:
                firefoxOptions.addArguments("--kiosk");
                break;
            case FULLSCREEN:
                firefoxOptions.addArguments("--start-fullscreen");
                break;
            default:
                throw new IllegalArgumentException("Неподдерживаемый режим браузера: " + mode);
        }
        return firefoxOptions;
    }
}