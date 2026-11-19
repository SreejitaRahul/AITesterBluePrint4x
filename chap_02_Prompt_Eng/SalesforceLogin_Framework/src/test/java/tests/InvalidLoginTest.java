package tests;

import base.BaseTest;
import config.ConfigReader;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;

public class InvalidLoginTest extends BaseTest {

    @Test
    public void verifyLoginWithInvalidUsername() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getInvalidUsername(), ConfigReader.getInvalidPassword());
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed for invalid username");
    }

    @Test
    public void verifyLoginWithInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin(ConfigReader.getValidUsername(), ConfigReader.getInvalidPassword());
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed for invalid password");
    }

    @Test
    public void verifyLoginWithBlankFields() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.doLogin("", "");
        Assert.assertTrue(loginPage.isErrorMessageDisplayed(),
                "Error message should be displayed for blank credentials");
    }
}
