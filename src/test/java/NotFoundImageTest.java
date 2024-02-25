import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page_objects.HeaderScooter;
import page_objects.TrackPageScooter;

import static org.junit.Assert.assertEquals;
public class NotFoundImageTest {
    private WebDriver driver;

    @Test
    public void refFromOrderStatusPageFlowTest(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--headless");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        HeaderScooter objHeader = new HeaderScooter(driver);
        TrackPageScooter objTrackPage = new TrackPageScooter(driver);
        String wrongNumber = "aaa";
        objHeader.checkOrderStatus(wrongNumber);
        String result = driver.getCurrentUrl();
        assertEquals("URL страницы не совпадает с ожидаемым","https://qa-scooter.praktikum-services.ru/track?t="+wrongNumber, result);
        result = objTrackPage.getNotFoundImage();
        assertEquals("Изображение не совпадает с ожидаемым","https://qa-scooter.praktikum-services.ru/assets/not-found.png", result);
    }
    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
