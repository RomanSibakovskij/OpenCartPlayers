package com.opencart.pack;

import org.junit.jupiter.api.Test;
public class HomePageTest extends BaseTest{

    private void clickOnMP3Links() {
        HomePage homePage = new HomePage(driver);

        homePage.clickMP3Link();
        System.out.println("The link is clicked");

        homePage.clickAllMP3PlayersSectionLink();
        System.out.println("The section was clicked on");

    }
    private void searchQueryInputTouch() {
        HomePage homePage = new HomePage(driver);

        homePage.inputSearchQueryIntoSearchInputField();
        homePage.clickSearchButton();
    }


    //select MP3 link test
    @Test
    void clickOnMP3LinksTest(){
        clickOnMP3Links();
    }

    //search the page for 'Touch'
    @Test
    void searchQueryTest(){
        searchQueryInputTouch();
        System.out.println("The search query 'Touch' was input");
    }



}
