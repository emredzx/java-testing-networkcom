package org.emredzx;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class ProductElement extends Page{

    By allProductLocator = By.cssSelector("[class='products__item col-6 col-md-4']");
    By discountLocator1 = By.cssSelector("[class='product__discountPercent']");
    By discountLocator2 = By.cssSelector("[class='product__discountPercent -dualSmall -end']");
    By productLocator = By.cssSelector("[class='product__imageList']");
    By sizeLocator = By.cssSelector("[class='radio-box__label']");
    By checkoutLocator = By.cssSelector("[class='button -primary header__basket--checkout header__basketModal -checkout']");
    By priceLocator = By.cssSelector("[class='product__price -actual']");
    By chartPriceLocator = By.cssSelector("[class='cartItem__price -sale']");
    By chartSizeLocator = By.cssSelector("[class='cartItem__attrValue']");
    By chartOldPriceLocator = By.cssSelector("[class='cartItem__price -old -labelPrice']");
    By continueBLocator = By.cssSelector("[class='continueButton n-button large block text-center -primary']");
    List<WebElement> allProducts;
    WebElement checkOut;
    Actions actions;
    String chosenPriceBefore;
    String chosenPriceAfter;
    String chosenSizeBefore;
    String chosenSizeAfter;
    String chosenPriceOld;

    public ProductElement(WebDriver driver) {
        super(driver);
        actions = new Actions(driver);
    }

    public void choose() {
        allProducts = findAll(allProductLocator);
        for (WebElement element : allProducts) {
            if (!element.findElements(discountLocator1).isEmpty() || !element.findElements(discountLocator2).isEmpty()) {

                actions.moveToElement(element.findElement(productLocator)).build().perform();

                chosenPriceBefore = element.findElement(priceLocator).getText();

                actions.moveToElement(element.findElement(sizeLocator)).click().perform();

                chosenSizeBefore = element.findElement(sizeLocator).getText();
                delay(1000);

                checkOut = driver.findElement(checkoutLocator);
                actions.moveToElement(checkOut).click().perform();

                delay(2000);
                break;
            }
        }
    }

    public Boolean validateChart() {
        chosenPriceAfter = driver.findElement(chartPriceLocator).getText();
        chosenSizeAfter = driver.findElement(chartSizeLocator).getText();
        chosenPriceOld = driver.findElement(chartOldPriceLocator).getText();
        return chosenPriceBefore.equals(chosenPriceAfter) && chosenSizeBefore.equals(chosenSizeAfter);
    }

    public Boolean validatePrice() {
        chosenPriceAfter = chosenPriceAfter.replaceAll("[^0-9]", "").trim();
        chosenPriceOld = chosenPriceOld.replaceAll("[^0-9]", "").trim();

        return Integer.parseInt(chosenPriceOld) > Integer.parseInt(chosenPriceAfter);
    }

    public void continueCart(){
        click(continueBLocator);
        delay(2000);
    }
}
