package com.example;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.time.Duration;

public class AppTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        
        // Use Brave with ChromeDriver
        ChromeOptions options = new ChromeOptions();
        options.setBinary("C:/Program Files/BraveSoftware/Brave-Browser/Application/brave.exe"); // Update path if needed
        
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Using Duration for wait time
    }
  
    @Test
    public void test() {
        driver.get("https://the-internet.herokuapp.com/login");
        
        WebElement email = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        email.sendKeys("tomsmith");
        
        WebElement password = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
        password.sendKeys("SuperSecretPassword!");
        
        WebElement btn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("button[type='submit']")));
        btn.submit();
        
        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
        assertTrue(successMessage.getText().contains("You logged into a secure area!"), 
                   "Expected success message was not found.");
        
                   System.err.println(successMessage
                   .getText());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
