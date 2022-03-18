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

public class TestJournal{
    WebDriver driver;

    private String url = "http://cyclone3.uclllabs.be:8081/Peeters-Jonas-1.0-SNAPSHOT/Controller";

    @Before
    public void setUp() throws Exception {
        // PAS DIT AAN NAAR JE EIGEN CHROME OF GECKODRIVER
        System.setProperty("webdriver.chrome.driver", "D:\\handige intelij downloads\\chromedriver.exe");
        driver = new ChromeDriver();

    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    @Test
    public void link_logboek_is_marked_as_actual() {
        driver.get(url+"?command=journal");
        WebElement link = driver.findElement(By.id("current"));
        assertEquals("Journal",link.getText());
    }
    // PAS DIT AAN NAAR JE EIGEN COMMAND OM NAAR JE ZOEK PAGINA TE NAVIGEREN
    @Test
    public void link_zoekForm_is_marked_as_actual() {
        driver.get(url+"?command=zoekenpagina");
        WebElement link = driver.findElement(By.id("current"));
        assertEquals("Zoeken", link.getText());
    }

    // SCHRIJF EEN TEST FUNCTIE DIE NAGAAT OF DE OPGEZOCHDE WAARDE IN JE LOGBOEK TERECHT KOMT
    @Test
    public void searched_value_is_added_to_logboek(){
        driver.get(url + "?command=voegtoeForm");
        WebElement titel= driver.findElement(By.id("titel"));
        titel.clear();
        titel.sendKeys("Zonny");


        WebElement rating= driver.findElement(By.id("Rating"));
        rating.clear();
        rating.sendKeys("9");

        WebElement duur= driver.findElement(By.id("Duur"));
        duur.clear();
        duur.sendKeys("200");

        WebElement button= driver.findElement(By.id("Submit"));
        button.click();
        driver.get(url+"?command=zoekenpagina");
        WebElement input = driver.findElement(By.id("titel"));
        input.sendKeys("Zonny");
        WebElement button1 = driver.findElement(By.id("submit"));
        button1.click();
        driver.get(url+"?command=journal");
        assertTrue(cont((ArrayList<WebElement>)driver.findElements(By.tagName("td")),"Zonny"));
        driver.quit();
    }

    @Test
    public void second_browser_gives_different_logboek() {
        // go to showForm in first browser
        driver.get(url + "?command=voegtoeForm");
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
        driver.get(url+"?command=zoekenpagina");
        WebElement input = driver.findElement(By.id("titel"));
        input.sendKeys("Erdogan");
        WebElement button1 = driver.findElement(By.id("submit"));
        button1.click();
        driver.get(url+"?command=journal");
        assertTrue(cont((ArrayList<WebElement>)driver.findElements(By.tagName("td")),"Erdogan"));
        driver.quit();

        // open browser 2 and check studentForm is not in logboek
        WebDriver driver2 = new ChromeDriver();
        driver2.get(url+"?command=journal");
        assertFalse(cont((ArrayList<WebElement>)driver2.findElements(By.tagName("td")),"bob"));
        driver2.quit();

    }

    private boolean cont(ArrayList<WebElement> e, String text) {
        for (int i = 0; i < e.size(); i++) {
            if(e.get(i).getText().equals(text)){
                return true;
            }
        }
        return false;
    }
}
