package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import java.util.List;

public class ElementsGlavnaya {

    private final WebDriver driver;


    //локатор область Вопросы о важном
    private final By voprosy= By.cssSelector(".Home_FAQ__3uVm4");
    //локатор области Все вопросы
    private final By vseVoprosy = By.cssSelector(".accordion__item");
    //локатор строки ответа
    private final By strokaOtvet =By.cssSelector(".accordion__panel");
    //локатор первая кнопка Заказать
    private final By zakazOneButton= By.cssSelector(".Button_Button__ra12g");
    //локатор вторая кнопка Заказать
    private final By zakazTwoButton= By.cssSelector(".Button_Middle__1CSJM");


    //констр класса
    public ElementsGlavnaya(WebDriver driver) {
        this.driver = driver;
    }

    // метод находим первую кнопку заказ и кликаем по ней
    public void clicZakaz1(){
        driver.findElement(zakazOneButton).click();
    }
    //метод находим вторую кнопку заказ и кликаем по ней
    public void clicZakaz2() {
        driver.findElement(zakazTwoButton).click();
    }
    //метод открываем сайт
    public void open() {
        driver.get("https://qa-scooter.praktikum-services.ru/");
    }
    //метод скролим до блока вопросов
    public void scrollToVoprosy(){
        WebElement element= driver.findElement(voprosy);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }
    //метод кликает по вопросу с номером number
    public void clickPoVoprosy(int number){
        List<WebElement> voprosy = driver.findElements(vseVoprosy);
        voprosy.get(number).click();
    }
    //метод возвращает ответ на вопрос по которому кликнули
    public String getOtvet(int number) {
        List<WebElement> voprosy = driver.findElements(vseVoprosy);
        return voprosy.get(number)
                .findElement(strokaOtvet)
                .getText();
    }


}


