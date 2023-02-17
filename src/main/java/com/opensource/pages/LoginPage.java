package com.opensource.pages;

import com.opensource.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Base {

    public LoginPage(WebDriver driver){
        super(driver);
    }
    //Objects
    By txtUserName = By.xpath("//input[@name='username']");
    By txtPassword = By.xpath("//input[@name='password']");
    By btnLogin = By.xpath("//button[@class= 'oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']");
    By imgLoginLogo = By.xpath("//img[@alt = 'company-branding']");

    /*
     * Login method
     * @author Julian Pardo
     */
    public void LoginIntoPage (String userName, String password){
        reporter("-Login into page by inputting userName, Password and click login button-");
        type(txtUserName,userName);
        type(txtPassword,password);
        clickElement(btnLogin);
        implicityWait(1);
    }

    public void LogOutValidation(){
        reporter("-Validate user has logged out successfully-");
        waitForElementPresent(btnLogin);
        assertIsDisplayed(imgLoginLogo);
    }

}
