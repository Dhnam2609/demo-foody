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
        System.out.println("Verify user can selection the banner options on Home page");
        HomePage home = new HomePage();
        home.select_bannerOptions();
    }

    @Test ( enabled = false)
    public void test_featureCollections(){
        System.out.println("Verify the featured collections section is displayed on Home page correctly");
        HomePage homepage = new HomePage();
        Assert.assertEquals(homepage.check_featureCollectionsSection(), Boolean.TRUE);
    }

    @Test (enabled = false)
    public void test_featureCollectionsDetail() {
        System.out.println("Verify detail pages of featured collection items are displayed correctly");
        HomePage homepage = new HomePage();
        Assert.assertEquals(homepage.check_featureCollectsDetail(),Boolean.TRUE);
    }

    @Test (enabled = false)
    public void test_viewAllCollections() throws InterruptedException {
        HomePage homepage = new HomePage();
        Assert.assertEquals(homepage.check_viewAllCollections(),Boolean.TRUE);

    }

    @Test (enabled = false)
    public void test_restaurantsSection() throws InterruptedException {
        System.out.println("Verify restaurants section UI is displayed on Home page correctly");
        HomePage homepage = new HomePage();
        Assert.assertTrue(homepage.check_home_restaurantsSection());
        homepage.check_home_restaurantsItemsDetail();

    }

    @Test
    public void test_newsAndEventsTab(){
        System.out.println("Verify the News & Events tab is displayed correctly");
        HomePage homepage = new HomePage();
        Assert.assertTrue(homepage.check_restaurantsSection_newsAndEventsTab());
        Assert.assertTrue(homepage.check_newsAndEventsItemsDetail());
    }
}
