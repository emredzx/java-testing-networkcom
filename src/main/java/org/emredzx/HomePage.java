package org.emredzx;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class HomePage extends Page{

    By boxLocator = By.id("search");
    By cartLocator = By.cssSelector("[class='header__icon -shoppingBag']");
    By dropCartLocator = By.cssSelector("[class='header__basketProductRemove']");
    By dropButtonLocator = By.cssSelector("[class='btn -black o-removeCartModal__button']");
    By emptyTestLocator = By.cssSelector("[class='header__emptyBasketText']");
    Actions actions;

    public HomePage(WebDriver driver) {
        super(driver);
        actions = new Actions(driver);
    }

    public void search(String text) {
        write(boxLocator, text);
        enter(boxLocator);
    }

    public boolean isOnPage() {
        String url = driver.getCurrentUrl();
        return url.equals("https://www.network.com.tr/");

    }

    public void showCart(){
        click(cartLocator);
        delay(2000);
    }

    public Boolean validateDropCart(){
        actions.moveToElement(driver.findElement(dropCartLocator)).click().perform();
        delay(1000);
        actions.moveToElement(driver.findElement(dropButtonLocator)).click().perform();
        delay(1000);

        showCart();

        return driver.findElement(emptyTestLocator).isDisplayed();
    }
}
