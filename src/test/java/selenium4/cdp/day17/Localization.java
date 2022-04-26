package selenium4.cdp.day17;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class Localization {
    @Test
    public void testLocalization() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        Map<String, Object> coordinates = new HashMap<>();
        coordinates.put("latitude", 39);
        coordinates.put("longitude", 116);
        coordinates.put("accuracy", 100);
        driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
        driver.get("https://google.com");
        driver.findElement(By.name("q")).sendKeys("netflix");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        Thread.sleep(10000L);
        driver.quit();
    }
}
