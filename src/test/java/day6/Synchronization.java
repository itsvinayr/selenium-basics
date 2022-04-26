package day6;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.function.Function;

public class Synchronization {
    WebDriver driver;
    @BeforeClass
    public void invokeChromeBrowser(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://amazon.in");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void explicitWaits() throws InterruptedException {
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("macbook");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.ENTER);

        By locator = By.xpath("//*[@data-index=3]");
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        driver.findElement(locator).click();
        Thread.sleep(5000L);
    }

    @Test
    public void fluentWait() throws InterruptedException {
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("macbook");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.ENTER);

        Wait<WebDriver> wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(30))
                .pollingEvery(Duration.ofSeconds(30)).ignoring(NoSuchElementException.class);
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                if(driver.findElement(By.xpath("//*[@data-index=3]")).isDisplayed())
                    return driver.findElement(By.xpath("//*[@data-index=3]"));
                else
                    return null;
            }
        });

        driver.findElement(By.xpath("//*[@data-index=3]")).click();
        Thread.sleep(5000L);
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
}
