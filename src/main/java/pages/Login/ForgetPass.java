package pages.Login;

import org.openqa.selenium.By;

import static support.helpers.*;

/**
 * Created by namdo on 24/06/2015.
 */
public class ForgetPass {
    By forgetPassHeader = By.xpath("//div[6]/div/div/form/h1");
    By forgetPassGuide = By.xpath("//div[6]/div/div/form/h3");
   // By emailTxtbox = By.id("Email");

    By emailTxtbox = By.xpath("/html/body/div[6]/div/div/form/div/div[2]/input");
    By resendPassBtn = By.id("send-email");
    By errorTxt = By.xpath("//*[@class='error']/span/span");
    By notFoundEmailTxt = By.xpath("//*[@class='resetbox']/strong");
    By resetBox = By.xpath("//div[6]/div/div");

    public Boolean checkforgetPassPageLoaded (){
        Boolean result = false;
        if(see_element(forgetPassHeader)
        && see_element(forgetPassGuide)
        && see_element(emailTxtbox)
        && see_element(resendPassBtn)){
            result = true;
        }
        return result;
    }

    public void resendPass (String email){
        inputData(emailTxtbox, email);
        click_element(resendPassBtn);
    }

    public Boolean check_resetBoxShown(){
        return see_element(resetBox);
    }

    public Boolean check_inputEmailMessageShown(){
        return compare_element(errorTxt, "Nhập vào email");
    }

    public Boolean check_invalidEmailMessageShown(){
        return compare_element(errorTxt, "Email không hợp lệ");
    }

    public Boolean check_notFoundEmailMessageShown() {
        return see_element(notFoundEmailTxt);
    }
 }
