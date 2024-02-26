import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import page_objects.*;

import static constants.ReturnDriver.RETURN_CHROME_DRIVER;
import static constants.ScooterUrls.SCOOTER_MAIN_PAGE_URL;
import static org.junit.Assert.assertEquals;



@RunWith(Parameterized.class)
public class OrderFlowTest {
    private final String startException;
    private final String firstName;
    private final String lastName;
    private final String address;
    private final int quantity;
    private final String phoneNumber;
    private final String date;
    private final String duration;
    private final String color;
    private final String message;


    public OrderFlowTest(String startException, String firstName, String lastName, String address, int quantity, String phoneNumber, String date, String duration, String color, String message){
        this.startException = startException;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.quantity = quantity;
        this.phoneNumber = phoneNumber;
        this.date = date;
        this.duration = duration;
        this.color = color;
        this.message = message;
    }


    @Parameterized.Parameters
    public static Object[][] data() {
        return new Object[][]{
                {"Header","Тимофей", "Литвинов", "улица два", 5, "+79999999999", "3-е марта 2024", "двое суток", "both", ""},
                {"MainPage", "Иван", "Иванов", "улица три", 10, "89999999999", "18-е февраля 2024", "семеро суток", "black", "hello"}
        };
    }

    @Test
    public void positiveOrderCase(){
        WebDriver driver = RETURN_CHROME_DRIVER();
        driver.get(SCOOTER_MAIN_PAGE_URL);
        CookieScooter objCookie = new CookieScooter(driver);
        objCookie.clickCookieButton();
        if(startException == "MainPage") {
            MainPageScooter objPage = new MainPageScooter(driver);
            objPage.clickOrderButton();
        } else if (startException == "Header"){
            HeaderScooter objPage = new HeaderScooter(driver);
            objPage.clickOrderButton();
        } else {
            throw new IllegalArgumentException("Invalid startException value");
        }
        OrderPageScooter objOrderPage = new OrderPageScooter(driver);
        objOrderPage.fillFirstScreenForm(firstName,lastName,address,quantity,phoneNumber );
        objOrderPage.clickFurtherButton();
        objOrderPage.fillSecondScreenForm(date,duration,color,message);
        objOrderPage.confirmRent();
        String result = objOrderPage.getModalHeaderText();
        assertEquals("Отсутсвует ожидаемая надпись в модальном окне","Заказ оформлен", result);
        driver.quit();
    }
}




