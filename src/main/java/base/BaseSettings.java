package base;

import factory.BrowserMode;
import factory.WebDriverFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public abstract class BaseSettings {
    protected WebDriver driver;
    private static WebDriverFactory factory;
    private static final int IMPLICIT_WAIT_TIME = 20;


    @BeforeAll
    public static void init() {
        factory = new WebDriverFactory();
    }

    @BeforeEach
    public void setUp() {
        driver = factory.createDriver("firefox", BrowserMode.FULLSCREEN);   //настройки браузере и режима
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_TIME, TimeUnit.SECONDS); //время ожидания в сек (20)
    }

    @AfterEach
    public void quitBrowser() {
        factory.quitDriver();
    }
}

