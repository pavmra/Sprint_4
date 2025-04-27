package test;

import org.example.ElementsGlavnaya;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;



public class GlavnayaTest {
    private WebDriver driver;
    private ElementsGlavnaya glavnaya;

    //массив ожидаемых ответов
    private final List<String> pravilnyOtvet = Arrays.asList(
            "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
            "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
            "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
            "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
            "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
            "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
            "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
            "Да, обязательно. Всем самокатов! И Москве, и Московской области."
    );


    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        glavnaya = new ElementsGlavnaya(driver);
        driver.findElement(By.cssSelector(".App_CookieButton__3cvqF")).click();
    }

    @Test
    public void elementsGlavnaya() {

        glavnaya.scrollToVoprosy();

        for (int i = 0; i < pravilnyOtvet.size(); i++) {
            glavnaya.clickPoVoprosy(i);
            String otvet = glavnaya.getOtvet(i);
            assertEquals("В вопросе " + (i + 1) + " неправильный ответ", pravilnyOtvet.get(i), otvet);
        }
    }

    @After
    public void teardown(){
        driver.quit();
    }

}
