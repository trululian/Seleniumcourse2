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

public class TC001_Admin_SearchEmployee_POM {

    WebDriver driver;
    Base base;
    LoginPage loginPage;
    MainMenu mainMenu;
    AdminPage adminPage;
    String userName, password, searchUser;

    @BeforeTest
    public void beforeTest(){
        // set up driver
        base = new Base(driver);
        driver = base.chromeDriver();
        loginPage = new LoginPage(driver);
        mainMenu = new MainMenu(driver);
        adminPage = new AdminPage(driver);

        // test data json
        /*this.userName = base.getJSONvalue("LoginCredentials","username");
        this.password = base.getJSONvalue("LoginCredentials","password");*/

        //test data excel
        this.userName = base.getCellData("TestData",1,1);
        this.password = base.getCellData("TestData",2,1);
        this.searchUser= base.getCellData("TestData",5,1);
    }

    @Test
    public void TC001_Admin_SearchEmployee_POM_script(){
        base.lunchBrowser(GlobalVariables.QA_URL);
        loginPage.LoginIntoPage(userName,password);
        mainMenu.userIsLoged();
        mainMenu.goToAdmin();
        adminPage.userIsInPage();
        adminPage.searchUser(searchUser);
        adminPage.searchValueInUserTable(searchUser);

        mainMenu.logOut();
        loginPage.LogOutValidation();
    }
    @AfterTest
    public void afterTest(){
        base.closeBrowser();
    }


}
