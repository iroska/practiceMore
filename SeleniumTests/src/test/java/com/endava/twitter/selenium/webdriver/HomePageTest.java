package com.endava.twitter.selenium.webdriver;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static junit.framework.Assert.assertTrue;

/**
 * Created by ibalanici on 21/10/2015.
 */
public class HomePageTest {

    @Test
    public void startWebDriver() {

        System.setProperty("webdriver.chrome.driver", "D:\\apps\\testing\\chromedriver.exe");
        WebDriver webDriver = new ChromeDriver();

//        WebDriver webDriver = new FirefoxDriver();

        webDriver.navigate().to("http://seleniumsimplified.com");
        assertTrue("Title a little bit different", webDriver.getTitle().startsWith("Selenium Simplified"));

        webDriver.close();

    }
}
