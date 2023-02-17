package com.opensource.base;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;



public class Base {

    private WebDriver driver;
    /*

    * constructor
    * @author
    * @param
    * @return
    * @throws
     */

    public Base(WebDriver driver){

        this.driver = driver;
    }

    /*
    * Reporter Method
    * @author Julian Pardo
    * @param String - Message to be reported
     */
    public void reporter(String message){

        Reporter.log(message);
    }

    /*
     * Set WebDriver
     * @author Julian Pardo
     * @param
     */

    public WebDriver chromeDriver() {
        System.setProperty(GlobalVariables.CHROME_DRIVER_KEY, GlobalVariables.CHROME_DRIVER_LOCATION);
        driver = new ChromeDriver();
        return driver;
    }

    /*
     * Lunch Browser
     * @author Julian Pardo
     * @param
     */
    public void lunchBrowser (String url){
        reporter("Lunching URL "+url);
        driver.get(url);
        driver.manage().window().maximize();
        implicityWait(5);
    }

    /*
     * Implicity Wait
     * @author Julian Pardo
     * @param - time in seconds
     */
    public void implicityWait (int seconds){
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
            seconds *= 1000;
        }catch(TimeoutException e){
            e.printStackTrace();
        }
    }

    public void sleepWait (int seconds){
        try {
            seconds *= 1000;
            Thread.sleep(seconds);
        }catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    /*
     * Implicity Wait2
     * @author Julian Pardo
     *
     */
    public void implicityWait (){
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(GlobalVariables.GENERAL_TIME_OUT));
        }catch(TimeoutException e){
            e.printStackTrace();
        }
    }




    /*
     * Element to be present
     * @author Julian Pardo
     * @param - element
     */
    public void waitForElementPresent(By locator){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVariables.GENERAL_TIME_OUT));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }catch(TimeoutException e){
            e.printStackTrace();
        }
    }

    /*
     * Element to be present
     * @author Julian Pardo
     * @param - element
     */
    public void waitForTextPresent(By locator, String expected){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVariables.GENERAL_TIME_OUT));
            wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(locator),expected));
        }catch(TimeoutException e){
            e.printStackTrace();
        }
    }

    /*
     * Element to be present
     * @author Julian Pardo
     * @param - element
     */
    public void waitForElementclickeable(By locator){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVariables.GENERAL_TIME_OUT));
            wait.until(ExpectedConditions.elementToBeClickable(locator));
        }catch(TimeoutException e){
            e.printStackTrace();
        }
    }

    /*
     * Element to be present
     * @author Julian Pardo
     * @param - element
     */
    public void waitForElementInvisibleWithText(By locator, String text){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVariables.GENERAL_TIME_OUT));
            wait.until(ExpectedConditions.invisibilityOfElementWithText(locator,text));
        }catch(TimeoutException e){
            e.printStackTrace();
        }
    }

    /*
     * Element to be present
     * @author Julian Pardo
     * @param - element
     */
    public void waitForElementInvisibleWithText(By locator){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVariables.GENERAL_TIME_OUT));
            wait.until(ExpectedConditions.invisibilityOfElementWithText(locator,getTextFromWebElement(locator)));
        }catch(TimeoutException e){
            e.printStackTrace();
        }
    }

    /*
     * Element to be present2
     * @author Julian Pardo
     * @param - element
     */
    public void waitForElementPresent(By locator, int timeout){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }catch(TimeoutException e){
            e.printStackTrace();
        }
    }

    /*
     * Click Method
     * @author Julian Pardo
     * @param WebElement - Element to be clicked
     */
    public void clickElement (By locator){
        try{
            driver.findElement(locator).click();
        }catch(NoSuchElementException e){
            e.printStackTrace();
        }

    }

    /*
     * Type Method
     * @author Julian Pardo
     * @param WebElement - Element where the text will be inputted
     */
    public void type (By locator, String text){
        try {
            driver.findElement(locator).sendKeys(text);
        }catch(NoSuchElementException e){
            e.printStackTrace();
        }
    }

    /*
     * is Displayed method
     * @author Julian Pardo
     * @param WebElement - Element to be validated
     */
    public boolean isDisplayed (By locator){
        try {
           return driver.findElement(locator).isDisplayed();
        }catch(NoSuchElementException e){
           e.printStackTrace();
            return false;
        }
    }

    /*
     * Get text method
     * @author Julian Pardo
     * @param WebElement - Element to be validated
     */
    public String getTextFromWebElement (By locator){
        try {
            return driver.findElement(locator).getText();
        }catch(NoSuchElementException e){
            e.printStackTrace();
            return null;
        }
    }

    /*
     * Wait for element to change text
     * @author Julian Pardo
     * @param WebElement - Element to be validated
     */
    public void waitForTextChange (By locator){
        try {
            String aux = getTextFromWebElement(locator);
            String aux2 = aux;
            do{
                waitForElementInvisibleWithText(locator,aux);
                aux2+= getTextFromWebElement(locator);
                implicityWait(2);
            } while (aux.equals(aux2));

        }catch(NoSuchElementException e){
            e.printStackTrace();
        }
    }

    /*
     * Assert equals String method
     * @author Julian Pardo
     * @param String actual and expected values
     */
    public void assertEqualsString (String actual, String Expected){
        try {
            Assert.assertEquals(actual,Expected);
        }catch(AssertionError e){
            Assert.fail(e.getMessage() +" Actual value "+actual+" does not match the expected value "+ Expected);
        }
    }

    public void assertIsDisplayed(By locator){
        try {
            Assert.assertEquals(driver.findElement(locator).isDisplayed(),true);
        }catch(AssertionError e){
            Assert.fail(e.getMessage() +" element is not displayed ");
        }
    }

    /*
    *Leer tabla y retornar valor solicitado
    *
     */


    public String readTableAndReturnValue(By locator, String value){
        List<WebElement> Table = driver.findElements(locator);
        List<String>TableData = new ArrayList<String>();
        String aux;
        int aux2;
        try{
            for(int i=0;i<Table.size();i++){
                TableData.add(Table.get(i).getText());
            }
            if(TableData.contains(value)){
                aux2=TableData.indexOf(value);
                aux=TableData.get(aux2);
                System.out.println(aux);
            }else{
                aux=null;
                System.out.println(Table.get(3).getText());
            }
        } catch (NoSuchElementException e){
            e.printStackTrace();
            return null;
        }
        return aux;
    }

    /*
     * close browser
     * @author Julian Pardo
     */
    public void closeBrowser(){
        driver.close();
    }

    /*
     * Get data from JSON FILE
     * @author Julian Pardo
     */

    public String getJSONvalue(String jsonFileObj, String jsonKey){
        try{
            //JSON data
            InputStream inputStream = new FileInputStream(GlobalVariables.PATH_JSON_DATA + jsonFileObj +".json");
            JSONObject jsonObject = new JSONObject(new JSONTokener(inputStream));

            //Get Data
            String jsonValue = (String) jsonObject.getJSONObject(jsonFileObj).get(jsonKey);
            return jsonValue;
        }catch (FileNotFoundException e){
            Assert.fail("JSON File is not found");
            return null;
        }
    }

    public String getCellData(String excelName, int row, int column){
        try{
            FileInputStream excelFile = new FileInputStream(GlobalVariables.PATH_EXCEL_DATA+excelName+".xlsx");
            @SuppressWarnings("Resource")
            Workbook wb = new XSSFWorkbook(excelFile);
            Sheet sheet = wb.getSheetAt(0);
            Row rowObj = sheet.getRow(row);
            Cell cell = rowObj.getCell(column);
            String value = cell.getStringCellValue();
            return value;
        } catch (FileNotFoundException e){
            e.printStackTrace();
            return null;
        } catch (IOException e1){
            e1.printStackTrace();
            return null;
        }
    }



}
