package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestFilmAanpassen {
    private WebDriver driver;




    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","D:\\handige intelij downloads\\chromedriver.exe");
        driver =  new ChromeDriver();
        driver.get("http://cyclone3.uclllabs.be:8081/Peeters-Jonas-1.0-SNAPSHOT/Controller?command=voegtoeForm");

    }

    @After
    public void clean(){driver.quit();  }

    @Test
    public void  past_correct_aan(){
        driver.get("http://localhost:8080/Controller?command=voegtoeForm");

        WebElement titel1 = driver.findElement(By.id("titel"));
        titel1.clear();
        titel1.sendKeys("Tom");

        WebElement rating1 = driver.findElement(By.id("Rating"));
        rating1.clear();
        rating1.sendKeys("7");

        WebElement duur1 = driver.findElement(By.id("Duur"));
        duur1.clear();
        duur1.sendKeys("300");

        WebElement button1 = driver.findElement(By.id("Submit"));
        button1.click();

        driver.get("http://localhost:8080/Controller?command=overzicht");

        WebElement buttonver = driver.findElement(By.className("aanpassen"));
        buttonver.click();

        Select genre = new Select(driver.findElement(By.id("genre")));
        genre.selectByIndex(4);

        WebElement rating = driver.findElement(By.id("Rating"));
        rating.clear();
        rating.sendKeys("10");

        WebElement duur = driver.findElement(By.id("Duur"));
        duur.clear();
        duur.sendKeys("190");

        WebElement button = driver.findElement(By.id("Submit"));
        button.click();


        assertEquals("Overzicht", driver.getTitle());
        ArrayList<WebElement> td= (ArrayList<WebElement>) driver.findElements(By.tagName("td"));
        assertTrue(containsWebElementsWithText(td, "Senne"));
        assertTrue(containsWebElementsWithText(td, "Romantiek"));
        assertTrue(containsWebElementsWithText(td, "10"));
        assertTrue(containsWebElementsWithText(td, "190"));
    }

    @Test
    public void  geeft_errors_leeg(){
        driver.get("http://localhost:8080/Controller?command=voegtoeForm");

        WebElement titel1 = driver.findElement(By.id("titel"));
        titel1.clear();
        titel1.sendKeys("Senne");

        WebElement rating1 = driver.findElement(By.id("Rating"));
        rating1.clear();
        rating1.sendKeys("7");

        WebElement duur1 = driver.findElement(By.id("Duur"));
        duur1.clear();
        duur1.sendKeys("300");

        WebElement button1 = driver.findElement(By.id("Submit"));
        button1.click();

        driver.get("http://localhost:8080/Controller?command=overzicht");

        WebElement buttonver = driver.findElement(By.className("aanpassen"));
        buttonver.click();

        Select genre = new Select(driver.findElement(By.id("genre")));
        genre.selectByIndex(4);

        WebElement rating = driver.findElement(By.id("Rating"));
        rating.clear();
        rating.sendKeys("");

        WebElement duur = driver.findElement(By.id("Duur"));
        duur.clear();
        duur.sendKeys("");

        WebElement button = driver.findElement(By.id("Submit"));
        button.click();

        assertEquals("Aanpassen", driver.getTitle());
        ArrayList<WebElement> lis= (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis,"Geef juiste rating."));
        assertTrue(containsWebElementsWithText(lis, "Geef juiste duur(in minuten)."));
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
