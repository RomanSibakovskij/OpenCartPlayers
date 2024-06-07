package com.opencart.pack;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MP3Page extends BasePage{
    //elements
    @FindBy(xpath = "//*[@id=\"button-list\"]")
    private WebElement selectItemsAsListButton;

    @FindBy(xpath = "//*[@id=\"product-list\"]/div[1]/div/div[1]/a")
    private WebElement iPodClassicSelectorLink;

    @FindBy(xpath ="//*[@id=\"product-list\"]/div[2]/div/div[1]/a")
    private WebElement iPodNanoSelectorLink;

    @FindBy(css ="#product-list > div:nth-child(3) > div > div.content > div > h4 > a")
    private WebElement iPodShuffleSelectorLink;

    @FindBy(css ="div:nth-of-type(4) > .product-thumb h4 > a")
    private WebElement iPodTouchSelectorLink;

    @FindBy(xpath = "//*[@id=\"input-quantity\"]")
    private WebElement changeQtyOfIPodInputField;

    @FindBy(xpath = "//*[@id=\"button-cart\"]")
    private WebElement addToCartButton;

    @FindBy(css = "#alert")
    private WebElement successPopUpAlert;

    @FindBy(xpath = "//div[@id='product-list']//h4/a")
    private List<WebElement> productNames;

    @FindBy(xpath = "//*[@id=\"header-cart\"]")
    private WebElement shoppingCartButton;

    @FindBy(xpath = "//*[@id=\"header-cart\"]/div/ul/li/table/tbody/tr/td[3]")
    private WebElement shoppingCartPopUpProductQuantity;

    @FindBy(xpath = "//*[@id=\"header-cart\"]/div/ul/li/table/tbody/tr/td[2]")
    private WebElement shoppingCartPopUpProductName;

    @FindBy(xpath = "//*[@id=\"header-cart\"]/div/ul/li/table/tbody/tr/td[4]")
    private WebElement shoppingCartPopUpProductPrice;

    //methods
    public MP3Page(WebDriver driver) {
        super(driver);
    }

    public void clickOnListButton(){
        selectItemsAsListButton.click();
    }

    public void selectIPodClassic(){
        iPodClassicSelectorLink.click();
    }
    public void selectIPodNano(){
        iPodNanoSelectorLink.click();
    }
    public void selectIPodShuffle(){ //works fine with and without js method
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(iPodShuffleSelectorLink));


        System.out.println("Element visible: " + iPodShuffleSelectorLink.isDisplayed());
        System.out.println("Element enabled: " + iPodShuffleSelectorLink.isEnabled());
        System.out.println("Element location: " + iPodShuffleSelectorLink.getLocation());

        try {
            // element scroll
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", iPodShuffleSelectorLink);

            // click with js
            js.executeScript("arguments[0].click();", iPodShuffleSelectorLink);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //iPodShuffleSelectorLink.click();
    }
    public void selectIPodTouch(){ //doesn't work with simple method, with js it works fine

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        wait.until(ExpectedConditions.visibilityOf(iPodTouchSelectorLink));


        System.out.println("Element visible: " + iPodTouchSelectorLink.isDisplayed());
        System.out.println("Element enabled: " + iPodTouchSelectorLink.isEnabled());
        System.out.println("Element location: " + iPodTouchSelectorLink.getLocation());

        try {
            // element scroll
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", iPodTouchSelectorLink);

            // click with js
            js.executeScript("arguments[0].click();", iPodTouchSelectorLink);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //iPodTouchSelectorLink.click();
    }

    public void selectIPodQuantityInput(){
        changeQtyOfIPodInputField.clear();
        changeQtyOfIPodInputField.sendKeys("5");
    }
    public void clickAddToCartButton(){
        addToCartButton.click();
    }

    public void clickShoppingCartButton(){
        shoppingCartButton.click();
    }

    public String getProductName() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.visibilityOf(successPopUpAlert));
        return successPopUpAlert.getText();
    }

    public boolean isProductDisplayed(String productName) {
        return productNames.stream()
                .anyMatch(element -> element.getText().equalsIgnoreCase(productName));
    }

    public String getSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.visibilityOf(successPopUpAlert));
        return successPopUpAlert.getText();
    }

    public String getPopUpProductQuantity(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.visibilityOf(shoppingCartPopUpProductQuantity));
        return shoppingCartPopUpProductQuantity.getText();
    }

    public String getPopUpProductName(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.visibilityOf(shoppingCartPopUpProductName));
        return shoppingCartPopUpProductName.getText();
    }


    public String getPopUpProductPrice(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.visibilityOf(shoppingCartPopUpProductPrice));
        return shoppingCartPopUpProductPrice.getText();
    }

    public void selectProductByName(String productName) {
        //wait for the visibility of the last element in the list (4 in this case)
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(iPodTouchSelectorLink));
        //scroll down to bring last product item into viewport
        WebElement iPodtouch = driver.findElement(By.cssSelector("div:nth-of-type(4) > .product-thumb h4 > a"));
        Actions scrollDown = new Actions(driver);
        scrollDown.moveToElement(iPodtouch);
        scrollDown.perform();

        //loop for clicking onto products' links and addition them into shopping cart
        for (WebElement product : productNames) {
            if (product.getText().equalsIgnoreCase(productName)) {
                product.click();
                break;
            }
        }

    }



}
