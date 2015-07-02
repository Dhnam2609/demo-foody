package testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.Search;
import support.SetupTest;

/**
 * Created by namdo on 30/06/2015.
 */
public class Home extends SetupTest {
    @Test (enabled = false)
    public void test() throws InterruptedException {
        HomePage home = new HomePage();
        home.select_bannerOptions();
    }

    @Test( enabled = false)
    public void test_featureCollections(){
        HomePage homepage = new HomePage();
        Assert.assertEquals(homepage.check_featureCollectionsSection(), Boolean.TRUE);
    }

    @Test
    public void test_featureCollectionsDetail() throws InterruptedException {
        HomePage homepage = new HomePage();
        //Assert.assertEquals(homepage.check_featureCollectsDetail(),Boolean.TRUE);
        homepage.check_featureCollectsDetail();
    }

//    @Test
//    public void test1(){
//        Search search = new Search();
//        search.click_searchBar();
//        search.search_keyword("sushi");
//        search.check_searchedResult();
//    }
}
