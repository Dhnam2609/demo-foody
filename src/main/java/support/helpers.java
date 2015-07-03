package support;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by namdo on 24/06/2015.
 */
public class helpers {
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
        return find_element(locator).isDisplayed();
    }

    public static void inputData(By locator, String data){
        find_element(locator).click();
        find_element(locator).sendKeys(data);
    }

    public static void click_element(By locator){
        find_element(locator).click();
    }

    public static Boolean compare_elementWithText(By locator, String text){
        return find_element(locator).getText().equals(text);
    }

    public static Boolean compare_elementWithElement(By locator1, By locator2){
        return find_element(locator1).getText().equals(find_element(locator2).getText());
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
}
