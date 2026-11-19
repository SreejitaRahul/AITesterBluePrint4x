package drivers;

import config.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public final class WebDriverFactory {

    private WebDriverFactory() {
    }

    public static WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(
                java.time.Duration.ofSeconds(ConfigReader.getImplicitTimeout()));
        driver.manage().timeouts().pageLoadTimeout(
                java.time.Duration.ofSeconds(ConfigReader.getPageLoadTimeout()));
        return driver;
    }
}
