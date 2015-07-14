package testcases;

import org.apache.commons.lang.RandomStringUtils;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.Login.ForgetPass;
import pages.Login.Login;
import support.SetupTest;

import java.io.IOException;
import java.util.Set;


/**
 * Created by namdo on 24/06/2015.
 */
public class TestLogin extends SetupTest{
    @DataProvider
    Object[][] getCredentials(){
        return new Object[][]{
                {"dhnam260988@gmail.com", "123456"},
                {RandomStringUtils.randomAlphabetic(5), RandomStringUtils.randomNumeric(5)},
                {"",""},
                {"dhnam260988@gmail.com",""},
                {"","123456"}
        };
    }

    @DataProvider
    Object[][] getEmails(){
        return new Object[][]{
                {"dhnam260988@gmail.com"}, {""}, {"abc"}, {"abc@gmail.com"}

        };
    }

    @DataProvider
    Object[][] getCredentialsFB(){
        return new Object[][]{
                {"dhnam260988@gmail.com", "nam2609"},
                {"testlogin", "123456"}
        };
    }

    @Test(dataProvider = "getCredentials", enabled = false)
    public void test_login(String user, String pass) throws IOException {
        System.out.println("Test login");
        HomePage homepage = new HomePage();
        Assert.assertEquals(homepage.checkHomePageLoaded(), Boolean.TRUE);
        homepage.click_loginBtn();

        Login login = new Login();
        Assert.assertEquals(login.check_loginFormOpened(), Boolean.TRUE);
        login.loginToLoginPage(user, pass);
        if(user.equals("dhnam260988@gmail.com") && pass.equals("123456"))
            login.check_loginSuccess();

        else
            login.close_popup();
    }

    @Test(dataProvider = "getEmails", enabled = false)
    public void test_forgetPass(String email){
        System.out.println("Test forgot Password page");
        HomePage homepage = new HomePage();
        Assert.assertEquals(homepage.checkHomePageLoaded(), Boolean.TRUE);
        homepage.click_loginBtn();

        Login login = new Login();
        Assert.assertEquals(login.check_loginFormOpened(), Boolean.TRUE);
        login.click_forgetPassBtn();

        ForgetPass forget = new ForgetPass();
        //Assert.assertEquals(forget.checkforgetPassPageLoaded(), Boolean.TRUE);
        forget.resendPass(email);
        if(email.equals("dhnam260988@gmail.com"))
            Assert.assertEquals(forget.check_resetBoxShown(), Boolean.TRUE);
        else if(email.equals(""))
            Assert.assertEquals(forget.check_inputEmailMessageShown(),Boolean.TRUE);
        else if(email.equals("abc"))
            Assert.assertEquals(forget.check_invalidEmailMessageShown(),Boolean.TRUE);
        else
            Assert.assertEquals(forget.check_notFoundEmailMessageShown(),Boolean.TRUE);
    }

    @Test(dataProvider = "getCredentialsFB", enabled = false)
    public void test_loginFB(String user, String pass) throws IOException {
        System.out.println("Test login with FB account");
        HomePage homepage = new HomePage();
        homepage.click_loginFBBtn();

        Login login = new Login();
        login.loginFB(user, pass);
        if(user.equals("dhnam260988@gmail.com"))
            login.check_loginSuccess();
        else
            login.check_loginFBFailed();

    }

    @Test
    public void test_loginsuccess() throws IOException {
        Reporter.log("=====ID_TC001: Verify that user can log in successfully with valid username and password=====", true);
        HomePage homepage = new HomePage();
        homepage.click_loginBtn();

        Login login = new Login();
        login.loginToLoginPage(username, pass);
        login.check_loginSuccess();

    }

    @Test
    public void test_invalidUsername_loginfailed() throws IOException {
        Reporter.log("=====ID_TC002: Verify that user can't log in successfully with invalid username", true);
        HomePage homepage = new HomePage();
        homepage.click_loginBtn();

        Login login = new Login();
        login.loginToLoginPage(username + RandomStringUtils.randomAlphabetic(5), pass );
        login.check_loginFailedWithInvalidUser();

    }

    @Test
    public void test_blankUserAndPass_loginfailed() throws IOException {
        Reporter.log("=====ID_TC003: Verify that user can't log in successfully with blank username and password", true);
        HomePage homepage = new HomePage();
        homepage.click_loginBtn();

        Login login = new Login();
        login.loginToLoginPage("","");
        login.check_loginFailedWithBlankUserAndPass();
    }
}
