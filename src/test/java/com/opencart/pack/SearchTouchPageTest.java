package com.opencart.pack;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SearchTouchPageTest extends BaseTest{

    //all of that is for 'bonus' section

    //method for 'Search Query Input' into search bar
    private void searchQueryInputTouch() {
        HomePage homePage = new HomePage(driver);

        homePage.inputSearchQueryIntoSearchInputField();
        homePage.clickSearchButton();
        System.out.println("The button is clicked");
    }

    //method for products' addition relevant by 'Touch' query
    private void addTouchProductsToShoppingCart() {
        SearchTouchPage searchTouchPage = new SearchTouchPage(driver);
        searchTouchPage.addHTCTouchToShoppingCart();
        System.out.println("HTC Touch is added");

        searchTouchPage.addiPodTouchToShoppingCart();
        System.out.println("iPod Touch is added");

        ShoppingCartPage.AlertHandler alertHandler = new ShoppingCartPage.AlertHandler(driver);
        alertHandler.handleAlert();

        searchTouchPage.clickShoppingCartButton();
        System.out.println("Shopping cart button is clicked");

        searchTouchPage.clickOnCheckoutLink();
        System.out.println("The checkout link is clicked");
    }

    //method for shopping cart page shipping confirmation part (country,region,quotes button clicking,shipping rate selection,shipping application)
    private void shoppingCartShippingConfirmation() {
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);

        shoppingCartPage.clickOnEstimateShippingAndTaxesMenu();
        System.out.println("Estimate Shipping and Taxes menu is clicked");

        shoppingCartPage.clickOnCountrySelectorMenu();
        System.out.println("Country Selector menu is clicked");

        shoppingCartPage.selectLithuaniaFromCountryMenu();
        System.out.println("Lithuania is selected");

        shoppingCartPage.selectRegionSelectorMenu();
        System.out.println("Region Selector menu is clicked");

        shoppingCartPage.selectVilniusFromRegionSelectorMenu();
        System.out.println("Vilnius is selected");

        shoppingCartPage.selectGetQuotesButton();
        System.out.println("Get Quotes button is clicked");

        shoppingCartPage.selectShippingRate();
        System.out.println("Shipping Rate button is clicked");

        shoppingCartPage.selectApplyShippingButton();
        System.out.println("Apply Shipping button is clicked");

    }


    //search the page for 'Touch'
    @Test
    void searchQueryTest() {
        SearchTouchPage searchTouchPage = new SearchTouchPage(driver);
        searchQueryInputTouch();

        //list with for loop for outputting product names having 'Touch' in their composition
        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        List<String> productsContainingTouch = shoppingCartPage.getProductNamesContainingWord("Touch");
        for (String productName : productsContainingTouch) {
            System.out.println("Product containing 'Touch': " + productName);
        }



        boolean allProductsContainTouch = searchTouchPage.areAllProductsContainingWord("Touch");
        assertTrue(allProductsContainTouch, "Not all products contain the word 'Touch'");
        System.out.println(allProductsContainTouch); // -> verification whether the assertion works or not
    }

    //add all 'touch' products to cart
    @Test
    void addTouchProductsToCartTest(){
        SearchTouchPage searchTouchPage = new SearchTouchPage(driver);
        searchQueryInputTouch();
        addTouchProductsToShoppingCart(); // -> 'Touch' products' addition by clicking on each elements' 'Add To Cart' button

    }

    //shopping cart test
    @Test
    void shoppingCartTest(){
        SearchTouchPage searchTouchPage = new SearchTouchPage(driver);
        searchQueryInputTouch();
        addTouchProductsToShoppingCart();
        shoppingCartShippingConfirmation(); // -> shipping confirmation by clicking on shipping option and click on 'Apply Shipping' button
    }

    //total price calculation with tax
    @Test
    void totalPriceCalcluationWithTaxTest() {

        ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
        searchQueryInputTouch();
        addTouchProductsToShoppingCart();

        // total values retrieved from the table
        WebElement subTotalElement = driver.findElement(By.xpath("//*//div[@id='shopping-cart']//table[@class='table table-bordered']/tbody/tr[1]/td[6]"));
        WebElement shippingRateElement = driver.findElement(By.xpath("//*//div[@id='shopping-cart']//table[@class='table table-bordered']/tbody/tr[2]/td[6]"));
        WebElement totalElement = driver.findElement(By.xpath("//tfoot[@id='checkout-total']/tr[4]/td[2]"));


        String subTotal = subTotalElement.getText().replaceAll("[^\\d.]", ""); // Remove non-numeric characters($ in this case)
        String shippingRate = shippingRateElement.getText().replaceAll("[^\\d.]", "");
        String total = totalElement.getText().replaceAll("[^\\d.]", "");

        /*
        String subTotal = subTotalElement.getText();    //same method but with text extraction
        String shippingRate = shippingRateElement.getText();
        String total = totalElement.getText();
        */
        //calculations' conversion to double
        double subTotalValue = Double.parseDouble(subTotal);
        double shippingRateValue = Double.parseDouble(shippingRate);
        double totalValue = Double.parseDouble(total);

        // calculation op
        double expectedTotal = subTotalValue + shippingRateValue;

        System.out.println("Subtotal: " + "$" + subTotalValue);
        System.out.println("Shipping Rate: " + "$" + shippingRateValue);
        System.out.println("Total: " + "$" + totalValue);
        System.out.println("Expected Total: " + "$" + expectedTotal);

        // assertion (the calculated total matches the expected total)
        assertEquals(expectedTotal, totalValue, "Total calculation is incorrect");
    }

}
