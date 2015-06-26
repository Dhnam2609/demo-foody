package pages.Login;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;

import java.util.Set;
import java.util.concurrent.TimeUnit;

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

    public Boolean check_loginFormOpened (){
        Boolean result = false;
        if(see_element(username)
          && see_element(password)
          && see_element(rememberCheckbox)
          && see_element(loginBtn)
          && see_element(forgetPass)
          && see_element(signupBtn)){
            result = true;
        }
        return result;
    }

    public void loginToLoginPage (String user, String pass){
        inputData(username, user);
        inputData(password, pass);
        if(!find_element(rememberCheckbox).isSelected()) {
            click_element(rememberCheckbox);
        }

        click_element(loginBtn);
    }

    public Boolean check_loginSuccess(){
        return see_element(userImage);
    }

    public void click_forgetPassBtn (){
        click_element(forgetPass);
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
                inputData(emailFB, user);
                inputData(passFB, pass);
                click_element(loginFBBtn);
            }
        }
        driver.switchTo().window(parentWindow);
    }

    public Boolean check_loginFBFailed(){
        return see_element(errorFBmessage);
    }


}
