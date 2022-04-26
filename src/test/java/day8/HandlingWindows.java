package day8;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

public class HandlingWindows {
    WebDriver driver;
    @BeforeClass
    public void invokeChromeBrowser(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://amazon.in");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void testSwitch() throws InterruptedException {
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("macbook");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.ENTER);
        Thread.sleep(5000L);
        driver.findElement(By.xpath("//*[@data-index=3]")).click();
        Thread.sleep(5000L);
        Set<String> windowHandles = driver.getWindowHandles();
        Iterator<String> iterator = windowHandles.iterator();
        String parent = iterator.next();
        String child = iterator.next();
        driver.switchTo().window(child);
        Thread.sleep(5000L);
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
}
