package com.opencart.pack;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static java.time.Duration.ofSeconds;

public class HomePage extends BasePage{
    //elements
    @FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul/li[8]/a")
    private WebElement mp3Link;

    @FindBy(xpath = "//*[@id=\"narbar-menu\"]/ul/li[8]/div/a")
    private WebElement allMP3PlayersSectionLink;

    @FindBy(css = "#search > input")
    private WebElement searchInputField;

    @FindBy(xpath = "//*[@id=\"search\"]/button")
    private WebElement searchButton;

    //methods
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void clickMP3Link (){
        mp3Link.click();
    }
    public void clickAllMP3PlayersSectionLink(){
        allMP3PlayersSectionLink.click();
    }
    public void inputSearchQueryIntoSearchInputField(){
        searchInputField.clear();
        searchInputField.sendKeys("Touch");
    }
    public void clickSearchButton(){
        searchButton.click();
    }
}
