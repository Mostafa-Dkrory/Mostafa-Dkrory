package TestModules.Login;

import Pages.LoginPage;
import TestModules.Base.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginModuleTest extends BaseTest {

    @Test()
    void LoginWithValidCredentials(){
        LoginPage loginPage = new LoginPage(super.driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("secret_sauce");
        loginPage.login();
        assertTrue(loginPage.isLoggedInSuccessfully(),"Failed to Login WithValidCredentials");
    }

    @Test()
    void LoginWithValidUsernameAndInValidPassword(){
        LoginPage loginPage = new LoginPage(super.driver);
        loginPage.enterUsername("standard_user");
        loginPage.enterPassword("InvalidPassword");
        loginPage.login();
        assertTrue(loginPage.isLoginErrorMSGDisplayed(),"Logged in with Valid Username And InValid Password");

    }

    @Test()
    void LoginWithInValidUsernameAndValidPassword(){
        LoginPage loginPage = new LoginPage(super.driver);
        loginPage.enterUsername("username");
        loginPage.enterPassword("secret_sauce");
        loginPage.login();
        assertTrue(loginPage.isLoginErrorMSGDisplayed(),"Logged in With InValid Username And Valid Password");

    }
    @Test()
    void LoginWithEmptyUsername(){
        LoginPage loginPage = new LoginPage(super.driver);
        loginPage.enterPassword("password");
        loginPage.login();
        assertTrue(loginPage.isLoginErrorMSGDisplayed(),"Username error msg is not displayed as excepted");
    }
    @Test()
    void LoginWithEmptyPassword(){
        LoginPage loginPage = new LoginPage(super.driver);
        loginPage.enterUsername("username");
        loginPage.login();
        assertTrue(loginPage.isLoginErrorMSGDisplayed(),"Password error msg is not displayed as excepted");
    }

    @Test()
    void LoginWithEmptyUsernameAndEmptyPassword(){
        LoginPage loginPage = new LoginPage(super.driver);
        loginPage.enterUsername("username");
        loginPage.login();
        assertTrue(loginPage.isLoginErrorMSGDisplayed(),"Login error msg is not displayed as excepted");
    }

/*    @DataProvider
    public Object[] Accepted_Usernames() {
        Object[] tag = new Object[15];
        tag[0] = "standard_user";
        tag[1] = "locked_out_user";
        tag[2] = "problem_user";
        tag[3] = "performance_glitch_user";
        tag[4] = "error_user";
        tag[5] = "visual_user";
        return tag;    }*/


}
