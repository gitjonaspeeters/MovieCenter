package domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestFilmVoegToe {
    private WebDriver driver;
        String url= "http://localhost:8080/";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver","D:\\handige intelij downloads\\chromedriver.exe");
        driver =  new ChromeDriver();
        driver.get(url + "voeg_toe.jsp");
    }
    @After
    public void clean(){
        driver.quit();
    }



    @Test
    public void test_Form_Geeft_Error_Alle_Iputs_Empty(){
        WebElement titel= driver.findElement(By.id("titel"));
        titel.clear();
        titel.sendKeys("");

        WebElement genre= driver.findElement(By.id("genre"));
        genre.sendKeys("");

        WebElement rating= driver.findElement(By.id("Rating"));
        rating.clear();
        rating.sendKeys("");

        WebElement duur= driver.findElement(By.id("Duur"));
        duur.clear();
        duur.sendKeys("");

        driver.findElement(By.id("Submit")).click();


        assertEquals("Voeg toe", driver.getTitle());
        ArrayList<WebElement> lis= (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis, "Vul correcte naam in."));
        assertTrue(containsWebElementsWithText(lis,"Geef juiste rating."));
        assertTrue(containsWebElementsWithText(lis, "Geef juiste duur(in minuten)."));

    }

    @Test
    public void test_naam_ingevuld_rest_leeg(){
        WebElement titel= driver.findElement(By.id("titel"));
        titel.clear();
        titel.sendKeys("Jonathan");

        WebElement genre= driver.findElement(By.id("genre"));
        genre.sendKeys("");

        WebElement rating= driver.findElement(By.id("Rating"));
        rating.clear();
        rating.sendKeys("");

        WebElement duur= driver.findElement(By.id("Duur"));
        duur.clear();
        duur.sendKeys("");

        driver.findElement(By.id("Submit")).click();


        assertEquals("Voeg toe", driver.getTitle());
        ArrayList<WebElement> lis= (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertFalse(containsWebElementsWithText(lis, "Vul correcte naam in."));
        assertTrue(containsWebElementsWithText(lis,"Geef juiste rating."));
        assertTrue(containsWebElementsWithText(lis, "Geef juiste duur(in minuten)."));


    }

    @Test
    public void test_naam_en_rating_ingevuld_rest_leeg(){
        WebElement titel= driver.findElement(By.id("titel"));
        titel.clear();
        titel.sendKeys("Spider-Man");

        WebElement rating= driver.findElement(By.id("Rating"));
        rating.clear();
        rating.sendKeys("9");

        WebElement duur= driver.findElement(By.id("Duur"));
        duur.clear();
        duur.sendKeys("");

        driver.findElement(By.id("Submit")).click();


        assertEquals("Voeg toe", driver.getTitle());
        ArrayList<WebElement> lis= (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertFalse(containsWebElementsWithText(lis, "Vul correcte naam in."));
        assertFalse(containsWebElementsWithText(lis,"Geef juiste rating."));
        assertTrue(containsWebElementsWithText(lis, "Geef juiste duur(in minuten)."));


    }




    @Test
    public void test_Form_Geeft_Geen_Error_Alle_Iputs_Juist(){
        WebElement titel= driver.findElement(By.id("titel"));
        titel.clear();
        titel.sendKeys("Erdogan");


        WebElement rating= driver.findElement(By.id("Rating"));
        rating.clear();
        rating.sendKeys("9");

        WebElement duur= driver.findElement(By.id("Duur"));
        duur.clear();
        duur.sendKeys("200");

        WebElement button= driver.findElement(By.id("Submit"));
        button.click();

        assertEquals("Overzicht", driver.getTitle());
        ArrayList<WebElement> td = (ArrayList<WebElement>) driver.findElements(By.tagName("td"));
        assertTrue(containsWebElementsWithText(td, "Erdogan"));

    }
    @Test
    public void test_Form_Geeft_Geen_Error_Als_Film_Al_Bestaat(){
        WebElement titel= driver.findElement(By.id("titel"));
        titel.clear();
        titel.sendKeys("Alex");


        WebElement rating= driver.findElement(By.id("Rating"));
        rating.clear();
        rating.sendKeys("9");

        WebElement duur= driver.findElement(By.id("Duur"));
        duur.clear();
        duur.sendKeys("200");

        WebElement button= driver.findElement(By.id("Submit"));
        button.click();


        driver.get(url + "voeg_toe.jsp");


        WebElement titel1= driver.findElement(By.id("titel"));
        titel1.clear();
        titel1.sendKeys("Alex");


        WebElement rating1= driver.findElement(By.id("Rating"));
        rating1.clear();
        rating1.sendKeys("9");

        WebElement duur1= driver.findElement(By.id("Duur"));
        duur1.clear();
        duur1.sendKeys("200");

        WebElement button1= driver.findElement(By.id("Submit"));
        button1.click();

        assertEquals("Voeg toe", driver.getTitle());
        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis, "Film bestaat al"));


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
