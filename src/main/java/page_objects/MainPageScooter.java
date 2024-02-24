package page_objects;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPageScooter {
    private WebDriver driver;
    // локатор кнопки «Заказать»
    private By orderButton = By.xpath("//*[@class='Home_ThirdPart__LSTEE']//button[text()='Заказать']");
    ///Локатор элемента вопроса
    private By question1;
    //Локатор текста ответа
    private By answer;

    public MainPageScooter(WebDriver driver){
        this.driver = driver;
    }
    private void InitialazeAccordeonItems(String accordeonText){
       question1 = By.xpath("//*[@class=accordion__button] and contains(text(), "+accordeonText+"");
       answer = By.xpath("//div[@class='accordion__panel' and @aria-labelledby=" + driver.findElement(question1).getAttribute("id") + "]/p");
    }
    //Метод, нажимающий кнопку заказать
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }
    //Метод, раскрывающий аккордеон
    public void clickAccordeonItem(String accordeonText) {
        InitialazeAccordeonItems(accordeonText);
        driver.findElement(question1).click();
    }
    //Метод, ожидающий загрузку ответа аккордеона
    public void waitForLoadAccordeonAnswer(String accordeonText) {
        InitialazeAccordeonItems(accordeonText);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(answer));
    }
    //Метод, получающий текст аккордеона
    public String getAccordeonAnswerText(String accordeonText){
        InitialazeAccordeonItems(accordeonText);
        return driver.findElement(answer).getText();
    }
}
