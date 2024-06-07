package com.opencart.pack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCartPage extends BasePage{
    //elements
    @FindBy(xpath = "//*[@id=\"accordion\"]/div[1]")
    private WebElement estimateShippingAndTaxesMenu;

    @FindBy(css = "select#input-country")
    private WebElement countrySelectorMenu;

    @FindBy(css = "#input-country > option:nth-child(132)")
    private WebElement selectLithuaniaFromCountrySelectorMenu;

    @FindBy(xpath = "//*[@id=\"form-quote\"]/div[2]/div")
    private WebElement regionSelectorMenu;

    @FindBy(css = "#input-zone > option:nth-child(11)")
    private WebElement selectVilniusFromRegionSelectorMenu;

    @FindBy(xpath = "//*[@id=\"button-quote\"]")
    private WebElement selectGetQuotesButton;

    @FindBy(css = "#input-shipping-method-flat-flat")
    private WebElement selectShippingRate;

    @FindBy(css = "#button-shipping-method")
    private WebElement selectApplyShippingButton;

    @FindBy(css = ".product-layout .product-thumb")
    private List<WebElement> productList;


    //methods
    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public static class AlertHandler {
        private WebDriver driver; // -> driver for alert, not the general driver
        private WebDriverWait wait;

        //alert 'handler' method
        public AlertHandler(WebDriver driver) {
            this.driver = driver;
            this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Adjust the timeout as needed
        }

        //alert 'handle' method
        public void handleAlert() {
            WebElement toastContainer = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("alert")));
            toastContainer.click();
        }
    }

    //click methods
    public void clickOnEstimateShippingAndTaxesMenu() {
        estimateShippingAndTaxesMenu.click();
    }
    public void clickOnCountrySelectorMenu() { // somehow it's a breakpoint for smooth test run
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6));
        wait.until(ExpectedConditions.elementToBeClickable(countrySelectorMenu));
        countrySelectorMenu.click();
    }
    public void selectLithuaniaFromCountryMenu() {
        selectLithuaniaFromCountrySelectorMenu.click();
    }
    public void selectRegionSelectorMenu() {
        regionSelectorMenu.click();
    }

    public void selectVilniusFromRegionSelectorMenu() {
        selectVilniusFromRegionSelectorMenu.click();
    }
    public void selectGetQuotesButton() {
        selectGetQuotesButton.click();
    }
    public void selectShippingRate() {
        selectShippingRate.click();
    }
    public void selectApplyShippingButton() {
        selectApplyShippingButton.click();
    }

    //String/boolean methods for verification
    public List<String> getProductNamesContainingWord(String word) {
        List<String> productNames = new ArrayList<>();
        for (WebElement product : productList) {
            String productName = product.findElement(By.tagName("h4")).getText();
            if (productName.contains(word)) {
                productNames.add(productName);
            }
        }
        return productNames;
    }

    public boolean areAllProductsContainingWord(String word) {
        for (WebElement product : productList) {
            String productName = product.findElement(By.tagName("h4")).getText();
            if (!productName.contains(word)) {
                return false;
            }
        }
        return true;
    }

}
