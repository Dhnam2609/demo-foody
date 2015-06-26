package support;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

    public static Boolean compare_element(By locator, String text){
        return find_element(locator).getText().equals(text);
    }
}
