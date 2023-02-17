package com.google;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
public class firtsTest {

    public static WebDriver driver;
    @BeforeTest
    public void beforeTest(){

    }
    @Test
    public void firts(){
        System.setProperty("webdriver.chrome.driver","C:/Users/jpardo/seleniumCourse/Seleniumcourse2/src/test/resources/drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.google.com");
        driver.manage().window().maximize();
    }
    @AfterTest
    public void afterTest(){
        driver.close();
    }

}
