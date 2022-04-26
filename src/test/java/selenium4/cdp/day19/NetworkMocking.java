package selenium4.cdp.day19;

import com.google.common.collect.ImmutableList;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v99.fetch.Fetch;
import org.openqa.selenium.devtools.v99.fetch.model.RequestPattern;
import org.openqa.selenium.devtools.v99.network.Network;
import org.openqa.selenium.devtools.v99.network.model.ConnectionType;
import org.openqa.selenium.devtools.v99.network.model.ErrorReason;
import org.testng.annotations.Test;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NetworkMocking {
    @Test
    public void testNetworkMocking() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Fetch.enable(Optional.empty(), Optional.empty()));
        devTools.addListener(Fetch.requestPaused(), requestPaused -> {
            if (requestPaused.getRequest().getUrl().contains("shetty")) {
                String mockedURL = requestPaused.getRequest().getUrl().replace("=shetty", "Badguy");
                System.out.println(mockedURL);
                devTools.send(Fetch.continueRequest(requestPaused.getRequestId(), Optional.of(mockedURL),
                        Optional.of(requestPaused.getRequest().getMethod()), Optional.empty(),
                        Optional.empty(), Optional.empty()));
            } else {
                devTools.send(Fetch.continueRequest(requestPaused.getRequestId(), Optional.of(requestPaused.getRequest().getUrl()),
                        Optional.of(requestPaused.getRequest().getMethod()), Optional.empty(),
                        Optional.empty(), Optional.empty()));
            }
        });
        driver.get("https://rahulshettyacademy.com/angularAppdemo");
        driver.findElement(By.xpath("//button[@routerlink='/library']")).click();
        Thread.sleep(10000L);
        driver.quit();
    }

    @Test
    public void testNetworkErrors() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        Optional<List<RequestPattern>> requestPatterns = Optional.of(Arrays.asList(new RequestPattern(Optional.of("*GetBook*"), Optional.empty(), Optional.empty())));
        devTools.send(Fetch.enable(requestPatterns, Optional.empty()));
        devTools.addListener(Fetch.requestPaused(), requestPaused -> {
            devTools.send(Fetch.failRequest(requestPaused.getRequestId(), ErrorReason.FAILED));
        });
        driver.get("https://rahulshettyacademy.com/angularAppdemo");
        driver.findElement(By.xpath("//button[@routerlink='/library']")).click();
        Thread.sleep(10000L);
        driver.quit();
    }

    @Test
    public void testBlockNetworkCalls() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.setBlockedURLs(ImmutableList.of("*jpg", "*css")));

        driver.get("https://amazon.in");
        Thread.sleep(10000L);
        driver.quit();
    }

    @Test
    public void testNetworkSpeed(){
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        devTools.send(Network.emulateNetworkConditions(false, 3000, 20000, 100000,
                Optional.of(ConnectionType.ETHERNET)));
    }
}
