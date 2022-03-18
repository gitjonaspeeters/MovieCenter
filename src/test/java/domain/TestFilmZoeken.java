package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestFilmZoeken {
    private WebDriver driver;
    String urlvoegtoe= "http://localhost:8080/voeg_toe.jsp";
    String urlzoeken= "http://localhost:8080/zoeken.jsp";


    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","D:\\handige intelij downloads\\chromedriver.exe");
        driver =  new ChromeDriver();
        driver.get(urlvoegtoe);

    }
    @After
    public void clean(){
        driver.quit();
    }

    @Test
    public void test_Zoeken_zoekt_juiste_film() {
        WebElement titel = driver.findElement(By.id("titel"));
        titel.clear();
        titel.sendKeys("Alex");

        WebElement rating = driver.findElement(By.id("Rating"));
        rating.clear();
        rating.sendKeys("9");

        WebElement duur = driver.findElement(By.id("Duur"));
        duur.clear();
        duur.sendKeys("200");

        WebElement button = driver.findElement(By.id("Submit"));
        button.click();

        driver.get(urlvoegtoe);

        WebElement titel1 = driver.findElement(By.id("titel"));
        titel1.clear();
        titel1.sendKeys("Jonas");

        WebElement rating1 = driver.findElement(By.id("Rating"));
        rating1.clear();
        rating1.sendKeys("7");

        WebElement duur1 = driver.findElement(By.id("Duur"));
        duur1.clear();
        duur1.sendKeys("300");

        WebElement button1 = driver.findElement(By.id("Submit"));
        button1.click();

        driver.get(urlzoeken);

        WebElement titel3 = driver.findElement(By.id("titel"));
        titel3.clear();
        titel3.sendKeys("Jonas");

        WebElement button2 = driver.findElement(By.id("submit"));
        button2.click();

        assertEquals("Overzicht", driver.getTitle());
        ArrayList<WebElement> td = (ArrayList<WebElement>) driver.findElements(By.tagName("td"));
        assertTrue(containsWebElementsWithText(td, "Jonas"));
    }

    @Test
    public void test_Zoeken_error_zoekt_foute_film() {
        WebElement titel = driver.findElement(By.id("titel"));
        titel.clear();
        titel.sendKeys("Alex");

        WebElement rating = driver.findElement(By.id("Rating"));
        rating.clear();
        rating.sendKeys("9");

        WebElement duur = driver.findElement(By.id("Duur"));
        duur.clear();
        duur.sendKeys("200");

        WebElement button = driver.findElement(By.id("Submit"));
        button.click();

        driver.get(urlvoegtoe);

        WebElement titel1 = driver.findElement(By.id("titel"));
        titel1.clear();
        titel1.sendKeys("Jonas");

        WebElement rating1 = driver.findElement(By.id("Rating"));
        rating1.clear();
        rating1.sendKeys("7");

        WebElement duur1 = driver.findElement(By.id("Duur"));
        duur1.clear();
        duur1.sendKeys("300");

        WebElement button1 = driver.findElement(By.id("Submit"));
        button1.click();

        driver.get(urlzoeken);

        WebElement titel3 = driver.findElement(By.id("titel"));
        titel3.clear();
        titel3.sendKeys("Senne");

        WebElement button2 = driver.findElement(By.id("submit"));
        button2.click();

        assertEquals("Zoeken", driver.getTitle());
        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis, "Film bestaat niet"));
    }
    private boolean containsWebElementsWithText(ArrayList<WebElement> e, String text) {
        for (int i = 0; i < e.size(); i++) {
            if(e.get(i).getText().equals(text)){
                return true;
            }
        }
        return false;
    }



}
