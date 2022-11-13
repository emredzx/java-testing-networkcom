package org.emredzx;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LoginPage extends Page{

    By usernameLocator = By.cssSelector("[class='input js-trim']");
    By passwordLocator = By.cssSelector("[class='input']");
    By loginButtonLocator = By.xpath("//*[@id='login']/button");
    By logoLocator = By.cssSelector("[class='headerCheckout__logo']");
    List<List<String>> userdata;
    ReadData readData;
    String username;
    String password;
    Random random;

    public LoginPage(WebDriver driver) {
        super(driver);
        readData = new ReadData();
    }

    public void writeData(){
        random = new Random();
        userdata = new ArrayList<>();

        userdata = readData.getData();

        username = userdata.get(random.nextInt(userdata.size())).get(0);
        password = userdata.get(random.nextInt(userdata.size())).get(1);

        write(usernameLocator, username);
        write(passwordLocator, password);

        delay(2000);
    }

    public Boolean validateLoginButton() {
        return driver.findElement(loginButtonLocator).isDisplayed();
    }

    public void returnHomePage() {
        click(logoLocator);
        delay(2000);
    }
}
