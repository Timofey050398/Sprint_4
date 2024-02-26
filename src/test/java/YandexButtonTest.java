import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page_objects.HeaderScooter;
import java.util.Set;

import static constants.ReturnDriver.RETURN_CHROME_DRIVER;
import static constants.ScooterUrls.SCOOTER_MAIN_PAGE_URL;
import static constants.ScooterUrls.YANDEX_URL;
import static org.junit.Assert.assertEquals;

public class YandexButtonTest {
    private WebDriver driver;

    @Test
    public void refToYandexMainPageTest(){
        driver = RETURN_CHROME_DRIVER();
        driver.get(SCOOTER_MAIN_PAGE_URL);
        HeaderScooter objHeader = new HeaderScooter(driver);
        objHeader.clickYandexButton();
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        String result = driver.getCurrentUrl();
        Set<String> allWindows = driver.getWindowHandles();
        assertEquals("Страница открылась в текущей вкладке",allWindows.size(), 2);
        assertEquals("URL новой страницы не совпадает с ожидаемым",YANDEX_URL, result);
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        result = driver.getCurrentUrl();
        assertEquals("URL начальной страницы не совпадает с ожидаемым", SCOOTER_MAIN_PAGE_URL, result);
    }
    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
