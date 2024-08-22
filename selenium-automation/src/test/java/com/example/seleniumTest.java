package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

import java.nio.file.Files;

import java.time.Duration;
import io.github.bonigarcia.wdm.WebDriverManager;
import junit.framework.TestCase;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * Unit test for simple App.
 */
public class seleniumTest 
    extends TestCase
{
    private WebDriver driver;
    private WebDriverWait waitTime;
    private final String screenShotPath = "C:/Users/Vaibhav/Pictures/Screenshots/";

    private ExtentReports report;
    private ExtentSparkReporter extentReport;
    private ExtentTest test;
    private String id ;

    @BeforeMethod
    public void setUp() {
       WebDriverManager.chromedriver().setup();
       ChromeOptions options = new ChromeOptions();
       //--options.addArguments("--headless");  //--if you want to execute the test in background uncomment this line
       driver =new ChromeDriver(options);
       waitTime = new WebDriverWait(driver,Duration.ofSeconds(10));
       id = String.valueOf(System.currentTimeMillis());

       //--for a test report
       extentReport= new ExtentSparkReporter("C:/Users/Vaibhav/Downloads/Reports/reports.html"+id+"");
       extentReport.config().setDocumentTitle("Selenium report");
       extentReport.config().setReportName("TestReport");
       extentReport.config().setTheme(Theme.STANDARD);
       report=new ExtentReports();
       report.attachReporter(extentReport);
 
       
    }

    @Test
    public void buyOProduct() throws InterruptedException, IOException {
             //-- load amazone url
             driver.get("https://www.amazon.in");
             captureScreenshot("amazon_homepage"+id+".png");


             //-- searchBox for amazone
             WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
             searchBox.sendKeys("Titan Watch");
             searchBox.sendKeys(Keys.ENTER);
             captureScreenshot("amazon_search"+id+".png");
            
             //-- waiat for 10 second to load list and collect all data for first product
             waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Titan')]")));
             WebElement product = driver.findElement(By.xpath("(//div[contains(@class,'rush-component s-featured-result-item s-expand-height')])[1]"));
             String productName = product.findElement(By.cssSelector("h2 a")).getText();
             String productPrice =product.findElement(By.cssSelector(".a-price-whole")).getText();
             String productLink = product.findElement(By.cssSelector("h2 a")).getAttribute("href");
             System.out.println("Amazon Product Name: "+productName);
             System.out.println("Amazon Product Price: "+productPrice);
             System.out.println("Amazon Product Link: "+productLink);
             captureScreenshot("amazon_product"+id+".png");


             WebElement product1 = driver.findElement(By.cssSelector(".s-main-slot .s-result-item h2 a"));
             product1.click();

             //-- switch tabs when we click on product
             for(String windowHandles : driver.getWindowHandles())
             {
                driver.switchTo().window(windowHandles);

             }


             //-- find add to cart 
             WebElement addToCart = waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.id("add-to-cart-button")));
             addToCart.click();

            //  //-- move to cart 
             WebElement moveToCart = waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.id("nav-cart")));
             moveToCart.click();

            //  //-- proceed to buy
             WebElement proceedToBuy = waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.name("proceedToRetailCheckout")));
             proceedToBuy.click();
             captureScreenshot("amazon_checkout"+id+".png");

             //-- after click on buy it will redirected to login first
             waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.id("continue-announce")));
             System.out.println("Amazon Proccedd to Buy url: " +driver.getCurrentUrl());

             


             //-- Search same product in flipkart 
             driver.get("https://www.flipkart.com");
             captureScreenshot("flipkart_homepage"+id+".png");


             //use product name from amazone test case and serach in flipkart
             WebElement flipkartSearch = driver.findElement(By.name("q"));
            flipkartSearch.sendKeys(productName);
            flipkartSearch.sendKeys(Keys.ENTER);
            captureScreenshot("flipkart_search"+id+".png");

            //-- selecting the first product on flipkart
            WebElement flipkartFirsProduct = driver.findElement(By.xpath("(//a[contains(text(),'Titan Smart 3')])[1]/.."));
            String flipkartProductLink = flipkartFirsProduct.findElement(By.cssSelector("a")).getAttribute("href");
            String flipkartProductPrice = flipkartFirsProduct.findElement(By.cssSelector("a div div.Nx9bqj")).getText();
            System.out.println("Flipkart Product Price: "+flipkartProductPrice);
            System.out.println("Flipkart Product Link: "+flipkartProductLink);
            flipkartFirsProduct.click();
            captureScreenshot("flipkart_product"+id+".png");

             //-- switch tabs when we click on product on flipkart
             for(String windowHandles : driver.getWindowHandles())
             {
                driver.switchTo().window(windowHandles);

             }
            
             //-- enter pincode to check stock
             WebElement flipKartPinCode = driver.findElement(By.id("pincodeInputId"));
             flipKartPinCode.sendKeys("321602");
             driver.findElement(By.xpath("//span[contains(text(),'Check')]")).click();
             Thread.sleep(10000);
             captureScreenshot("flipkart_availability"+id+".png");



             //-- add to flipkart cart
             WebElement flipkartAddtoCart = waitTime.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[contains(@class,'NwyjNT')]")));
             flipkartAddtoCart.click();


             //--place order on flipkart
             Thread.sleep(10000);
             WebElement flipkartPlaceOrder = driver.findElement(By.xpath("//span[contains(text(),'Place Order')]"));
             flipkartPlaceOrder.click();
            captureScreenshot("flipkart_checkout"+id+".png");

             //--when checkout call happen
             waitTime.until(ExpectedConditions.urlContains("https://www.flipkart.com/checkout"));

             //--print current url 
            System.out.println("Current URL for flipkart is : "+driver.getCurrentUrl());
           
    }

    @Test
    public void comparisionForpriceTest() throws IOException{
             //--to add test in report 
             test = report.createTest("compareProduct", "compare price to buy product in flipkart and amazon site");
             driver.get("https://www.amazon.in");
             captureScreenshot("amazon_homepage1"+id+".png");


             //-- searchBox for amazone
             WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
             searchBox.sendKeys("Titan Watch");
             searchBox.sendKeys(Keys.ENTER);
             captureScreenshot("amazon_search1"+id+".png");
            
             //-- waiat for 10 second to load list and collect all data for first product
             waitTime.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Titan')]")));
             WebElement product = driver.findElement(By.xpath("(//div[contains(@class,'rush-component s-featured-result-item s-expand-height')])[1]"));
             String productName = product.findElement(By.cssSelector("h2 a")).getText();
             String productPrice =product.findElement(By.cssSelector(".a-price-whole")).getText();
             String productLink = product.findElement(By.cssSelector("h2 a")).getAttribute("href");
             System.out.println("Amazon Product Name: "+productName);
             System.out.println("Amazon Product Price: "+productPrice);
             System.out.println("Amazon Product Link: "+productLink);
             captureScreenshot("amazon_product1"+id+".png");


             //-- Search same product in flipkart 
             driver.get("https://www.flipkart.com");
             captureScreenshot("flipkart_homepage1"+id+".png");


             //use product name from amazone test case and serach in flipkart
             WebElement flipkartSearch = driver.findElement(By.name("q"));
            flipkartSearch.sendKeys(productName);
            flipkartSearch.sendKeys(Keys.ENTER);
            captureScreenshot("flipkart_search1"+id+".png");

            //-- selecting the first product on flipkart
            WebElement flipkartFirsProduct = driver.findElement(By.xpath("(//a[contains(text(),'Titan Smart 3')])[1]/.."));
            String flipkartProductLink = flipkartFirsProduct.findElement(By.cssSelector("a")).getAttribute("href");
            String flipkartProductPrice = flipkartFirsProduct.findElement(By.cssSelector("a div div.Nx9bqj")).getText();
            System.out.println("Flipkart Product Price: "+flipkartProductPrice);
            System.out.println("Flipkart Product Link: "+flipkartProductLink);
            captureScreenshot("flipkart_product1"+id+".png");

            double flipkartPrice = Double.parseDouble(flipkartProductPrice.replace("₹","").replace(",", "").trim());
            System.out.println("Current price for flipkart is : "+flipkartPrice);
            test.pass("Current price for flipkart is : "+flipkartPrice);
            double amazonPrice = Double.parseDouble(productPrice.replace(",","").trim());
            System.out.println("Current price for amazon is : "+amazonPrice);
            test.pass("Current price for amazon is : "+amazonPrice);
            

            //-- comparision of fliokart and amzon price value for both oproducts
            String result ;
            if(amazonPrice>flipkartPrice){ 
                System.out.println("Flipkart is giving better offer for this product than amazon");
                result ="Flipkart is giving better offer for this product than amazon";}

            else if(amazonPrice>flipkartPrice){  
                System.out.println("amazon is giving better offer for this product than flipkart");
                result = "amazon is giving better offer for this product than flipkart";}

            else{  
                System.out.println("Flipkart and amazon, both are giving same offer for this product");
                result ="Flipkart and amazon, both are giving same offer for this product" ;}

                test.pass(result);

    }

    //--screenshot feature
    private void captureScreenshot(String ScreenshotName) throws IOException {
        File screenShot =((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        Files.copy(screenShot.toPath(), Paths.get(screenShotPath+ScreenshotName));
    }

    @AfterMethod
    public void testReport(ITestResult result)
    {
        if(result.getStatus()==ITestResult.FAILURE)
        {
            System.out.println("Test failed: "+ result.getName());
        }
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
            System.out.println("Driver closed: ");
        }

        report.flush();
    }

}
