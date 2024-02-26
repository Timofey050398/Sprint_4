package page_objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CookieScooter {
    private WebDriver driver;
    // локатор кнопки «Cookie»
    private By cookieButton = By.className("App_CookieButton__3cvqF");
    public CookieScooter(WebDriver driver){
        this.driver = driver;
    }
    public void clickCookieButton(){
        driver.findElement(cookieButton).click();
    }
}
