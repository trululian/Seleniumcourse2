package com.opensource.pages;

import com.opensource.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminPage extends Base {
    public AdminPage(WebDriver driver) {
        super(driver);
    }

    By hdrAdmin = By.xpath("//h6[text()= 'Admin']");
    By txtUserName = By.xpath("//div[@class = 'oxd-input-group oxd-input-field-bottom-space']/div/input");
    By btnAdd = By.xpath("//button[normalize-space() = 'Add']/i");
    By btnSearch = By.xpath("//button[@type = 'submit'][normalize-space() = 'Search']");
    //By tblUsername1 = By.xpath("//div[@role = 'cell']/div[text()]");
    By hdrRecordsFound = By.xpath("//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']/span");
    By hdrNotRecordsFound = By.xpath("//span[text()='No Records Found']");
    By btnAddUserSave = By.xpath("//button[@type = 'submit'][normalize-space() = 'Save']");
    By lstAddUserRole = By.xpath("//div[1][@class='oxd-grid-item oxd-grid-item--gutters']/div[@Class = 'oxd-input-group oxd-input-field-bottom-space']/div[2]//div[@class='oxd-select-text-input']");
    By txtExistingUser = By.xpath("//div[@class = 'oxd-autocomplete-text-input oxd-autocomplete-text-input--active']/input");
    By lstExistingUser = By.xpath("//div[@role = 'listbox']");
    By lstAddUserStatus = By.xpath("//div[3][@class='oxd-grid-item oxd-grid-item--gutters']/div[@Class = 'oxd-input-group oxd-input-field-bottom-space']/div[2]//div[@class='oxd-select-text-input']");
    By txtNewUserName = By.xpath("//div[4][@class='oxd-grid-item oxd-grid-item--gutters']/div[@Class = 'oxd-input-group oxd-input-field-bottom-space']//input");
    By txtNewPassword = By.xpath("//div[@class = 'oxd-grid-item oxd-grid-item--gutters user-password-cell']//input[@type='password']");
    By txtNewPasswordConfirm = By.xpath("//div[@class = 'oxd-grid-item oxd-grid-item--gutters']//input[@type='password']");
    By btnAddUserCancel = By.xpath("");
    By btnDeleteUser = By.xpath("//button[normalize-space() = 'Delete Selected']");
    By popDeleteConfirmbox = By.xpath("//div[@role='document']//P[text()='Are you Sure?']");
    By btnYesDelete = By.xpath("//button[normalize-space()='Yes, Delete']");
    By tblAdminUser = By.xpath("//div[@role='table']//div[@class='oxd-table-row oxd-table-row--with-border']//div[@role='cell']");

    //Objects
    public void userIsInPage(){
        reporter("-user is Admin / user management page-");
        waitForElementPresent(btnSearch);
        waitForElementPresent(hdrAdmin);
        waitForElementclickeable(hdrRecordsFound);
        assertIsDisplayed(hdrAdmin);
        assertIsDisplayed(btnAdd);
        assertIsDisplayed(hdrRecordsFound);
    }

    public void searchUser(String userName){
        reporter("-Search for user name "+userName+"-");
        clickElement(txtUserName);
        type(txtUserName, userName);
        waitForElementclickeable(btnSearch);
        clickElement(btnSearch);
        waitForTextPresent(hdrRecordsFound,"(1) Record Found");
    }

    public void searchValueInUserTable(String userName){
        reporter("-Validating user search = "+userName+"-");
        /*
        waitForTextChange(tblFoundRecords);
        String actual = getTextFromWebElement(tblFoundRecords);
        */
        String actual = readTableAndReturnValue(tblAdminUser,userName);
        assertEqualsString(actual,userName);
    }

    public void validateUserNameIsNotFound(String userName){
        reporter("-Validating user search = "+userName+" is not found-");
        waitForElementPresent(hdrNotRecordsFound);
        assertIsDisplayed(hdrNotRecordsFound);
    }


    public void goToAdd(){
        reporter("-user Clicks Add button-");
        waitForElementclickeable(btnAdd);
        clickElement(btnAdd);
    }
    public void userInAddScreen(){
        reporter("-user is Admin - Add New User-");
        waitForElementPresent(btnAddUserSave);
        assertIsDisplayed(btnAddUserSave);
        assertIsDisplayed(lstAddUserRole);
        assertIsDisplayed(txtNewPassword);
    }

    public void addUserSelectRole(String RoleToAdd){
        reporter("-Add new user - Selecting Role-");
        waitForElementPresent(lstAddUserRole);
        waitForElementclickeable(lstAddUserRole);
        assertIsDisplayed(lstAddUserRole);
        By aux = By.xpath("nothing");
        if(RoleToAdd.equals("ESS") || RoleToAdd.equals("Admin")) {
            waitForElementclickeable(lstAddUserRole);
            clickElement(lstAddUserRole);
            aux = By.xpath("//div[@role = 'listbox']//*[contains(text(), '" +RoleToAdd+ "')]");
            clickElement(aux);
            assertEqualsString(getTextFromWebElement(lstAddUserRole),RoleToAdd);
        } else{
            assertIsDisplayed(aux);
        }
    }

    public void addUserInputUser (String ExistingUser){
        reporter("-Add new user - Selecting The User to generate the new user-");
        type(txtExistingUser,ExistingUser);
        waitForTextPresent(lstExistingUser,ExistingUser);
        assertEqualsString(getTextFromWebElement(lstExistingUser),ExistingUser);
        clickElement(lstExistingUser);
    }

    public void addUserSelectStatus(String Status){
        reporter("-Add new user - Selecting The User Status-");
        waitForElementPresent(lstAddUserStatus);
        waitForElementclickeable(lstAddUserStatus);
        assertIsDisplayed(lstAddUserStatus);
        By aux = By.xpath("nothing");
        if(Status.equals("Enabled") || Status.equals("Disabled")) {
            waitForElementclickeable(lstAddUserRole);
            clickElement(lstAddUserStatus);
            aux = By.xpath("//div[@role = 'listbox']//*[contains(text(), '"+Status+"')]");
            clickElement(aux);
            assertEqualsString(getTextFromWebElement(lstAddUserStatus),Status);
        } else{
            assertIsDisplayed(aux);
        }
    }

    public void addUserInputUserName(String NewUserName){
        reporter("-Add new user - Input the New UserName-");
        type(txtNewUserName,NewUserName);
        System.out.println(getTextFromWebElement(txtNewUserName));
        //assertEqualsString(getTextFromWebElement(txtNewUserName),NewUserName);
    }

    public void addUserInputPasswordAndConfirm(String Password){
        reporter("-Add new user - Input the New Password and confirm it-");
        clickElement(txtNewPassword);
        type(txtNewPassword, Password);
        //assertEqualsString(getTextFromWebElement(txtNewPassword),Password);
        clickElement(txtNewPasswordConfirm);
        type(txtNewPasswordConfirm,Password);
        //assertEqualsString(getTextFromWebElement(txtNewPasswordConfirm),Password);
    }

    public void addUserSaveNewUser(){
        reporter("-Add new user - Click Save button to confirm the new user-");
        waitForElementPresent(btnAddUserSave);
        waitForElementclickeable(btnAddUserSave);
        waitForElementInvisibleWithText(btnAddUserSave,"Save");
        waitForElementclickeable(btnAddUserSave);
        clickElement(btnAddUserSave);
    }

    public void wrappedAddNewUser(String RoleToAdd, String ExistingUser, String Status, String NewUserName,String Password ){
        goToAdd();
        userInAddScreen();
        addUserSelectRole(RoleToAdd);
        addUserInputUser (ExistingUser);
        addUserSelectStatus(Status);
        addUserInputUserName(NewUserName);
        addUserInputPasswordAndConfirm(Password);
        addUserSaveNewUser();
    }

    /*
    Example wrapper method
    Verify that user exists after click search button
     */

    public void verifyUserExistInTableAfterClickSearch(String userName){
        userIsInPage();
        searchUser(userName);
        searchValueInUserTable(userName);
    }

    /*
    Delete User
     */

    public void deleteUserFromCheckBox(String userToDelete){
        String aux = tblAdminUser.toString();
        aux.trim();
        aux = aux.substring(10,105);
        aux+="//span//i";
        By aux2 = By.xpath(aux);
        waitForElementclickeable(aux2);
        clickElement(aux2);
        waitForElementPresent(btnDeleteUser);
        waitForElementclickeable(btnDeleteUser);
        clickElement(btnDeleteUser);
        waitForElementPresent(popDeleteConfirmbox);
        waitForElementPresent(btnYesDelete);
        waitForElementclickeable(btnYesDelete);
        clickElement(btnYesDelete);
        waitForElementPresent(hdrNotRecordsFound);
    }



}
