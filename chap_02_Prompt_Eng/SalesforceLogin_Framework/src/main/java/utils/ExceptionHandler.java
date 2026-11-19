package utils;

import exceptions.LoginException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriverException;

public final class ExceptionHandler {

    private ExceptionHandler() {
    }

    public static void handleActionException(String actionName, Exception exception) {
        if (exception instanceof NoSuchElementException) {
            throw new LoginException(actionName + " failed - element not found: "
                    + exception.getMessage(), exception);
        } else if (exception instanceof TimeoutException) {
            throw new LoginException(actionName + " failed - operation timed out: "
                    + exception.getMessage(), exception);
        } else if (exception instanceof WebDriverException) {
            throw new LoginException(actionName + " failed - webdriver error: "
                    + exception.getMessage(), exception);
        } else {
            throw new LoginException(actionName + " failed - unexpected error: "
                    + exception.getMessage(), exception);
        }
    }
}
