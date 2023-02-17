package com.opensource.pages;

import com.opensource.base.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

public class DashboardPage extends Base {
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    By hdrDashboard = By.xpath("//h6[text()= 'Dashboard']");

    public void userIsInPage(){
        reporter("user is into Dashboard Page");
        isDisplayed(hdrDashboard);
    }
}
