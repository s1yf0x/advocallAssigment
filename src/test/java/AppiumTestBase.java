import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppiumTestBase {

    protected static AppiumDriver driver;
    private static AppiumDriverLocalService service;

    @BeforeAll
    public static void setUp() throws MalformedURLException {
        // Запускаем аппиум
        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder());
        service.start();


        // Устанавливаем капабилити
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appium:automationName", "UiAutomator2");

        // Инициализируем драйвер
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), capabilities);
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        if (service != null) {
            service.stop();
        }
    }
}