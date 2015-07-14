package support;


import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;


/**
 * Created by namdo on 24/06/2015.
 */
public class helpers {

    public static String result = "passed";

    public static String fileError, errMess, fileScrShot, tcName;

    public static WebDriver driver;

    public static void init(WebDriver webdriver){
        driver = webdriver;
    }

    public static WebElement find_element(By locator){
        return driver.findElement(locator);
    }

    public static List<WebElement> find_elements(By locator){
        return driver.findElements(locator);
    }

    public static Boolean see_element(By locator){
        Boolean re = false;
            try {
                re = driver.findElement(locator).isDisplayed();
                if(re)
                Reporter.log("element displayed: " + locator, true);
                else
                    Reporter.log("element doesn't displayed: " + locator, true);

            }catch(NoSuchElementException e){
                Reporter.log("No element exception: " + locator, true);
            }

        return re;
    }

    public static void inputData(By locator, String data){
        try{
            driver.findElement(locator).click();
            driver.findElement(locator).clear();
            Reporter.log("input data: " + data + " for element: " + locator, true);
            driver.findElement(locator).sendKeys(data);
        }catch (NoSuchElementException e){
            Reporter.log("No element exception: " + locator, true);
        }

    }

    public static void click_element(By locator){
        try {
            Reporter.log("click element: " + locator, true);
            driver.findElement(locator).click();
            Thread.sleep(1000);

        }catch (NoSuchElementException e){
            Reporter.log("No element exception: " + locator, true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String getText(By locator){
        String text = "";
        try{
            text = driver.findElement(locator).getText();
        }catch (NoSuchElementException e){
            Reporter.log("No element exception: " + locator, true);
        }
        return text;
    }

    public static Boolean compare_elementWithText(By locator, String text){
        return find_element(locator).getText().equals(text);
    }

    public static Boolean compare_elementWithElement(By locator1, By locator2){
        return find_element(locator1).getText().equals(find_element(locator2).getText());
    }

    public static String getParentWindow(){
        String parentWindow = driver.getWindowHandle();
        return parentWindow;
    }

    public static Set<String> getAllWindows(){
        Set<String> allwindows = driver.getWindowHandles();
        return allwindows;
    }

    public void switchWindow() {
        try {
            Set<String> handles = driver.getWindowHandles();
            String current = driver.getWindowHandle();
            handles.remove(current);
            String newTab = handles.iterator().next();
            driver.switchTo().window(newTab);
        } catch( Exception e ) {
            System.err.println(e.getMessage());
        }
    }

    public static Alert getAlert(){
        Alert alert = driver.switchTo().alert();
        return alert;
    }

    /**
     * Take screen shot
     *
     * @return File
     * @throws java.io.IOException
     */
    public static File takeScreenshot(String SCREENSHOT_PATH) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
//get current date time with Date()
        Date date = new Date();
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(SCREENSHOT_PATH + dateFormat.format(date).toString() + ".png"));
        } catch (IOException e) {
// TODO Auto-generated catch block
            System.err.println(e);
        }
        return scrFile;
    }


    public static void waitForAjax()  {
        System.err.println("Checking active ajax calls by calling jquery.active ...");
        try {
            if (driver instanceof JavascriptExecutor) {
                JavascriptExecutor jsDriver = (JavascriptExecutor)driver;

                for (int i = 0; i< 30; i++)
                {
//		        	if(i == Constant.TIME_WAIT -1){
//		        		System.err.println("Time out : FAILED");
//		        		Assert.assertFalse(true, "Time out");
//		        	}
                    Object numberOfAjaxConnections = jsDriver.executeScript("return jQuery.active");
                    // return should be a number
                    if (numberOfAjaxConnections instanceof Long) {
                        Long n = (Long)numberOfAjaxConnections;
                        System.err.println("Number of active jquery ajax calls: " + n);
                        if (n.longValue() == 0L)
                            break;
                    }
                    Thread.sleep(1000);
                }
            }
            else {
                System.err.println("Web driver: " + driver + " cannot execute javascript");
            }
        }
        catch (InterruptedException e) {
            System.err.println(e);
        }
    }

    static void stringToFile( String text, String fileName ) {
        try {
            File file = new File( fileName );

            // if file doesn't exists, then create it
            if ( ! file.exists( ) )
            {
                file.createNewFile( );
            }

            FileWriter fw = new FileWriter( file.getAbsoluteFile( ),true);
            BufferedWriter bw = new BufferedWriter( fw );
            bw.newLine();
            bw.write("===================="+tcName+"====================");
            bw.newLine();
            bw.write( text );
            bw.close( );
            //System.out.println("Done writing to " + fileName); //For testing
        }
        catch( IOException e )
        {
            System.out.println("Error: " + e);
            e.printStackTrace( );
        }
    }

    public static void takeScreenshotWithHighlightElement(WebElement element) {
        try {
            String oldStyle = element.getAttribute("style");

            String args = "arguments[0].setAttribute('style', arguments[1]);";
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript(args,
                    element,
                    "border: 4px solid yellow;display:block;");

            File screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);
            fileScrShot = "Report//ScreenShot//" + tcName + ".png";
            FileUtils.copyFile(screenshot, new File(fileScrShot));

            js.executeScript(args, element, oldStyle);
        }
         catch( IOException e )
            {
                System.out.println("Error: " + e);
                e.printStackTrace( );
            }
    }

    public static void check(WebElement element) {
        if (!result.contains("passed")) {
            String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            fileError = "Report//Error"+date+".txt";
            stringToFile(errMess, fileError);

            takeScreenshotWithHighlightElement(element);

        }
        //Assert.assertEquals(result, true);
        Reporter.log("=====>TC: " + result, true);
        Assert.assertEquals(result, "passed");
    }

    public static void check() throws IOException {
        if (!result.contains("passed")) {
            String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            fileError = "Report//Error"+date+".txt";
            stringToFile(errMess, fileError);

            File screenshot = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);
            fileScrShot = "Report//ScreenShot//" + tcName + ".png";
            FileUtils.copyFile(screenshot, new File(fileScrShot));
        }
        //Assert.assertEquals(result, true);
        Reporter.log("=====>TC: " + result, true);
        Assert.assertEquals(result, "passed");
    }

//    protected static void highlightElementAndTakeScreenshot(WebElement element, String priority, SeleniumInterface selenium)
//    {
//        JavascriptExecutor js = (JavascriptExecutor) selenium.getDriver();
//        String oldStyle = element.getAttribute("style");
//
//        String args = "arguments[0].setAttribute('style', arguments[1]);";
//        js.executeScript(args, element,
//                "border: 4px solid yellow;display:block;");
//
//        takeScreenshot(priority, element, selenium);
//
//        js.executeScript(args, element, oldStyle);
//    }
}
