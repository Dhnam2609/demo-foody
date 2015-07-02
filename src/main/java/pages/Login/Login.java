package pages.Login;

import objectdata.ObjectMap;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import java.util.Set;
import java.util.concurrent.TimeUnit;


import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

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

    public Boolean check_loginSuccess(){
        return see_element(objectMap.getLocator("login.userImage"));
    }

    public void click_forgetPassBtn (){
        click_element(objectMap.getLocator("login.forgetPass"));
    }

    public void close_popup(){
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void loginFB(String user, String pass) {
        String parentWindow = driver.getWindowHandle();

        Set<String> allwindows = driver.getWindowHandles();
        for (String handler: allwindows){
            driver.switchTo().window(handler);
            if (!handler.equals(parentWindow)){
                inputData(objectMap.getLocator("login.emailFB"), user);
                inputData(objectMap.getLocator("login.passFB"), pass);
                click_element(objectMap.getLocator("login.loginFBBtn"));
            }
        }
        driver.switchTo().window(parentWindow);
    }

    public Boolean check_loginFBFailed(){
        return see_element(objectMap.getLocator("login.errorFBmessage"));
    }


}
