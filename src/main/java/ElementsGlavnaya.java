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

    //констр класса
    public ElementsGlavnaya(WebDriver driver) {
        this.driver = driver;
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
                .findElement(By.cssSelector(".accordion__panel"))
                .getText();
    }

}


