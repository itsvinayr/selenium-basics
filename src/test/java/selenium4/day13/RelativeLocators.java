package selenium4.day13;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import static org.openqa.selenium.support.locators.RelativeLocator.with;

public class RelativeLocators {
    WebDriver driver;
    @BeforeClass
    public void invokeChromeBrowser(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://amazon.in");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void testRelativeLocators(){
       driver.findElement(By.xpath("//*[@id='nav-link-accountList-nav-line-1']")).click();
        WebElement emailField = driver.findElement(By.xpath("//input[@id='ap_email']"));
        emailField.sendKeys("hello");

        // webelement screenshot
        File file = emailField.getScreenshotAs(OutputType.FILE); // write logic here

        // get dimensions of webelement
        int height = emailField.getRect().getHeight();
        int width = emailField.getRect().getWidth();
        System.out.println("Height and width are : "+height+"-"+width);

        String label = driver.findElement(with(By.tagName("label")).above(emailField)).getText().trim();
        System.out.println("Printing label"+label);
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
}
