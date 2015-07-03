package testcases;

import com.beust.jcommander.Parameter;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.Search;
import support.SetupTest;

/**
 * Created by namdo on 25/06/2015.
 */
public class TestSearchBar extends SetupTest{
    @Test
    public void test_searchBarUI(){
        System.out.println("Verify search bar is displayed correctly");
        HomePage homepage = new HomePage();
        Assert.assertEquals(homepage.checkHomePageLoaded(), Boolean.TRUE);

        Search search = new Search();
        Assert.assertEquals(search.check_searchBarAvailable(), Boolean.TRUE);
        search.click_searchBar();
        Assert.assertEquals(search.check_suggestedSearchShown(), Boolean.TRUE);
    }

    @Test
    @Parameters({"keyword"})
    public void test_searchKeyword(String keyword){
        System.out.println("Verify user can search a keyword successfully");
        HomePage homepage = new HomePage();
        Assert.assertEquals(homepage.checkHomePageLoaded(), Boolean.TRUE);

        Search search = new Search();
        Assert.assertEquals(search.check_searchBarAvailable(), Boolean.TRUE);
        search.search_keyword(keyword);
        Assert.assertEquals(search.check_searchResultPage(keyword), Boolean.TRUE);
    }

    @Test (dependsOnMethods = {"test_searchKeyword"})
    @Parameters({"keyword"})

    public void test_sortTypeSelectBox(String keyword){
        System.out.println("Verify user can select items in sort type select-box");
        Search search = new Search();
        search.search_keyword(keyword);
        search.select_sortTypeItems();
    }

    @Test (dependsOnMethods = {"test_searchKeyword"})
    @Parameters({"keyword"})
    public void test_searchDetailPage(String keyword){
        System.out.println("Verify detail page of a search result is displayed correctly");
        Search search = new Search();
        search.search_keyword(keyword);
        search.click_searchedResult();
        Assert.assertEquals(search.check_searchDetailLoaded(), Boolean.TRUE);
    }

}
