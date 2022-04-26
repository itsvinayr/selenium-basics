package day21;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class SauceLabs {
    @Test
    public void testSauceLabs() throws MalformedURLException, InterruptedException {

        String username = "vinay.raghumanda";
        String access_key = "453e6f4f-cd1d-41a6-98c7-577ea24c5414";

        MutableCapabilities sauceOptions = new MutableCapabilities();
        sauceOptions.setCapability(CapabilityType.BROWSER_NAME, "Chrome");
        sauceOptions.setCapability("username", username);
        sauceOptions.setCapability("access_key", access_key);
        sauceOptions.setCapability("browserVersion", "latest");

        ChromeOptions options = new ChromeOptions();
        options.setCapability("sauce:options", sauceOptions);
        URL url = new URL("https://ondemand.us-west-1.saucelabs.com/wd/hub");

        WebDriver driver = new RemoteWebDriver(url, options);

        driver.get("https://google.com");
        driver.findElement(By.name("q")).sendKeys("Saucelabs");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        Thread.sleep(10000L);
        driver.quit();
    }
}
