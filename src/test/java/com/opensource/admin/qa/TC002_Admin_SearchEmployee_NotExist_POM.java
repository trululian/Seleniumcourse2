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

import java.io.FileNotFoundException;

public class TC002_Admin_SearchEmployee_NotExist_POM {

    WebDriver driver;
    Base base;
    LoginPage loginPage;
    MainMenu mainMenu;
    AdminPage adminPage;
    String userName, password, userName2;

    @BeforeTest
    public void beforeTest(){
        // set up driver
        base = new Base(driver);
        driver = base.chromeDriver();
        loginPage = new LoginPage(driver);
        mainMenu = new MainMenu(driver);
        adminPage = new AdminPage(driver);


        /*
        // test data JSON
        this.userName = base.getJSONvalue("LoginCredentials","username");
        this.password = base.getJSONvalue("LoginCredentials","password");
        this.userName2 = base.getJSONvalue("TC002_Admin_SearchEmployee_NotExist_POM","searchuser");
         */
        //test data excel
        this.userName = base.getCellData("TestData",1,1);
        this.password = base.getCellData("TestData",2,1);
        this.userName2 = base.getCellData("TestData",8,1);
    }

    @Test
    public void TC002_Admin_SearchEmployee_NotExist_POM_script(){
        base.lunchBrowser(GlobalVariables.QA_URL);
        loginPage.LoginIntoPage(userName,password);
        mainMenu.userIsLoged();
        mainMenu.goToAdmin();
        adminPage.userIsInPage();
        adminPage.searchUser(userName2);
        adminPage.validateUserNameIsNotFound(userName2);
        mainMenu.logOut();
        loginPage.LogOutValidation();
    }
    @AfterTest
    public void afterTest(){

        base.closeBrowser();
    }


}
