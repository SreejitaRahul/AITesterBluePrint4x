package tests;

import base.BaseTest;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class ValidLoginTest extends BaseTest {

    @Test
    public void verifyLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getValidUsername(), ConfigReader.getValidPassword());
        Assert.assertFalse(loginPage.isLoginPageDisplayed(),
                "User should be navigated away from the login page after successful login");
    }

    @Test
    public void verifyLoginWithRememberMe() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLoginWithRememberMe(ConfigReader.getValidUsername(), ConfigReader.getValidPassword());
        Assert.assertFalse(loginPage.isLoginPageDisplayed(),
                "User should be navigated away from the login page after successful login with remember me");
    }

    @Test
    public void verifyRememberMeCheckboxSelectable() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.clickRememberMe();
        Assert.assertTrue(loginPage.isRememberMeSelected(),
                "Remember me checkbox should be selected after click");
    }
}
