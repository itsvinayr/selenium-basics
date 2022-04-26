package day4;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class HandlingDropDowns {
    WebDriver driver;
    @BeforeClass
    public void invokeChromeBrowser(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://amazon.in");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void testDropdown(){
        WebElement searchDropDown = driver.findElement(By.xpath("//*[@id='searchDropdownBox']"));
        Select staticDropdown = new Select(searchDropDown);
        System.out.println("default selected option : "+staticDropdown.getFirstSelectedOption().getText());
        staticDropdown.selectByIndex(5);
        System.out.println("selected option : "+staticDropdown.getFirstSelectedOption().getText());
        staticDropdown.selectByValue("search-alias=stripbooks");
        System.out.println("selected option : "+staticDropdown.getFirstSelectedOption().getText());
        staticDropdown.selectByVisibleText("Furniture");
        System.out.println("selected option : "+staticDropdown.getFirstSelectedOption().getText());
    }

    @Test
    public void handlingDynamicDropdown() throws InterruptedException {
        driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("la");
        Thread.sleep(10000L);
        List<WebElement> elements = driver.findElements(By.cssSelector("div.autocomplete-results-container"));
        for(WebElement element: elements){
            System.out.println(element.getText().trim());
        }
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
}
