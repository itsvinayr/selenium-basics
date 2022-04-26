package day11;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class WebTables {
    WebDriver driver;
    @BeforeClass
    public void invokeChromeBrowser(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void testDataRetrieval(){
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id='product']/tbody/tr"));
        for(WebElement element: elements){
            List<WebElement> data = element.findElements(By.tagName("td"));
            for(WebElement value: data){
                System.out.print(value.getText().trim()+" -- ");
            }
            System.out.println();
        }
    }

    @Test
    public void testDataRetrievalUsingStreams(){
        List<WebElement> elements = driver.findElements(By.xpath("//*[@id='product']/tbody/tr"));
        for(WebElement element: elements) {
            List<WebElement> data = element.findElements(By.tagName("td"));
            data.stream().map(s->s.getText().trim()).forEach(s-> System.out.print(s+"--"));
            System.out.println();
        }
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
}
