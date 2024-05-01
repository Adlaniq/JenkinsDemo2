package com.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
    WebDriver driver;
    
    @BeforeMethod
    public void setup() {
        String browser = System.getProperty("browser", "Chrome").toLowerCase(); // Default to Chrome if not specified
        
        switch (browser) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                
                // Set Chrome options including path to Chrome binary
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setBinary("C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
                driver = new ChromeDriver(chromeOptions);
                break;
                
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;
                
            case "ie":
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
                
            default:
                throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
        
        driver.manage().window().maximize();
        driver.get(System.getProperty("url", "https://www.google.com")); // Get URL from system property or default to Google
    }
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.close();
            driver.quit(); // Ensure all sessions are closed
        }
    }
}
