package objectdata;

import org.openqa.selenium.By;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by namdo on 29/06/2015.
 */
public class ObjectMap {
    Properties prop;

    public ObjectMap(){
        prop = new Properties();

        try{
            FileInputStream fis = new FileInputStream("src/main/java/objectdata/objectmap.properties");
            prop.load(fis);
            fis.close();

        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public By getLocator(String strElement){
        // retrieve the specified object from object list
        String locator = prop.getProperty(strElement);
        // extract the locator type and value of the object
        String locatorType = locator.split(":")[0];
        String locatorValue = locator.split(":")[1];
        // for testing and debugging purposes
        System.out.println("Retrieving object of type '" + locatorType + "' and value '" + locatorValue + "' from the object map" );

        // return a instance of By class base on the type of locator
        // this By can be used by the browser object in the actual test
        if(locatorType.toLowerCase().equals("id"))
            return By.id(locatorValue);
        else if(locatorType.toLowerCase().equals("name"))
            return By.name(locatorValue);
        else if(locatorType.toLowerCase().equals("classname") || locatorType.toLowerCase().equals("class"))
            return By.className(locatorValue);
        else if(locatorType.toLowerCase().equals("tagname") || locatorType.toLowerCase().equals("tag"))
            return By.tagName(locatorValue);
        else if(locatorType.toLowerCase().equals("linktext") || locatorType.toLowerCase().equals("link"))
            return By.linkText(locatorValue);
        else if(locatorType.toLowerCase().equals("partiallinktext"))
            return By.partialLinkText(locatorValue);
        else if(locatorType.toLowerCase().equals("cssselector") || locatorType.toLowerCase().equals("css"))
            return By.cssSelector(locatorValue);
        else
            return By.xpath(locatorValue);
    }
}
