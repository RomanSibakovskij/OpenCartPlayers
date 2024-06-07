package com.opencart.pack;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {
    WebDriver driver;


    @BeforeEach
    void setup(){
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://192.168.88.221/");
    }




    //close
    @AfterEach
        void close() throws InterruptedException {
        Thread.sleep(5500); // -> for now it's a very forced option since none other methods works here and it's imprudent to put separate waits just for this purpose alone(closure of the page)
        /* //the wait method doesn't seem to work due to the speed of driver work
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
       wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        */
       driver.quit();
    }



}

