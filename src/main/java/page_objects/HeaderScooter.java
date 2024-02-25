package page_objects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class HeaderScooter {
    private WebDriver driver;
    // локатор кнопки «Заказать»
    private By orderButton = By.xpath("//*[@class='Header_Header__214zg']//button[text()='Заказать']");
    // локатор кнопки «Самокат»
    private By scooterButton = By.className("Header_LogoScooter__3lsAR");
    // локатор кнопки «Узнать статус»
    private By statusButton = By.className("Header_Link__1TAG7");
    // локатор инпута номер заказа
    private By orderNumberInput = By.className("Header_Input__xIoUq");
    // локатор кнопки «Go!»
    private By goButton = By.className("Header_Button__28dPO");
    // локатор кнопки «Яндекс»
    private By yandexButton = By.className("Header_LogoYandex__3TSOI");
    public HeaderScooter(WebDriver driver){
        this.driver = driver;
    }
    //Метод, нажимающий кнопку заказать
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }
    //Метод, нажимающий кнопку "Самокат"
    public void clickScooterButton(){
        driver.findElement(scooterButton).click();
    }
    //Метод, нажимающий кнопку "Узнать статус"
    public void clickStatusButton(){
        driver.findElement(statusButton).click();
    }
    //Метод, заполняющий инпут "Статус"
    public void fillOrderNumberInput(String orderNumber){
        driver.findElement(orderNumberInput).sendKeys(orderNumber);
    }
    //Метод, нажимающий кнопку "Go!"
    public void clickGoButton(){
        driver.findElement(goButton).click();
    }
    //Метод, осуществляющий поиск заказа
    public void checkOrderStatus(String orderNumber){
        clickStatusButton();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(orderNumberInput));
        fillOrderNumberInput(orderNumber);
        clickGoButton();
    }

    public void clickYandexButton(){
        driver.findElement(yandexButton).click();
    }
}

