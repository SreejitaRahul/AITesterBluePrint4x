package base;

import config.ConfigReader;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public abstract class BasePage {

    protected final WebDriver driver;
    protected final WaitUtils waitUtils;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.waitUtils = new WaitUtils(driver, ConfigReader.getExplicitTimeout());
    }
}
