package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public final class WaitUtils {

    private final WebDriverWait wait;

    public WaitUtils(WebDriver driver, int explicitTimeoutSeconds) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(explicitTimeoutSeconds));
    }

    public WebElement waitForElementVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitForElementPresence(WebElement element) {
        wait.until(ExpectedConditions.presenceOfElementLocated(
                (By) ((org.openqa.selenium.support.pagefactory.internal.LocatingElementHandler)
                        java.lang.reflect.Proxy.getInvocationHandler(element))
                        .getLocator()));
        return element;
    }

    public WebElement waitForElementClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForTextToBePresent(WebElement element, String text) {
        wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }

    public boolean waitForElementInvisible(By locator) {
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForUrlToContain(String fraction) {
        wait.until(ExpectedConditions.urlContains(fraction));
    }

    public boolean waitForElementPresence(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator)) != null;
    }
}
