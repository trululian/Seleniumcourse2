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

public class TC003_Admin_AddNewUser_POM {

    WebDriver driver;
    Base base;
    LoginPage loginPage;
    MainMenu mainMenu;
    AdminPage adminPage;
    String loginuserName, password, newUserName, existingUser, userRole, userStatus, newPassword;

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
        this.newUserName = base.getJSONvalue("TC003_Admin_AddNewUser_POM","newUserName");
        this.newPassword = base.getJSONvalue("TC003_Admin_AddNewUser_POM","newPassword");
        this.existingUser = base.getJSONvalue("TC003_Admin_AddNewUser_POM","existingUser");
        this.userRole = base.getJSONvalue("TC003_Admin_AddNewUser_POM","userRole");
        this.userStatus=base.getJSONvalue("TC003_Admin_AddNewUser_POM","userStatus");
        */
        // test data Excel
        this.loginuserName = base.getCellData("TestData",1,1);
        this.password = base.getCellData("TestData",2,1);
        this.newUserName = base.getCellData("TestData",11,1);
        this.newPassword = base.getCellData("TestData",12,1);
        this.existingUser = base.getCellData("TestData",13,1);
        this.userRole = base.getCellData("TestData",14,1);
        this.userStatus= base.getCellData("TestData",15,1);
    }

    @Test
    public void TC003_Admin_AddNewUser_POM(){
        base.lunchBrowser(GlobalVariables.QA_URL);
        loginPage.LoginIntoPage(loginuserName,password);
        mainMenu.userIsLoged();
        mainMenu.goToAdmin();
        adminPage.userIsInPage();
        adminPage.wrappedAddNewUser(userRole, existingUser, userStatus, newUserName,newPassword);
        adminPage.userIsInPage();
        adminPage.searchUser(newUserName);
        adminPage.searchValueInUserTable(newUserName);
        mainMenu.logOut();
        loginPage.LogOutValidation();
    }
    @AfterTest
    public void afterTest(){

        base.closeBrowser();
    }


}
