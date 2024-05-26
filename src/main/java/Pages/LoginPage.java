package Pages;

import Base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BaseClass {
    WebDriver driver;
    final private By usernameField = By.cssSelector("[data-test='username']");
    final private By passwordField = By.id("password");
    final private By loginBtn = By.id("login-button");
    final private By loginErrorMessage = By.className("error-message-container");
    //final private By unSuccessfulLoginMessage = By.className("error-message-container");
    final private By errorMessage = By.cssSelector("[data-test='error']");
    final private By productsLocator = By.cssSelector("[data-test='title']");



    public LoginPage(WebDriver driver){
        super(driver);
        this.driver=driver;
    }
    public void enterUsername(String username){
        sendKeys(usernameField,username);
    }
    public void enterPassword(String password){
        sendKeys(passwordField,password);
    }
    public void login(){
        clickOn(loginBtn);
    }
    public Boolean isLoggedInSuccessfully(){
        return isDisplayed(productsLocator);
    }
    public Boolean isLoginErrorMSGDisplayed(){
        return isDisplayed(loginErrorMessage);
    }

    /*public Boolean myAccountAppear(){
        return waitToPresent(myAccount).isDisplayed();
    }*/
    public Boolean isLoginBtnDisplayed(){
        return isDisplayed(loginBtn);
    }
    /*public Boolean unSuccessfulLogin(){
        //return waitToPresent(unSuccessfulLoginMessage).isDisplayed();
        return isDisplayed(unSuccessfulLoginMessage);
    }*/

}


