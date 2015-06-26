package pages;

import org.openqa.selenium.By;

import static support.helpers.click_element;
import static support.helpers.find_element;
import static support.helpers.see_element;

/**
 * Created by namdo on 24/06/2015.
 */
public class HomePage {
    By foodyLogo = By.xpath("//*[@id='container-s']/div/div[1]/div/a/img");
    By loginBtn = By.xpath("//*[@id='login_form']/div[3]/div/a/span");
    By loginFBbtn = By.xpath("//*[@id=\"login_form\"]/div[3]/a/img");

    public Boolean checkHomePageLoaded (){
        return see_element(foodyLogo);
    }

    public void click_loginBtn(){
        click_element(loginBtn);
    }

    public void click_loginFBBtn(){
        click_element(loginFBbtn);
    }


}
