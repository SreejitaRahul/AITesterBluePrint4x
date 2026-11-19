package pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.ExceptionHandler;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@id='username']")
    private WebElement usernameInput;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@id='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@id='rememberUn']")
    private WebElement rememberMeCheckbox;

    @FindBy(xpath = "//div[@id='error']")
    private WebElement errorMessage;

    @FindBy(xpath = "//a[contains(@class, 'forgot-password')]")
    private WebElement forgotPasswordLink;

    private static final By ERROR_MESSAGE_LOCATOR = By.xpath("//div[@id='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void enterUsername(String username) {
        try {
            waitUtils.waitForElementVisible(usernameInput);
            usernameInput.clear();
            usernameInput.sendKeys(username);
        } catch (Exception exception) {
            ExceptionHandler.handleActionException("Enter username", exception);
        }
    }

    public void enterPassword(String password) {
        try {
            waitUtils.waitForElementVisible(passwordInput);
            passwordInput.clear();
            passwordInput.sendKeys(password);
        } catch (Exception exception) {
            ExceptionHandler.handleActionException("Enter password", exception);
        }
    }

    public void clickLoginButton() {
        try {
            waitUtils.waitForElementClickable(loginButton);
            loginButton.click();
        } catch (Exception exception) {
            ExceptionHandler.handleActionException("Click login button", exception);
        }
    }

    public void clickRememberMe() {
        try {
            waitUtils.waitForElementClickable(rememberMeCheckbox);
            if (!rememberMeCheckbox.isSelected()) {
                rememberMeCheckbox.click();
            }
        } catch (Exception exception) {
            ExceptionHandler.handleActionException("Click remember me", exception);
        }
    }

    public boolean isRememberMeSelected() {
        try {
            waitUtils.waitForElementVisible(rememberMeCheckbox);
            return rememberMeCheckbox.isSelected();
        } catch (Exception exception) {
            ExceptionHandler.handleActionException("Check remember me selection", exception);
            return false;
        }
    }

    public void doLogin(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public void doLoginWithRememberMe(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickRememberMe();
        clickLoginButton();
    }

    public boolean isErrorMessageDisplayed() {
        try {
            waitUtils.waitForElementVisible(errorMessage);
            return errorMessage.isDisplayed();
        } catch (Exception exception) {
            return false;
        }
    }

    public String getErrorMessageText() {
        try {
            waitUtils.waitForElementVisible(errorMessage);
            return errorMessage.getText().trim();
        } catch (Exception exception) {
            ExceptionHandler.handleActionException("Read error message", exception);
            return "";
        }
    }

    public boolean isForgotPasswordLinkDisplayed() {
        try {
            waitUtils.waitForElementVisible(forgotPasswordLink);
            return forgotPasswordLink.isDisplayed();
        } catch (Exception exception) {
            return false;
        }
    }

    public boolean isLoginPageDisplayed() {
        try {
            waitUtils.waitForElementVisible(usernameInput);
            return usernameInput.isDisplayed() && passwordInput.isDisplayed();
        } catch (Exception exception) {
            return false;
        }
    }

    public boolean isErrorElementAbsent() {
        try {
            return waitUtils.waitForElementInvisible(ERROR_MESSAGE_LOCATOR);
        } catch (Exception exception) {
            return false;
        }
    }
}
