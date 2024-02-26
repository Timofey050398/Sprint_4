import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import page_objects.HeaderScooter;
import page_objects.OrderPageScooter;
import java.time.Duration;

import static constants.ReturnDriver.RETURN_CHROME_DRIVER;
import static constants.ScooterUrls.SCOOTER_MAIN_PAGE_URL;
import static org.junit.Assert.assertEquals;

public class ScooterButtonTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = RETURN_CHROME_DRIVER();
        driver.get(SCOOTER_MAIN_PAGE_URL);
    }

    @Test
    public void refFromOrderFormPageFlowTest(){
        HeaderScooter objHeader = new HeaderScooter(driver);
        objHeader.clickOrderButton();
        OrderPageScooter objOrderPage = new OrderPageScooter(driver);
        objOrderPage.waitForLoadFirstScreen();
        objHeader.clickScooterButton();
        String result = driver.getCurrentUrl();
        assertEquals("URL страницы не совпадает с ожидаемым", SCOOTER_MAIN_PAGE_URL, result);
    }

    @Test
    public void refFromOrderStatusPageFlowTest(){
        HeaderScooter objHeader = new HeaderScooter(driver);
        objHeader.checkOrderStatus("aaa");
        new WebDriverWait(driver, Duration.ofSeconds(2));
        objHeader.clickScooterButton();
        String result = driver.getCurrentUrl();
        assertEquals("URL страницы не совпадает с ожидаемым", SCOOTER_MAIN_PAGE_URL, result);
    }
    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
