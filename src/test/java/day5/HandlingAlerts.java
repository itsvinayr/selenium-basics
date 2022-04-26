package day5;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class HandlingAlerts {

    WebDriver driver;
    @BeforeClass
    public void invokeChromeBrowser(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void handleAlert() throws InterruptedException {
        driver.findElement(By.id("alertbtn")).click();
        Thread.sleep(5000L);
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().accept();
        Thread.sleep(5000L);
    }

    @Test
    public void handleOkAndCancelAlerts() throws InterruptedException {
        driver.findElement(By.id("confirmbtn")).click();
        Thread.sleep(5000L);
        System.out.println(driver.switchTo().alert().getText());
        driver.switchTo().alert().dismiss();
        Thread.sleep(5000L);
        driver.findElement(By.id("confirmbtn")).click();
        Thread.sleep(5000L);
        driver.switchTo().alert().accept();
        Thread.sleep(5000L);
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }

}
