import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page_objects.HeaderScooter;
import java.util.Set;
import static org.junit.Assert.assertEquals;

public class YandexButtonTest {
    private WebDriver driver;

    @Test
    public void refToYandexMainPageTest(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        HeaderScooter objHeader = new HeaderScooter(driver);
        objHeader.clickYandexButton();
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        String result = driver.getCurrentUrl();
        Set<String> allWindows = driver.getWindowHandles();
        assertEquals("Страница открылась в текущей вкладке",allWindows.size(), 2);
        assertEquals("URL новой страницы не совпадает с ожидаемым","https://yandex.ru/", result);
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        result = driver.getCurrentUrl();
        assertEquals("URL начальной страницы не совпадает с ожидаемым","https://qa-scooter.praktikum-services.ru/", result);
    }
    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
