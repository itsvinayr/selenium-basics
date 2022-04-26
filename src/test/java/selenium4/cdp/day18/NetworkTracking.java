package selenium4.cdp.day18;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v99.network.Network;
import org.openqa.selenium.devtools.v99.network.model.Request;
import org.openqa.selenium.devtools.v99.network.model.Response;
import org.testng.annotations.Test;

import java.util.Optional;

public class NetworkTracking {
    @Test
    public void networkTracking() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        DevTools devTools = driver.getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(),
                Optional.empty()));

        devTools.addListener(Network.requestWillBeSent(), requestWillBeSent -> {
            Request request = requestWillBeSent.getRequest();
            System.out.println("Requests : "+request.getUrl());
        });

        devTools.addListener(Network.responseReceived(), responseReceived -> {
            Response response = responseReceived.getResponse();
            System.out.println("Response : "+response.getUrl());
            System.out.println("Response : "+response.getStatus());
            if(response.getStatus().toString().startsWith("4")){
                System.out.println("Response : "+response.getUrl());
            }
        });

        driver.get("https://amazon.in");
        Thread.sleep(10000L);
        driver.quit();
    }
}
