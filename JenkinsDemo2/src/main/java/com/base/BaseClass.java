package com.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseClass {
    WebDriver driver;
    
    @BeforeMethod
    public void setup() {
        String browser = System.getProperty("browser", "Chrome").toLowerCase(); // Default to Chrome if not specified
        
        if (browser.equals("chrome")) {
            // Set the path to the ChromeDriver you've downloaded
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\adlaniq\\Downloads\\chrome-headless-shell-win32\\chromedriver.exe");

            // Initialize Chrome options if needed
            ChromeOptions options = new ChromeOptions();
            // Uncomment the next line if you want to run Chrome in headless mode
            // options.addArguments("--headless");
            
            driver = new ChromeDriver(options);
        } else {
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
