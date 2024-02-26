import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import page_objects.HeaderScooter;
import page_objects.TrackPageScooter;

import static constants.ScooterUrls.*;
import static constants.ReturnDriver.RETURN_CHROME_DRIVER;
import static org.junit.Assert.assertEquals;
public class NotFoundImageTest {
    private WebDriver driver;

    @Test
    public void refFromOrderStatusPageFlowTest(){
        driver = RETURN_CHROME_DRIVER();
        driver.get(SCOOTER_MAIN_PAGE_URL);
        HeaderScooter objHeader = new HeaderScooter(driver);
        TrackPageScooter objTrackPage = new TrackPageScooter(driver);
        String wrongNumber = "aaa";
        objHeader.checkOrderStatus(wrongNumber);
        String result = driver.getCurrentUrl();
        assertEquals("URL страницы не совпадает с ожидаемым", SCOOTER_TRACK_PAGE_URL+wrongNumber, result);
        result = objTrackPage.getNotFoundImage();
        assertEquals("Изображение не совпадает с ожидаемым", SCOOTER_MAIN_PAGE_URL+NOT_FOUND_IMAGE_PATH, result);
    }
    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
