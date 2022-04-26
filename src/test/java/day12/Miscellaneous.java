package day12;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.time.Duration;

public class Miscellaneous {
    WebDriver driver;
    @BeforeClass
    public void invokeChromeBrowser(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\chromedriver.exe");
        driver = new ChromeDriver(chromeOptions);
    }

    @Test
    public void testForCerts() throws InterruptedException {
        driver.get("https://expired.badssl.com/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        Thread.sleep(5000L);
        System.out.println(driver.getTitle());
    }

    @Test
    public void simpleFeature(){
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
    }

    @Test
    public void validateBrokenLinks() throws IOException {
        String url = "https://google.com/";
        HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(url).openConnection();
        httpURLConnection.setRequestMethod("HEAD");
        httpURLConnection.connect();
        int responseCode = httpURLConnection.getResponseCode();
        System.out.println("response code is "+responseCode);
    }

    @Test
    public void softAssert(){
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(false);
        softAssert.assertTrue(false);
        softAssert.assertTrue(false);
        softAssert.assertAll();
    }

    @AfterClass
    public void closeBrowser(){
        driver.quit();
    }
}
