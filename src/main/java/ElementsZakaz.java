package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;


public class ElementsZakaz {

    private final WebDriver driver;
    //локатор первая кнопка Заказать
    private final By zakaz1button= By.cssSelector(".Button_Button__ra12g");
    //локатор вторая кнопка Заказать
    private final By zakaz2button= By.cssSelector(".Button_Middle__1CSJM");
    //локатор на поле имя
    private final By nameVvod = By.xpath(".//input[@placeholder='* Имя']");
    //локатор на поле фамилия
    private final By lastNameVvod = By.xpath(".//input[@placeholder='* Фамилия']");
    //локатор на поле адреса
    private final By adresVvod = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    //локатор на поле станции метро
    private final By metroVvod = By.xpath(".//input[@placeholder='* Станция метро']");
    //локатор на поле номера телефона
    private final By telefonVvod = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    //локатор на кнопку далее
    private final By daleeButton = By.xpath(".//button[text()='Далее']");
    //локатор на поле ввода даты
    private final By dataPole = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    //локатор на элемент даты
    private final By dataVvod = By.xpath(".//div[@aria-label='Choose воскресенье, 27-е апреля 2025 г.']");
    //локатор на поле ввода срока аренды
    private final By srokVvod = By.cssSelector(".Dropdown-placeholder");
    //локатор на выбор черного цвета
    private final By blackVvod = By.id("black");
    //локатор на выбор серого цвета
    private final By grayVvod = By.id("grey");
    //локатор на поле комментария
    private final By comentVvod = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    //локатор на кнопку заказать
    private final By zakazButton = By.xpath("//button[text()='Заказать' and contains(@class, 'Button_Middle__1CSJM')]");
    //локатор на кнопку Да
    private final By da = By.xpath("//button[text()='Да' and contains(@class, 'Button_Middle__1CSJM')]");
    //локатор на окно подтверждения заказа
    private final By oknoPodtverjdeniyaZakaza = By.xpath("//button[text()='Заказ оформлен' and contains(@class, 'Order_ModalHeader__3FDaJ')]");


    //констр класса
    public ElementsZakaz(WebDriver driver) {
        this.driver = driver;
    }

    // метод находим первую кнопку заказ и кликаем по ней
    public void clickZakaz1(){
        driver.findElement(zakaz1button).click();
    }
    //метод находим вторую кнопку заказ и кликаем по ней
    public void clickZakaz2() {
        driver.findElement(zakaz2button).click();
    }

    //метод ввода станции метро
    public void vvodMetro(String data) {
        driver.findElement(metroVvod).click();
        driver.findElement(metroVvod).sendKeys(data);
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.elementToBeClickable(
                        By.xpath("//div[contains(text(), '" + data + "')]")));
        driver.findElement(By.xpath("//div[contains(text(), '" + data + "')]")).click();
    }

    //методы заполнение формы1 заказа
    public void zapolnenie1page(String name, String lastName, String adres, String metro, String telefon){
        driver.findElement(nameVvod).sendKeys(name);
        driver.findElement(lastNameVvod).sendKeys(lastName);
        driver.findElement(adresVvod).sendKeys(adres);
        vvodMetro(metro);
        driver.findElement(telefonVvod).sendKeys(telefon);
        driver.findElement(daleeButton).click();
    }

    //методы заполнения формы2 заказа
    public void zapolnenie2page (String data, String srok, String color, String coment){

        driver.findElement(dataPole).click();
        driver.findElement(dataVvod).click();
        driver.findElement(srokVvod).click();
        driver.findElement(By.xpath(".//div[text()='" + srok + "']")).click();
        if (color.equals("black")) {
            driver.findElement(blackVvod).click();
        }
        else {
            driver.findElement(grayVvod).click();
        }
        driver.findElement(comentVvod).sendKeys(coment);
        driver.findElement(zakazButton).click();
    }

    //в окне подтверждения нажимаем Да
    public void podtverjdenie(){
        driver.findElement(da).click();
    }
    // ждем появления окна подтверждения заказа
    public boolean oknoPodtverjdeniya() {
        //
        new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.visibilityOfElementLocated(oknoPodtverjdeniyaZakaza));
        return driver.findElement(oknoPodtverjdeniyaZakaza).isDisplayed();
    }
}