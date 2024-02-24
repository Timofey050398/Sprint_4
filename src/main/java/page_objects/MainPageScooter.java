package page_objects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPageScooter {
    private WebDriver driver;
    // локатор кнопки «Заказать»
    private By orderButton = By.xpath("//*[@class='Home_ThirdPart__LSTEE']//button[text()='Заказать']");
    ///Локатор элемента вопроса
    private By question;
    //Локатор текста ответа
    private By answer;

    public MainPageScooter(WebDriver driver){
        this.driver = driver;
    }
    private void initializeAccordionItems(String accordionText){
       question = By.xpath("//*[@class='accordion__button' and text()='"+accordionText+"']");
       answer = By.xpath("//div[@class='accordion__panel' and @aria-labelledby='" + driver.findElement(question).getAttribute("id") + "']/p");
    }
    //Метод, нажимающий кнопку заказать
    public void clickOrderButton() {
        driver.findElement(orderButton).click();
    }
    //Метод, раскрывающий аккордеон
    public void clickAccordionItem(String accordeonText) {
        initializeAccordionItems(accordeonText);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", question);
        driver.findElement(question).click();
    }
    //Метод, ожидающий загрузку ответа аккордеона
    public void waitForLoadAccordionAnswer(String accordeonText) {
        initializeAccordionItems(accordeonText);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(answer));
    }
    //Метод, получающий текст аккордеона
    public String getAccordionAnswerText(String accordeonText){
        initializeAccordionItems(accordeonText);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", answer);
        return driver.findElement(answer).getText();
    }
}
