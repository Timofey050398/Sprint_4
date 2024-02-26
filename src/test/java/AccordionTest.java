import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import page_objects.MainPageScooter;

import static constants.ScooterUrls.SCOOTER_MAIN_PAGE_URL;
import static constants.ReturnDriver.RETURN_CHROME_DRIVER;
import static org.junit.Assert.assertEquals;
@RunWith(Parameterized.class)
public class AccordionTest {
    private final String question;
    private final String answer;
    public AccordionTest(String question, String answer){
        this.question = question;
        this.answer = answer;
    }
    @Parameterized.Parameters
    public static Object[][] getAnswers() {
        return new Object[][] {
                {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"Хочу сразу несколько самокатов! Так можно?","Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"Как рассчитывается время аренды?","Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"Можно ли заказать самокат прямо на сегодня?","Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"Можно ли продлить заказ или вернуть самокат раньше?","Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"Вы привозите зарядку вместе с самокатом?","Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"Можно ли отменить заказ?","Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"Я жизу за МКАДом, привезёте?","Да, обязательно. Всем самокатов! И Москве, и Московской области."},
        };
    }
    @Test
    public void isAnswerCorrect() {
        WebDriver driver = RETURN_CHROME_DRIVER();
        driver.get(SCOOTER_MAIN_PAGE_URL);
        MainPageScooter objMainPage = new MainPageScooter(driver);
        objMainPage.clickAccordionItem(question);
        objMainPage.waitForLoadAccordionAnswer(question);
        String result = objMainPage.getAccordionAnswerText(question);
        assertEquals("Текст ответа не соответсвует ожиданиям",answer, result);
        driver.quit();
    }
}
