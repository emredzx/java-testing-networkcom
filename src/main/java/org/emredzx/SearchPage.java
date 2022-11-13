package org.emredzx;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class SearchPage extends Page{

    By buttonLocator = By.cssSelector("[class='button -secondary -sm relative']");
    ProductElement productElement;
    JavascriptExecutor js = (JavascriptExecutor) driver;

    public SearchPage(WebDriver driver) {
        super(driver);
        productElement = new ProductElement(driver);
    }

    public void scroll(){
        js.executeScript("arguments[0].scrollIntoView();",find(buttonLocator));
        delay(1000);
    }

    public ProductElement productElement(){
        return productElement;
    }

    public boolean isOnPage() {
        String url = driver.getCurrentUrl();
        return !url.equals("https://www.network.com.tr/");
    }
}
