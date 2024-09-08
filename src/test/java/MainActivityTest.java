import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainActivityTest extends AppiumTestBase {

    private static final By NEXT_BUTTON_XPATH = By.xpath("//android.widget.TextView[@text=\"Дальше\"]\n");
    private static final By ALLOW_BUTTON_XPATH = By.xpath("//android.widget.TextView[@text=\"Разрешить\"]");
    private static final By ONLY_THIS_TIME_BUTTON_XPATH = By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_allow_one_time_button\"]");
    private static final By DONE_BUTTON_XPATH = By.xpath("//android.widget.TextView[@text=\"Готово\"]");
    private static final By PHONE_INPUT_XPATH = By.xpath("//android.widget.EditText[@text=\"+7 \"]");
    private static final By GET_CODE_BUTTON_XPATH = By.xpath("//android.widget.TextView[@text=\"Получить код\"]");
    private static final By CODE_INPUT_XPATH = By.xpath("//android.widget.EditText[@text=\"\"]");
    private static final By LOGIN_BUTTON_XPATH = By.xpath("//android.widget.TextView[@text=\"Войти\"]");
    private static final By USER_AVATAR = By.xpath("//androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View");
    private static final By CONS_MENU = By.xpath("//android.widget.TextView[@text=\"Консультация\"]\n");
    private static final By CALL_MENU = By.xpath("//android.widget.TextView[@text=\"Вызов\"]\n");
    private static final By DONT_ALLOW_NOTIFICATIONS_BUTTON_XPATH = By.xpath("//android.widget.Button[@resource-id=\"com.android.permissioncontroller:id/permission_deny_button\"]");


    @Test
    public void testAppFlow() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Устанавливаем время ожидания в 5 секунд

        // Шаг 2: Дождаться кнопки "далее" и нажать
        WebElement nextButton = wait.until(ExpectedConditions.visibilityOfElementLocated(NEXT_BUTTON_XPATH));
        nextButton.click();

        // Шаг 4: Дождаться кнопки "далее" и нажать
        nextButton = wait.until(ExpectedConditions.visibilityOfElementLocated(NEXT_BUTTON_XPATH));
        nextButton.click();

        // Шаг 6: Дождаться кнопки "разрешить" и нажать
        WebElement allowButton = wait.until(ExpectedConditions.visibilityOfElementLocated(ALLOW_BUTTON_XPATH));
        allowButton.click();

        // Шаг 8: Дождаться появления служебного сообщения и нажать "только в этот раз"

        try {
            // Ожидание, пока элемент не станет доступен
            WebElement messageButton = wait.until(ExpectedConditions.visibilityOfElementLocated(ONLY_THIS_TIME_BUTTON_XPATH));
            // Клик по элементу
            messageButton.click();
        } catch (TimeoutException e) {
            // Обработка ситуации, когда элемент не появился в течение 5 секунд
            System.out.println("Кнопка разрешения геолокаци не появилась в течении 10 секунд, пропускаем шаг");
        } catch (Exception e) {
            // Обработка других возможных исключений
            e.printStackTrace();
        }

        // Шаг 10: Дождаться кнопки "готово" и нажать
        WebElement doneButton = wait.until(ExpectedConditions.visibilityOfElementLocated(DONE_BUTTON_XPATH));
        doneButton.click();

        // Шаг 12: Дождаться поле ввода номера телефона и ввести номер
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(PHONE_INPUT_XPATH));
        phoneInput.sendKeys("9999999999");

        // Шаг 13: Нажать "получить код"
        WebElement getCodeButton = wait.until(ExpectedConditions.visibilityOfElementLocated(GET_CODE_BUTTON_XPATH));
        getCodeButton.click();

        // Шаг 14: Дождаться кнопки логина и ввести код
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON_XPATH));
        WebElement codeInput = wait.until(ExpectedConditions.visibilityOfElementLocated(CODE_INPUT_XPATH));
        codeInput.sendKeys("1111");

        // Шаг 16: Нажать "войти"
        WebElement loginButton = wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BUTTON_XPATH));
        loginButton.click();

        try {
            // Ожидание, пока элемент не станет доступен
            WebElement messageButton = wait.until(ExpectedConditions.visibilityOfElementLocated(DONT_ALLOW_NOTIFICATIONS_BUTTON_XPATH));
            // Клик по элементу
            messageButton.click();
        } catch (TimeoutException e) {
            // Обработка ситуации, когда элемент не появился в течение 5 секунд
            System.out.println("Кнопка разрешения нотификаций не появилась в течении 10 секунд, пропускаем шаг");
        } catch (Exception e) {
            // Обработка других возможных исключений
            e.printStackTrace();
        }

        // Шаг 17: Дождаться загрузки экрана
        wait.until(ExpectedConditions.visibilityOfElementLocated(USER_AVATAR));

        // Шаг 18: Проверить пункты меню
        WebElement consMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CONS_MENU));
        WebElement callMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(CALL_MENU));

        Assertions.assertNotNull(consMenu, "Меню Консультация отсутвует");
        Assertions.assertTrue(consMenu.isDisplayed(), "Меню Консультация не видимо");
        Assertions.assertNotNull(callMenu, "Меню Вызов отсутвует");
        Assertions.assertTrue(callMenu.isDisplayed(), "Меню Вызов не видимо");
    }
}