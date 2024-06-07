package com.opencart.pack;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchTouchPage extends BasePage{
    //elements
    @FindBy(css = "#search > input")
    private WebElement searchInputField;

    @FindBy(xpath = "//*[@id=\"search\"]/button")
    private WebElement searchButton;

    @FindBy(css = ".product-layout .product-thumb")
    private List<WebElement> productList;

    @FindBy(css = "div#product-list > div:nth-of-type(1) form[method='post']  button[title='Add to Cart']")
    private WebElement htcTouchAddToCart;

    @FindBy(css = "#product-list > div:nth-child(2) > div > div.content > form > div > button:nth-child(1)")
    private WebElement iPodTouchAddToCart;

    @FindBy(css = "#header-cart")
    private WebElement shoppingCartButton;

    @FindBy(css = "#header-cart > div > ul > li > div > p > a:nth-child(1)")
    private WebElement viewCartLink;

    @FindBy(css = "#alert")
    private WebElement successPopUpAlert;

    //methods
    public SearchTouchPage(WebDriver driver) {
        super(driver);
    }

    public void inputSearchQueryIntoSearchInputField(){
        searchInputField.clear();
        searchInputField.sendKeys("Touch");
    }
    public void clickSearchButton(){
        searchButton.click();
    }

    //assurance that the correct products are being displayed with search query 'Touch'
    public boolean areAllProductsContainingWord(String word) {
        boolean allProductsContainWord = true;
        for (WebElement product : productList) {
            String productName = product.findElement(By.tagName("h4")).getText();
            if (!productName.contains(word)) {
                allProductsContainWord = false;
                break;
            }
        }
        return allProductsContainWord;
    }

    public void addHTCTouchToShoppingCart(){
        htcTouchAddToCart.click();
    }

    public void addiPodTouchToShoppingCart(){
        iPodTouchAddToCart.click();
    }

    public void clickShoppingCartButton(){

        // Find the shopping cart button
        WebElement shoppingCartButton = driver.findElement(By.id("header-cart"));
        //wait for the annoying alert to disappear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".alert.alert-success.alert-dismissible")));
        //js script for scrolling shopping cart button into view
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", shoppingCartButton);

        //wait for shopping cart element to be clickable
        WebDriverWait pause = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement clickableCartButton = pause.until(ExpectedConditions.elementToBeClickable(shoppingCartButton));

        shoppingCartButton.click();



    }
    public void clickOnCheckoutLink(){
        viewCartLink.click();
    }
}
