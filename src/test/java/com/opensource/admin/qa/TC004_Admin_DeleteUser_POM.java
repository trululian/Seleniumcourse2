package com.opensource.admin.qa;

import com.opensource.base.Base;
import com.opensource.base.GlobalVariables;
import com.opensource.pages.AdminPage;
import com.opensource.pages.LoginPage;
import com.opensource.pages.MainMenu;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TC004_Admin_DeleteUser_POM {

    WebDriver driver;
    Base base;
    LoginPage loginPage;
    MainMenu mainMenu;
    AdminPage adminPage;
    String loginuserName, password, userName;

    @BeforeTest
    public void beforeTest(){
        // set up driver
        base = new Base(driver);
        driver = base.chromeDriver();
        loginPage = new LoginPage(driver);
        mainMenu = new MainMenu(driver);
        adminPage = new AdminPage(driver);

        /*
        // test data
        this.loginuserName = base.getJSONvalue("LoginCredentials","username");
        this.password = base.getJSONvalue("LoginCredentials","password");
        this.userName = base.getJSONvalue("TC003_Admin_AddNewUser_POM","newUserName");
        */
        // test data Excel
        this.loginuserName = base.getCellData("TestData",1,1);
        this.password = base.getCellData("TestData",2,1);
        this.userName = base.getCellData("TestData",5,4);

    }

    @Test
    public void TC004_Admin_DeleteUser_POM(){
        base.lunchBrowser(GlobalVariables.QA_URL);
        loginPage.LoginIntoPage(loginuserName,password);
        mainMenu.userIsLoged();
        mainMenu.goToAdmin();
        adminPage.verifyUserExistInTableAfterClickSearch(userName);
        adminPage.deleteUserFromCheckBox(userName);
        adminPage.validateUserNameIsNotFound(userName);
        mainMenu.logOut();
        loginPage.LogOutValidation();
    }
    @AfterTest
    public void afterTest(){

        base.closeBrowser();
    }


}
