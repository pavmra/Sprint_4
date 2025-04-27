package test;


import org.example.ElementsZakaz;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)

public class ZakazTest {

    private WebDriver driver;
    private ElementsZakaz zakaz;

    private final String verhIliNiz;
    private final String name;
    private final String lastName;
    private final String adres;
    private final String metro;
    private final String telefon;
    private final String data;
    private final String srok;
    private final String color;
    private final String coment;

    public ZakazTest(String verhIliNiz, String name, String lastName, String adres,
                     String metro, String telefon, String data, String srok,
                     String color, String coment) {
        this.verhIliNiz = verhIliNiz;
        this.name = name;
        this.lastName = lastName;
        this.adres = adres;
        this.metro = metro;
        this.telefon = telefon;
        this.data = data;
        this.srok = srok;
        this.color = color;
        this.coment = coment;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                // Первый вариант
                {"verh", "Павел", "Навоев", "Минск", "Белорусская", "99999999999", "01.01.2025", "двое суток", "black", "Оставить возле дома"},
                // Второй вариант
                {"niz", "Анна", "Навоев", "Минск", "Белорусская", "88888888888", "02.01.2025", "трое суток", "grey", "Оставить на парковке"}
        });
    }


    @Before
    public void setup() {
        driver = new ChromeDriver();
        driver.get("https://qa-scooter.praktikum-services.ru/");
        zakaz = new ElementsZakaz(driver);
        driver.findElement(By.cssSelector(".App_CookieButton__3cvqF")).click();
    }

    @Test
    public void zakazTest(){
        if (verhIliNiz.equals("verh")){
            zakaz.clickZakaz1();
        } else {
            zakaz.clickZakaz2();
        }

        //вставляем данные на первой странице
        zakaz.zapolnenie1page(name, lastName, adres, metro, telefon);
        //вставляем данные на второй странице
        zakaz.zapolnenie2page(data, srok, color, coment);
        zakaz.oknoPodtverjdeniya();
        zakaz.podtverjdenie();
        assertFalse("Нет окна подтверждения заказа", zakaz.oknoPodtverjdeniya());
    }

    @After
    public void teardown()
    {
        driver.quit();
    }


}
