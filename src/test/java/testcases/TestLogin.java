package testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.Login.ForgetPass;
import pages.Login.Login;
import support.SetupTest;


/**
 * Created by namdo on 24/06/2015.
 */
public class TestLogin extends SetupTest{
    @DataProvider
    Object[][] getCredentials(){
        return new Object[][]{
                {"dhnam260988@gmail.com", "123456"},
                {"abc@gmail.com", "123"},
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
    public void test_login(String user, String pass){
        HomePage homepage = new HomePage();
        Assert.assertEquals(homepage.checkHomePageLoaded(), Boolean.TRUE);
        homepage.click_loginBtn();

        Login login = new Login();
        Assert.assertEquals(login.check_loginFormOpened(), Boolean.TRUE);
        login.loginToLoginPage(user, pass);
        if(user.equals("dhnam260988@gmail.com") && pass.equals("123456"))
            Assert.assertEquals(login.check_loginSuccess(), Boolean.TRUE);

        else
            login.close_popup();
    }

    @Test(dataProvider = "getEmails", enabled = false)
    public void test_forgetPass(String email){
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

    @Test(dataProvider = "getCredentialsFB")
    public void test_loginFB(String user, String pass) {
        HomePage homepage = new HomePage();
        homepage.click_loginFBBtn();

        Login login = new Login();
        login.loginFB(user, pass);
        if(user.equals("dhnam260988@gmail.com"))
            Assert.assertEquals(login.check_loginSuccess(), Boolean.TRUE);
        else
            Assert.assertEquals(login.check_loginFBFailed(), Boolean.TRUE);

    }
}
