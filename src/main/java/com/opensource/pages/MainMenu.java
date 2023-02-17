package com.opensource.pages;

import com.opensource.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainMenu extends Base {
    public MainMenu(WebDriver driver) {
        super(driver);
    }

    //Objects
    By imgClientLogo = By.xpath("//img[@alt = 'client brand logo']");
    By lnkAdmin = By.xpath("//span[text()='Admin']");
    By lnkPIM = By.xpath("//span[text()='PIM']");
    By ulMenu = By.xpath("//aside[@class = 'oxd-sidepanel']");
    By ulUser = By.xpath("//li[@class = 'oxd-userdropdown']");
    By lnkLogOut = By.xpath("//a[text()='Logout']");

    public void userIsLoged(){
        reporter("-user is successfully logged into the page-");
        waitForElementPresent(ulMenu);
        isDisplayed(imgClientLogo);
        isDisplayed(ulMenu);
    }

    public void goToAdmin(){
        reporter("-user selects admin page-");
        waitForElementPresent(lnkAdmin);
        waitForElementclickeable(lnkAdmin);
        clickElement(lnkAdmin);
    }

    public void logOut(){
        reporter("-logging out app-");
        clickElement(ulUser);
        waitForElementPresent(lnkLogOut);
        clickElement(lnkLogOut);
        implicityWait(2);
    }


}
