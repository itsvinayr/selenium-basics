package day2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Locators {
    WebDriver driver;
    @BeforeClass
    public void invokeChromeBrowser(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://amazon.in");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void identifyElementByID() throws InterruptedException {
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("by id");
        Thread.sleep(10000L);
        driver.findElement(By.id("twotabsearchtextbox")).clear();
    }

    @Test
    public void identifyElementByName() throws InterruptedException {
        driver.findElement(By.name("field-keywords")).sendKeys("by name");
        Thread.sleep(10000L);
        driver.findElement(By.name("field-keywords")).clear();
    }

    @Test
    public void identifyElementByClassName() throws InterruptedException {
        /*
         * nav-input nav-progressive-attribute --> two class names, try going with one
         * else
         * compound class names not permitted
         */
        driver.findElement(By.className("nav-input")).sendKeys("by class name");
        Thread.sleep(10000L);
        driver.findElement(By.className("nav-input")).clear();
    }

    @Test
    public void identifyElementByCSSSelector() throws InterruptedException {
        driver.findElement(By.cssSelector("input[id='twotabsearchtextbox']")).sendKeys("by css selector");
        Thread.sleep(10000L);
        driver.findElement(By.cssSelector("input[id='twotabsearchtextbox']")).clear();
    }

    @Test
    public void identifyElementByLinkText(){
        driver.findElement(By.linkText("Best Sellers")).click();
    }

    @Test
    public void identifyElementByXpath() throws InterruptedException {
        driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("by xpath");
        Thread.sleep(10000L);
        driver.findElement(By.xpath("//*[@id='twotabsearchtextbox']")).clear();
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
}
