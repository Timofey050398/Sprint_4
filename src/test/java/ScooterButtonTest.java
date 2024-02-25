import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.HeaderScooter;
import page_objects.OrderPageScooter;
import java.time.Duration;
import static org.junit.Assert.assertEquals;

public class ScooterButtonTest {
    private WebDriver driver;

    @Test
    public void refFromOrderFormPageFlowTest(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        HeaderScooter objHeader = new HeaderScooter(driver);
        objHeader.clickOrderButton();
        OrderPageScooter objOrderPage = new OrderPageScooter(driver);
        objOrderPage.waitForLoadFirstScreen();
        objHeader.clickScooterButton();
        String result = driver.getCurrentUrl();
        assertEquals("URL страницы не совпадает с ожидаемым","https://qa-scooter.praktikum-services.ru/", result);
    }

    @Test
    public void refFromOrderStatusPageFlowTest(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        HeaderScooter objHeader = new HeaderScooter(driver);
        objHeader.checkOrderStatus("aaa");
        new WebDriverWait(driver, Duration.ofSeconds(2));
        objHeader.clickScooterButton();
        String result = driver.getCurrentUrl();
        assertEquals("URL страницы не совпадает с ожидаемым","https://qa-scooter.praktikum-services.ru/", result);
    }
    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
