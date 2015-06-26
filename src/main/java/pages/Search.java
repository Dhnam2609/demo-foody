package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static support.helpers.*;

/**
 * Created by namdo on 25/06/2015.
 */
public class Search {
    By searchBar = By.id("pkeywords");
    By searchBtn = By.xpath("//*[@id='searchFormTop']/div/a/span");
    By searchMenuBtn = By.xpath("//*[@id='container-s']/div/div[2]/div[1]/div/ul/li[1]/a/span");
    By suggestSearchTitle = By.xpath("//*[@id='searchFormTop']/div/div/div");
    By fivestarLink = By.xpath("//*[@id=\"searchFormTop\"]/div/div/ul/li[1]/a");
    By pokiLink = By.xpath("//*[@id=\"searchFormTop\"]/div/div/ul/li[2]/a");
    By galleryLink = By.xpath("//*[@id=\"searchFormTop\"]/div/div/ul/li[3]/a");
    By topKeywordLink = By.xpath("//*[@id=\"searchFormTop\"]/div/div/ul/li[4]/a");
    By ecardLink = By.xpath("//*[@id=\"searchFormTop\"]/div/div/ul/li[5]/a");
    By promoteLink = By.xpath("//*[@id=\"searchFormTop\"]/div/div/ul/li[6]/a");
    By reserveLink = By.xpath("//*[@id=\"searchFormTop\"]/div/div/ul/li[7]/a");

    By numberOfResut = By.xpath("//*[@id='directorypage']/div/div/div/div/div[2]/div[2]/div/div[1]/div/div/span");
    By directorySearchResultTab = By.xpath("//*[@id=\"directorypage\"]/div/div/div/div/div[2]/div[1]");
    By sortTypeSelectBox = By.xpath("//*[@id=\"directorypage\"]/div/div/div/div/div[2]/div[2]/div/div[2]/select");
    By listViewBtn = By.xpath("//*[@id=\"directorypage\"]/div/div/div/div/div[2]/div[2]/div/div[4]/a[1]");
    By gridViewBtn = By.xpath("//*[@id='directorypage']/div/div/div/div/div[2]/div[2]/div/div[4]/a[2]");
    By mapViewBtn = By.xpath("//*[@id=\"directorypage\"]/div/div/div/div/div[2]/div[2]/div/div[4]/a[3]");
    By resultList = By.xpath("//*[@id=\"directorypage\"]/div/div/div/div/div[2]/div[4]");

    Boolean result;

    public Boolean check_searchBarAvailable(){
        result = false;
        if(see_element(searchBar) && see_element(searchBtn) && see_element(searchMenuBtn)){
            result = true;
        }
        return result;
    }
    public void click_searchBar(){
        click_element(searchBar);
    }

    public Boolean check_suggestedSearchShown(){
        result = false;
        if(see_element(suggestSearchTitle)
            && see_element(fivestarLink)
            && see_element(pokiLink)
            && see_element(galleryLink)
            && see_element(topKeywordLink)
            && see_element(ecardLink)
            && see_element(promoteLink)
            && see_element(reserveLink)){
            result = true;
        }
        return result;
    }

    public void search_keyword(String keyword){
        inputData(searchBar, keyword);
        click_element(searchBtn);
    }

    public Boolean check_searchResultPage(String keyword){
        result = false;
        if(see_element(directorySearchResultTab)
                && see_element(numberOfResut)
                && see_element(sortTypeSelectBox)
                && see_element(listViewBtn)
                && see_element(gridViewBtn)
                && see_element(mapViewBtn)
                && see_element(resultList)){
            result = true;
        }
        return result;
    }

    public void select_sortTypeItems(){
        Select select = new Select(find_element(sortTypeSelectBox));
        List<WebElement> listItem = select.getOptions();
        for (WebElement we: listItem)
        {
            we.click();
        }

     }


}
