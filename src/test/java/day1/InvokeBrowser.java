package day1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class InvokeBrowser {
    @Test
    public void invokeChromeBrowser(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://amazon.in");
        System.out.println("Current URL :"+driver.getCurrentUrl());
        System.out.println("Get Title :"+driver.getTitle());
        // driver.close(); // closes current window
        driver.quit(); // closes all associated windows
    }

    @Test
    public void invokeFirefoxBrowser(){
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        driver.get("https://amazon.in");
        System.out.println("Current URL :"+driver.getCurrentUrl());
        System.out.println("Get Title :"+driver.getTitle());
        driver.close();
    }

    @Test
    public void invokeEdgeBrowser(){
        System.setProperty("webdriver.edge.driver", System.getProperty("user.dir")+"\\src\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.get("https://amazon.in");
        System.out.println("Current URL :"+driver.getCurrentUrl());
        System.out.println("Get Title :"+driver.getTitle());
        driver.close();
    }
}
