package selenium4.cdp.day20;

import org.openqa.selenium.HasAuthentication;
import org.openqa.selenium.UsernameAndPassword;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.net.URI;
import java.util.function.Predicate;

public class BasicAuthentication {
    @Test
    public void testBasicAuthentication() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\src\\chromedriver.exe");
        ChromeDriver driver = new ChromeDriver();
        Predicate<URI> predicate = uri -> uri.getHost().contains("httpbin.org");

        ((HasAuthentication)driver).register(predicate, UsernameAndPassword.of("foo", "bar"));
        driver.get("http://httpbin.org/basic-auth/foo/bar");
        /*LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        List<LogEntry> all = logEntries.getAll();
        for(LogEntry entry:all)
            System.out.println(entry.getMessage());*/
        Thread.sleep(10000L);
        driver.quit();
    }
}
