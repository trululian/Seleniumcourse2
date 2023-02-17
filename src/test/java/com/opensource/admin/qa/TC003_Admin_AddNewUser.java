package com.opensource.admin.qa;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class TC003_Admin_AddNewUser {
    public static WebDriver driver;
    @BeforeTest
    public void beforeTest(){
    }

    @Test
    public void TC003_Admin_AddNewUser() throws InterruptedException {
        //Step1
        Reporter.log("Open Browser \"OrangeHRM\" web page");
        System.setProperty("webdriver.chrome.driver","C:/Users/jpardo/seleniumCourse/Seleniumcourse2/src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        Actions actions = new Actions(driver);
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
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class='orangehrm-container']"))));
        System.out.println("step 4 completed");

        //step5
        Reporter.log("Click add button");
        driver.findElement(By.xpath("//button[normalize-space() = 'Add']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class = 'orangehrm-card-container']"))));
        System.out.println("step 5 completed");

        //Step 6,7 & 8
        Reporter.log("Selecting Role");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class = 'oxd-select-text oxd-select-text--active']/div[text() = '-- Select --']"))));
        driver.findElement(By.xpath("//div[@class = 'oxd-select-text oxd-select-text--active']/div[text() = '-- Select --']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@role = 'listbox']"))));
        //String Values = driver.findElement(By.xpath("//div[@role = 'listbox']")).getText();
        //System.out.println(Values);
        //System.out.println( driver.findElement(By.xpath("//div[@role = 'listbox']//*[contains(text(), 'ESS')]")).getText());
        driver.findElement(By.xpath("//div[@role = 'listbox']//*[contains(text(), 'ESS')]")).click();

        Reporter.log("Enter valid Employee Name");
        String admonUserName = driver.findElement(By.xpath("//p[@class='oxd-userdropdown-name']")).getText();
        driver.findElement(By.xpath("//div[@class = 'oxd-autocomplete-text-input oxd-autocomplete-text-input--active']/input")).sendKeys(admonUserName);
        wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.xpath("//div[@role = 'listbox']")),admonUserName));
        System.out.println(driver.findElement(By.xpath("//div[@role = 'listbox']")).getText());
        driver.findElement(By.xpath("//div[@role = 'listbox']")).click();

        Reporter.log("Enter status");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class = 'oxd-grid-2 orangehrm-full-width-grid']/child::div[3]/div/div[2]//div[@class = 'oxd-select-text-input']"))));
        driver.findElement(By.xpath("//div[@class = 'oxd-grid-2 orangehrm-full-width-grid']/child::div[3]/div/div[2]//div[@class = 'oxd-select-text-input']")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@role = 'listbox']"))));
        driver.findElement(By.xpath("//div[@role = 'listbox']//*[contains(text(), 'Enabled')]")).click();

        Reporter.log("Enter valid username");
        driver.findElement(By.xpath("//div[@class = 'oxd-grid-2 orangehrm-full-width-grid']/child::div[4]/div/div[2]//input[@class='oxd-input oxd-input--active']")).sendKeys("PC.testUser");


        Reporter.log("Enter new password and confirm");
        WebElement Pass = driver.findElement(By.xpath("//div[@class = 'oxd-grid-item oxd-grid-item--gutters user-password-cell']//input[@type='password']"));
        Pass.sendKeys("Test1234*");
        WebElement confirmPass = driver.findElement(By.xpath("//div[@class = 'oxd-grid-item oxd-grid-item--gutters']//input[@type='password']"));
        confirmPass.sendKeys("Test1234*");
        actions.sendKeys(confirmPass,Keys.TAB);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(5000);
        System.out.println("step 6, 7, 8 completed");

        //Step 9
        Reporter.log("Click Save button");
        //WebElement SaveBtn = driver.findElement(By.xpath("//div[@class='oxd-form-actions']/button[2]"));
        WebElement SaveBtn = driver.findElement(By.cssSelector("button[type='submit']"));
        WebElement aux = driver.findElement(By.xpath("//p[text()=' * Required']"));
        wait.until(ExpectedConditions.visibilityOf(SaveBtn));
        wait.until(ExpectedConditions.elementToBeClickable(SaveBtn));
        System.out.println((SaveBtn).getText());
        SaveBtn.click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class = 'oxd-table-filter']"))));
        System.out.println("step 9 completed");

        //Step 10
        Reporter.log("Search username in field Username, Click Search");
        driver.findElement(By.xpath("//div[@class = 'oxd-input-group oxd-input-field-bottom-space']/div/input")).sendKeys("PC.testUser");
        driver.findElement(By.xpath("//button[normalize-space() = 'Search']")).click();
        System.out.println("step 10 completed");

        //Step 11
        Reporter.log("Verify username exist in table");
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//div[@class = 'oxd-table']/div[@class = 'oxd-table-body']//div[contains(text(), 'PC.testUser')]"))));
        String valueSearched = driver.findElement(By.xpath("//div[@class = 'oxd-table']/div[@class = 'oxd-table-body']//div[contains(text(), 'PC.testUser')]")).getText();
        System.out.println(valueSearched);
        Assert.assertEquals(valueSearched,"PC.testUser");
        System.out.println("step 11 completed");

    }

    @AfterTest
    public void afterTest(){
        driver.close();
    }

}
