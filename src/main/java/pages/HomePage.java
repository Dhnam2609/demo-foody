package pages;

import org.openqa.selenium.By;
import objectdata.ObjectMap;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static support.helpers.*;

/**
 * Created by namdo on 24/06/2015.
 */
public class HomePage {
    By foodyLogo = By.xpath("//*[@id='container-s']/div/div[1]/div/a/img");
    By loginBtn = By.xpath("//*[@id='login_form']/div[3]/div/a/span");
    By loginFBbtn = By.xpath("//*[@id=\"login_form\"]/div[3]/a/img");
    By bannerMenu = By.xpath("//*[@id=\"home-banner-1-2-main\"]/div[2]");
    By featureCollections_section = By.xpath("/html/body/section[1]/div/div");
    By viewAllCollectionsBtn = By.xpath("/html/body/section[1]/div/div/div/div/div[3]/a");
    By byLocation_section = By.xpath("/html/body/section[2]/div/div");

    By topReviews_section = By.xpath("/html/body/section[3]/div/div");
    By restaurants_section = By.xpath("/html/body/section[4]/div/div");
    By buffet_section = By.xpath("/html/body/section[5]/div/div");
    By coffee_section = By.xpath("/html/body/section[6]/div/div");
    By bar_section = By.xpath("/html/body/section[7]/div/div");
    By streetFood_section = By.xpath("/html/body/section[8]/div/div");
    By articles_section = By.xpath("/html/body/section[9]/div/div");

    By pageDetailTitle = By.xpath("//*[@id=\"head-wish-list\"]/div/div/div[1]/div/h1");
    ObjectMap objectMap = new ObjectMap();

    public Boolean checkHomePageLoaded (){
        Boolean result = false;
        if (see_element(objectMap.getLocator("homepage.foodyLogo"))
        && see_element(objectMap.getLocator("homepage.homeBanner"))
        && see_element(objectMap.getLocator("homepage.imageThumb"))){
            result = true;
        }
            return result;
    }

    public void click_loginBtn(){
        click_element(objectMap.getLocator("homepage.loginBtn"));
    }

    public void click_loginFBBtn(){
        click_element(objectMap.getLocator("homepage.loginFBBtn"));
    }

    public void select_bannerOptions() throws InterruptedException {
        WebElement we = find_element(bannerMenu);
        List<WebElement> list = we.findElements(By.tagName("li"));
        System.out.println("List size:" + list.size());
        for(int i=0; i<list.size();i++) {
            System.out.println(list.get(i));
            list.get(i).click();
            Thread.sleep(2000);
        }
    }

    public Boolean check_featureCollectionsSection(){
        Boolean result = false;
        WebElement we = find_element(featureCollections_section);
        List<WebElement> listwe = we.findElements(By.cssSelector(".top-featured-home-wishlist-item-new"));
        if(listwe.size() == 16 && see_element(viewAllCollectionsBtn)){
            result = true;
        }
        return  result;
    }

    public Boolean check_featureCollectsDetail() throws InterruptedException {
        Boolean result = true;
        WebElement we = find_element(featureCollections_section);
        List<WebElement> listwe = we.findElements(By.cssSelector(".top-featured-home-wishlist-item-new"));
        //System.out.print("list size" + listwe.size());
        for (int i = 1; i <= listwe.size(); i++) {
            WebElement el = find_element(By.xpath("//*[@id='top-featured-home-wishlist-container']/a[" + i + "]"));
            el.click();
            String collectionTitle = el.getText();

            //e.click();
            //System.out.println(collectionTitle);
            System.out.println(find_element(pageDetailTitle).getText());
            if (collectionTitle.equals(find_element(pageDetailTitle).getText())) {
                result = true;
                driver.navigate().back();
                //WebDriverWait wait = new WebDriverWait(driver, 10);
                //wait.until(ExpectedConditions.visibilityOfAllElements(listwe));

            } else
                break;


        }
        return result;
    }

    public void waitForAjax()  {
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
