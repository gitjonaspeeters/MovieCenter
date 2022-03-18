package domain;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class ValidHtmlTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        // vul hier JOUW pad naar chromedriver in
        // Voor Windows (vergeet "\" niet te escapen met een tweede "\")
//		System.setProperty("webdriver.chrome.driver", "C:\\Users\\...\\chromedriver.exe");
        // Voor mac:
        System.setProperty("webdriver.chrome.driver", "D:\\handige intelij downloads\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test // Voer deze test uit als je je project opgeladen hebt
    public void isValidHtml0() {
        assertTrue(isValidHtml("http://cyclone3.uclllabs.be:8081/Peeters-Jonas-1.0-SNAPSHOT/Controller"));
    }

    @Test // Voer deze test uit als je je project opgeladen hebt
    public void isValidHtml1() {
        assertTrue(isValidHtml("http://cyclone3.uclllabs.be:8081/Peeters-Jonas-1.0-SNAPSHOT/Controller?command=overzicht"));
    }

    @Test // Voer deze test uit als je je project opgeladen hebt
    public void isValidHtml2() {
        assertTrue(isValidHtml("http://cyclone3.uclllabs.be:8081/Peeters-Jonas-1.0-SNAPSHOT/Controller?command=voegtoepagina"));
    }

    @Test // Voer deze test uit als je je project opgeladen hebt
    public void isValidHtml3() {
        assertTrue(isValidHtml("http://cyclone3.uclllabs.be:8081/Peeters-Jonas-1.0-SNAPSHOT/Controller?command=zoekenpagina"));

    }

    @Test // Voer deze test uit als je je project opgeladen hebt
    public void isValidHtml4() {
        assertTrue(isValidHtml("http://cyclone3.uclllabs.be:8081/Peeters-Jonas-1.0-SNAPSHOT/Controller?command=verwijdercon&naam"));
    }

    @Test
    public void isValidHtml5() {

        assertTrue(isValidHtml("http://cyclone3.uclllabs.be:8081/Peeters-Jonas-1.0-SNAPSHOT/Controller?command=naaraanpassen&naam=Zonny&genre=Sci-Fi&rating=9&duur=200"));
    }
    @Test
    public void isValidHtml6() {
        assertTrue(isValidHtml("http://cyclone3.uclllabs.be:8081/Peeters-Jonas-1.0-SNAPSHOT/Controller?command=journal"));
    }

    public boolean isValidHtml(String page) {
        driver.get("https://validator.w3.org/#validate_by_uri+with_options");
        WebElement invulveld = driver.findElement(By.id("uri"));
        invulveld.sendKeys(page);
        Select dropdown = new Select(driver.findElement(By.id("uri-doctype")));
        dropdown.selectByValue("HTML5");
        WebElement button = driver.findElement(By.className("submit"));
        button.click();
        try
        {
            Thread.sleep(5000);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        try {

            WebElement button2 = driver.findElements(By.className("message_filtering")).get(1);
            button2.click();
            System.out.println(button2.getText());
            WebElement button3 = driver.findElement(By.className("hide"));
            if(button3.getText().equals("Hide all warnings")) {
                button3.click();
            }
            else {
                System.out.println("Errors");
            }

        }
        catch(IndexOutOfBoundsException ex){

            try {
                WebElement pass = driver.findElement(By.cssSelector("p.success"));
                return pass.getText().equals("Document checking completed. No errors or warnings to show.");

            }catch(NoSuchElementException exx){
                return false;
            }
        }

        try {
            WebElement pass = driver.findElement(By.cssSelector("p.success"));
            return pass.getText().equals("Document checking completed. No errors or warnings to show.");

        }
        catch(NoSuchElementException ex){
            return false;
        }
    }



}
