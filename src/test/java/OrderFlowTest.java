import org.junit.After;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import page_objects.CookieScooter;
import page_objects.HeaderScooter;
import page_objects.MainPageScooter;
import page_objects.OrderPageScooter;

import static org.junit.Assert.assertEquals;

public class OrderFlowTest {
    private WebDriver driver;

    @Test
    public void positiveOrderCaseStartedFromMainPage(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox","--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        MainPageScooter objMainPage = new MainPageScooter(driver);
        CookieScooter objCookie = new CookieScooter(driver);
        objCookie.clickCookieButton();
        objMainPage.clickOrderButton();
        OrderPageScooter objOrderPage = new OrderPageScooter(driver);
        objOrderPage.fillFirstScreenForm("Тимофей","Литвинов","улица два",5,"+79999999999" );
        objOrderPage.clickFurtherButton();
        objOrderPage.fillSecondScreenForm("3-е марта 2024","двое суток","both","");
        objOrderPage.clickOrderButton();
        objOrderPage.confirmOrder();
        String result = objOrderPage.getModalHeaderText();
        assertEquals("Отсутсвует ожидаемая надпись в модальном окне","Заказ оформлен", result);
    }

    @Test
    public void positiveOrderCaseStartedFromHeader(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://qa-scooter.praktikum-services.ru/");
        HeaderScooter objHeader = new HeaderScooter(driver);
        CookieScooter objCookie = new CookieScooter(driver);
        objCookie.clickCookieButton();
        objHeader.clickOrderButton();
        OrderPageScooter objOrderPage = new OrderPageScooter(driver);
        objOrderPage.fillFirstScreenForm("Иван","Иванов","улица три",10,"89999999999" );
        objOrderPage.clickFurtherButton();
        objOrderPage.fillSecondScreenForm("18-е февраля 2024","семеро суток","black","hello");
        objOrderPage.clickOrderButton();
        objOrderPage.confirmOrder();
        String result = objOrderPage.getModalHeaderText();
        assertEquals("Отсутсвует ожидаемая надпись в модальном окне","Заказ оформлен", result);
    }
    @After
    public void teardown() {
        // Закрой браузер
        driver.quit();
    }
}
