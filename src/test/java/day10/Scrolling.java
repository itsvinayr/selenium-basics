package day10;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Scrolling {
    WebDriver driver;
    @BeforeClass
    public void invokeChromeBrowser(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void testScrolling() throws InterruptedException {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("window.scrollBy(0,500)");
        Thread.sleep(5000L);
        javascriptExecutor.executeScript("document.querySelector(\".tableFixHead\").scrollTop=5000");
        Thread.sleep(5000L);
        // to scroll till end of page
        javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
}
