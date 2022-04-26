package day7;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class ActionTests {
    WebDriver driver;
    @BeforeClass
    public void invokeChromeBrowser(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://amazon.in");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void testMouseHover() throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement element= driver.findElement(By.xpath("//*[text()='Hello, Sign in']"));
        actions.moveToElement(element).build().perform();
        Thread.sleep(5000L);
    }

    @Test
    public void testCompositeActions() throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement element= driver.findElement(By.id("twotabsearchtextbox"));
        actions.moveToElement(element).click().keyDown(Keys.SHIFT).sendKeys("macbook").doubleClick().build().perform();
        Thread.sleep(5000L);
    }

    @Test
    public void testRightClick() throws InterruptedException {
        Actions actions = new Actions(driver);
        WebElement element= driver.findElement(By.xpath("//*[text()='Hello, Sign in']"));
        actions.moveToElement(element).contextClick().build().perform();
        Thread.sleep(5000L);
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
}
