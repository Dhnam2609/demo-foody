package pages.Login;

import objectdata.ObjectMap;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import static support.helpers.*;

/**
 * Created by namdo on 24/06/2015.
 */
public class Login {
    By username = By.id("Email");
    By password = By.id("Password");
    By rememberCheckbox = By.id("RememberMe");
    By loginBtn = By.id("signin_submit");
    By forgetPass = By.id("resend_password_link");
    By signupBtn = By.xpath("//*[@id='signin_menu']/div/div[1]/div[1]/div[2]/a[2]");
    By userImage = By.xpath("//*[@id='account']/div[1]/a/img");

    By emailFB = By.id("email");
    By passFB = By.id("pass");
    By loginFBBtn = By.id("u_0_2");
    By errorFBmessage = By.xpath("//*[@id='login_form']/div[1]");



    String missCredentialText = "Vui lòng nhập email hoặc username của bạn\n" +
            "Vui lòng nhập mật khẩu đăng nhập";

    String invalidCredentialError = "Email hoặc Mật khẩu không chính xác!";

    String blankEmailAndPassError = "Vui lòng nhập email hoặc username của bạn\n" +
            "Vui lòng nhập mật khẩu đăng nhập";

    ObjectMap objectMap = new ObjectMap();

    public Boolean check_loginFormOpened (){
        Boolean result = false;
        if(see_element(objectMap.getLocator("login.username"))
          && see_element(objectMap.getLocator("login.password"))
          && see_element(objectMap.getLocator("login.rememberCheckbox"))
          && see_element(objectMap.getLocator("login.loginBtn"))
          && see_element(objectMap.getLocator("login.forgetPass"))
          && see_element(objectMap.getLocator("login.signupBtn"))){
            result = true;
        }
        return result;
    }

    public void loginToLoginPage (String user, String pass){
        inputData(objectMap.getLocator("login.username"), user);
        inputData(objectMap.getLocator("login.password"), pass);
        if(!find_element(objectMap.getLocator("login.rememberCheckbox")).isSelected()) {
            click_element(objectMap.getLocator("login.rememberCheckbox"));
        }

        click_element(objectMap.getLocator("login.loginBtn"));
    }

    public void check_loginSuccess() throws IOException {
        tcName = "check login success";
        if(!see_element(objectMap.getLocator("login.userImage"))){
            result = "false";
            errMess = "login is failed";
        }
        check();
    }

    public void check_loginFailedWithInvalidUser() throws IOException {
        tcName = "check login failed with invalid user";

        String msgError = getAlert().getText();
        Reporter.log("Error message displayed: " + msgError, true);
        if(!msgError.contains(invalidCredentialError)){
            result = "failed";
            errMess = "TC is failed";
        }
        check();
    }

    public void check_loginFailedWithBlankUserAndPass() throws IOException {
        tcName = "check login failed with blank user and pass";
        String msgError = getAlert().getText();
        Reporter.log("Error message displayed: " + msgError, true);
        if(!msgError.contains(blankEmailAndPassError)){
           result = "failed";
            errMess = "TC is failded";
        }
        check();
    }

    public void click_forgetPassBtn (){
        click_element(objectMap.getLocator("login.forgetPass"));
    }

//    public Alert getAlert(){
//        Alert alert = driver.switchTo().alert();
//        return alert;
//    }

    public void close_popup(){
        getAlert().accept();
    }


//    public String getParentWindow(){
//        String parentWindow = driver.getWindowHandle();
//        return parentWindow;
//    }
//
//    public Set<String> getAllWindows(){
//        Set<String> allwindows = driver.getWindowHandles();
//        return allwindows;
//    }

    public void loginFB(String user, String pass){
            for (String handler : getAllWindows()) {
                driver.switchTo().window(handler);
                if (!handler.equals(getParentWindow())) {
                    inputData(objectMap.getLocator("login.emailFB"), user);
                    inputData(objectMap.getLocator("login.passFB"), pass);
                    click_element(objectMap.getLocator("login.loginFBBtn"));
                }
            }
            driver.switchTo().window(getParentWindow());

    }

//    public Boolean check_loginFBFailed(){
//        return see_element(objectMap.getLocator("login.errorFBmessage"));
//    }

    public void check_loginFBFailed(){
        tcName = "check login FB failed";
        if(!see_element(objectMap.getLocator("login.errorFBmessage"))){
            result = "failed";
            errMess = "TC is failed";
        }
        check(find_element(objectMap.getLocator("login.errorFBmessage")));
    }


//    public void check_loginFBPageLoaded (){
//        tcName = "check login FB page Loaded";
//        if(!see_element(objectMap.getLocator("login.emailFB"))
//                || see_element(objectMap.getLocator("login.passFB"))
//                || see_element(objectMap.getLocator("login.loginFBBtn"))){
//            result = "failed";
//            errMess = "TC is failed";
//
//        }
//    }


}
