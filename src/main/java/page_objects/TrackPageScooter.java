package page_objects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TrackPageScooter {
    private WebDriver driver;
    //Локатор изображения "Такого заказа нет"
    private By notFoundImage = By.xpath("//div[@class='Track_NotFound__6oaoY']/img");

    public TrackPageScooter(WebDriver driver) {
        this.driver=driver;
    }

    //Получить url изображения "Такого заказа нет"
    public String getNotFoundImage(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(notFoundImage));
        return driver.findElement(notFoundImage).getAttribute("src");
    }
}
