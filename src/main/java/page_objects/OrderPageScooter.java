package page_objects;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Objects;

public class OrderPageScooter {
    private WebDriver driver;
    // локатор формы первого экрана
    private By firstScreen = By.xpath("//div[@class='Order_Header__BZXOb' and text()='Для кого самокат']");
    // локатор формы второго экрана
    private By secondScreen = By.xpath("//div[@class='Order_Header__BZXOb' and text()='Про аренду']");
    // локатор инпута «Имя»
    private By nameInput = By.cssSelector("input[placeholder='* Имя']");
    // локатор инпута «Фамилия»
    private By surnameInput = By.cssSelector("input[placeholder='* Фамилия']");
    // локатор инпута «Адрес»
    private By addressInput = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']");
    // локатор списка «Станция метро»
    private By undergroundPicker = By.cssSelector("input[placeholder='* Станция метро']");
    // локатор значения списка «Имя»
    private By undergroundValue;
    // локатор инпута «Телефон»
    private By phoneNumberInput = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");
    // локатор кнопки «Далее»
    private By furtherButton = By.xpath("//div[@class='Order_NextButton__1_rCA']/button[text()='Далее']");
    // локатор инпута «Дата»
    private By datePickerInput = By.cssSelector("input[placeholder='* Когда привезти самокат']");
    // локатор даты в календаре
    private By datePickerDay;
    //локатор календаря
    private By datePicker = By.className("react-datepicker__month-container");
    // локатор инпута «Срок»
    private By rentInput = By.xpath("//div[@class='Dropdown-placeholder' and text()='* Срок аренды']");
    //локатор списка вариантов
    private By dropdownOptions;
    //локатор чек-бокса "чёрный жемчуг"
    private By blackCheckBox = By.id("grey");
    //локатор чек-бокса "серая безысходоность"
    private By greyCheckBox = By.id("black");
    // локатор инпута «Комментарий»
    private By commentInput = By.cssSelector("input[placeholder='Комментарий для курьера']");
    // локатор кнопки «Заказать»
    private By orderButton = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
    //Локатор заголовка модалки
    private By orderModalHeader = By.className("Order_ModalHeader__3FDaJ");
    //Локатор кнопки подтверждения заказа
    private By confirmOrderButton = By.xpath("//button[text()='Да']");

    public OrderPageScooter(WebDriver driver){
        this.driver = driver;
    }
    //Метод, ожидающий загрузку формы первого экрана
    public void waitForLoadFirstScreen(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(firstScreen));
    }
    //Метод, устанавливающий имя
    public void setName(String name){
        driver.findElement(nameInput).sendKeys(name);
    }
    //Метод, устанавливающий фамилия
    public void setSurname(String surname){
        driver.findElement(surnameInput).sendKeys(surname);
    }
    //Метод, устанавливающий адрес
    public void setAddress(String address){
        driver.findElement(addressInput).sendKeys(address);
    }
    //Метод, устанавливающий станцию метро
    public void setUndergroundStation(int stationNumber){
        int elementNumber = stationNumber-1;
        driver.findElement(undergroundPicker).click();
        undergroundValue = By.xpath("//li["+elementNumber+"]");
        WebElement undergroundStation = driver.findElement(undergroundValue);
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", undergroundStation);
        undergroundStation.click();
    }
    //Метод, устанавливающий номер телефона
    public void setPhoneNumber(String phoneNumber){
        driver.findElement(phoneNumberInput).sendKeys(phoneNumber);
    }
    //Метод, заполняющий форму первого экрана
    public void fillFirstScreenForm(String name, String surname, String address, int stationNumber, String phoneNumber){
       waitForLoadFirstScreen();
       setName(name);
       setSurname(surname);
       setAddress(address);
       setUndergroundStation(stationNumber);
       setPhoneNumber(phoneNumber);
    }
    //Метод, нажимающий кнопку "Далее"
    public void clickFurtherButton(){
        driver.findElement(furtherButton).click();
    }
    //Метод, ожидающий загрузку первого экрана формы заказа
    public void waitForLoadSecondScreen(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(secondScreen));
    }
    //Метод, выбирающий дату в дейтпикере
    public void setDate(String date) {
        driver.findElement(datePickerInput).click();
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(datePicker));
        datePickerDay = By.cssSelector("[aria-label*=\""+date+"\"]");
        driver.findElement(datePickerDay).click();
    }
    //Метод, выбирающий срок аренды
    public void setRentalPeriod(String rentalPeriod){
        driver.findElement(rentInput).click();
        dropdownOptions = By.xpath("//div[@class='Dropdown-menu']/div[text()='"+rentalPeriod+"']");
        WebElement dropdownOption = driver.findElement(dropdownOptions);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", dropdownOption);
        dropdownOption.click();
    }
    //Метод, выбирающий цвет самоката
    public void setScooterColor(String colorOption){
        if(colorOption == "grey"){
            driver.findElement(greyCheckBox).click();
        }else if(colorOption =="black"){
            driver.findElement(blackCheckBox).click();
        }else if(colorOption =="both"){
            driver.findElement(greyCheckBox).click();
            driver.findElement(blackCheckBox).click();
        }
    }
    //Метод, заполняющий поле "комментарий"
    public void setComment(String comment){
        driver.findElement(commentInput).sendKeys(comment);
    }
    //Метод, активирующий кнопку "Заказать"
    public void clickOrderButton(){
        driver.findElement(orderButton).click();
    }
    //Метод, заполняющий форму второго экрана
    public void fillSecondScreenForm(String date, String rentalPeriod, String colorOption, String comment){
        waitForLoadSecondScreen();
        setDate(date);
        setRentalPeriod(rentalPeriod);
        setScooterColor(colorOption);
        setComment(comment);
    }
    //Метод, подтверждающий заказ
    public void confirmOrder(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.visibilityOfElementLocated(orderModalHeader));
        driver.findElement(confirmOrderButton).click();
    }
    //Метод, возвращающий текст заголовка модалки
    public String getModalHeaderText(){
        new WebDriverWait(driver, Duration.ofSeconds(3)).until(ExpectedConditions.invisibilityOfElementLocated(confirmOrderButton));
        return driver.findElement(orderModalHeader).getText();
    }
}
