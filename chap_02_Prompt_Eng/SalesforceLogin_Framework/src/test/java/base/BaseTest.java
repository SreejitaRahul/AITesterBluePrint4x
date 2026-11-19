package base;

import config.ConfigReader;
import drivers.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = WebDriverFactory.createDriver();
        driver.get(ConfigReader.getUrl());
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
