package com.opencart.pack;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

public class MP3PageTest extends BaseTest{
    //main test part (2 - 11)
    private void clickOnMP3Links() {
        HomePage homePage = new HomePage(driver);

        homePage.clickMP3Link();
        homePage.clickAllMP3PlayersSectionLink();
    }
    private void mp3PageListSelectButton() {
        clickOnMP3Links();
        MP3Page mp3Page = new MP3Page(driver);

        mp3Page.clickOnListButton();
        System.out.println("The button is clicked");
    }
    //click on list
    @Test
    void mp3PageInteractionTest(){
        mp3PageListSelectButton();

    }

    //read all names from csv file test
    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/testdata.csv", numLinesToSkip = 1)
    void testDataWithCSVFileSource(String productName) {
        clickOnMP3Links();
        MP3Page mp3Page = new MP3Page(driver);

        // assertion the product name is displayed
        assertNotNull(productName, "Product name should not be null");
        System.out.println("Testing product: " + productName);

        // Verify that the product is displayed and name matches
        boolean isDisplayed = mp3Page.isProductDisplayed(productName.trim());
        assertTrue(isDisplayed, "Product not displayed: " + productName);
        System.out.println(String.valueOf(isDisplayed)); // -> output the textual result into console log
    }

    //IPodClassic Test addition to cart
    @Test
    void selectIPodClassicTest(){
        MP3Page mp3Page = new MP3Page(driver);

        mp3PageListSelectButton();

        mp3Page.selectIPodClassic();
        System.out.println("The item was selected");

        mp3Page.selectIPodQuantityInput();
        System.out.println("The quantity was input into field");

        mp3Page.clickAddToCartButton();
        System.out.println("The button was clicked");

        String expectedMessage = "Success: You have added iPod Classic to your shopping cart!";
        String actualMessage = mp3Page.getSuccessMessage();
        assertEquals(expectedMessage, actualMessage, "The success message is incorrect.");
        System.out.println("Message on display: " + actualMessage);

    }

    //IPodNano Test addition to cart
    @Test
    void selectIPodNanoTest(){
        MP3Page mp3Page = new MP3Page(driver);

        mp3PageListSelectButton();

        mp3Page.selectIPodNano();
        System.out.println("The item was selected");

        mp3Page.selectIPodQuantityInput();
        System.out.println("The quantity was input into field");

        mp3Page.clickAddToCartButton();
        System.out.println("The button was clicked");

        String expectedMessage = "Success: You have added iPod Nano to your shopping cart!";
        String actualMessage = mp3Page.getSuccessMessage();
        assertEquals(expectedMessage, actualMessage, "The success message is incorrect.");
        System.out.println("Message on display: " + actualMessage);
    }

    //IpodShuffle Test addition to cart
    @Test
    void selectIPodShuffleTest(){
        MP3Page mp3Page = new MP3Page(driver);

        mp3PageListSelectButton();

        mp3Page.selectIPodShuffle();
        System.out.println("The item was selected");

        mp3Page.selectIPodQuantityInput();
        System.out.println("The quantity was input into field");

        mp3Page.clickAddToCartButton();
        System.out.println("The button was clicked");

        String expectedMessage = "Success: You have added iPod Shuffle to your shopping cart!";
        String actualMessage = mp3Page.getSuccessMessage();
        assertEquals(expectedMessage, actualMessage, "The success message is incorrect.");
        System.out.println("Message on display: " + actualMessage);
    }

    //IpodTouch Test addition to cart
    @Test
    void selectIPodTouchTest(){
        MP3Page mp3Page = new MP3Page(driver);

        mp3PageListSelectButton();

        mp3Page.selectIPodTouch();
        System.out.println("The item was selected");

        mp3Page.selectIPodQuantityInput();
        System.out.println("The quantity was input");

        mp3Page.clickAddToCartButton();
        System.out.println("The button was clicked");

        String expectedMessage = "Success: You have added iPod Touch to your shopping cart!";
        String actualMessage = mp3Page.getSuccessMessage();
        assertEquals(expectedMessage, actualMessage, "The success message is incorrect.");
        System.out.println("Message on display: " + actualMessage);

    }
    //extract product names and assure they're displayed -> hiccup on Ipod Touch ElementClickInterceptedException
    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/testdata.csv", numLinesToSkip = 1)
    void testAddProductsToCart(String productName) {
        clickOnMP3Links();
        MP3Page mp3Page = new MP3Page(driver);
        mp3PageListSelectButton();

        // assertion the product is displayed
        assertNotNull(productName, "Product name should not be null");
        boolean isDisplayed = mp3Page.isProductDisplayed(productName.trim());

        if (!isDisplayed) {
            System.out.println(productName + " does not exist in the eshop");
            return;
        }

        // select/add product to cart
        mp3Page.selectProductByName(productName.trim());//name selection
        mp3Page.selectIPodQuantityInput();//qty input
        mp3Page.clickAddToCartButton();//'add to cart' button click

        // shopping cart button clicking for viewing the cart details
        mp3Page.clickShoppingCartButton();


        // Assert cart details
        String actualProductName = mp3Page.getPopUpProductName();
        String actualProductQuantity = mp3Page.getPopUpProductQuantity().replaceAll("\\D", "");
        String actualProductPrice = mp3Page.getPopUpProductPrice().replaceAll("[^\\d.]", "");

        assertEquals(productName, actualProductName, "Product name in cart does not match.");
        assertEquals("5", actualProductQuantity, "Product quantity in cart does not match.");
        double totalPrice = Double.parseDouble(actualProductPrice); // string to double parse
        double expectedPrice = 122.00 * 5; // total price expected calculation
        assertEquals(expectedPrice, totalPrice, 0.01, "Product price in cart does not match.");
    }


}
