package com.opensource.admin.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.IReporter;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TC001_Admin_SearchEmployee {
    public static WebDriver driver;
    @BeforeTest
    public void beforeTest(){

    }
    @Test
    public void TC001_Admin_SearchEmployee(){
        //Step1
        Reporter.log("Open Browser \"OrangeHRM\" web page");
        System.setProperty("webdriver.chrome.driver","C:/Users/jpardo/seleniumCourse/Seleniumcourse2/src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);


        //Step2
        Reporter.log("Enter Username and Password");
        driver.findElement(By.xpath("//input[@name='username']")).click();
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@name='password']")).click();
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//button[@class= 'oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']")).click();
        System.out.println("step 2 completed");

        //step3
        Reporter.log("Validate that you have logged in successfully");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class = 'oxd-layout-context']")));
        System.out.println("step 3 completed");

        //step4
        Reporter.log("Click Admin - Go to the admin page");
        driver.findElement(By.xpath("//a[contains(@href, '/web/index.php/admin/viewAdminModule')]")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[normalize-space() = 'Add']"))));
        System.out.println("step 4 completed");

        //step5 & step6
        Reporter.log("Search username in field Username, Click Search");
        driver.findElement(By.xpath("//div[@class = 'oxd-input-group oxd-input-field-bottom-space']/div/input")).sendKeys("Admin");
        driver.findElement(By.xpath("//button[normalize-space() = 'Search']")).click();
        System.out.println("step 5 & 6 completed");

        //Step 7
        Reporter.log("Verify username exist in table");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class = 'oxd-table']/div[@class = 'oxd-table-body']//div[contains(text(), 'Admin')]"))));
        String valueSearched = driver.findElement(By.xpath("//div[@class = 'oxd-table']/div[@class = 'oxd-table-body']//div[contains(text(), 'Admin')]")).getText();
        System.out.println(valueSearched);
        Assert.assertEquals(valueSearched,"Admin");
        System.out.println("step 7 completed");

    }
    @AfterTest
    public void afterTest(){
        driver.close();
    }
}
