package com.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import java.time.Duration;
import static org.testng.Assert.*;

public class GoogleTest extends TestSetup
{

  @Test
  void testGoogleSearch() {
    driver = setupHeadless();
    driver.get("https://www.google.com");
    String title = driver.getTitle();
    assertEquals(title, "Google");
  }

  @Test
  void shouldOpenGoogle() {
    driver = setup();
    driver.manage().window().maximize();
    driver.get("https://www.google.com");
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

    String title = driver.getTitle();
    assertTrue(title.contains("Google"));
  }

  @Test
  void logIntoSecureArea()
  {
    driver = setup();
    driver.get("https://the-internet.herokuapp.com/login");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // wait and conditions
    WebElement usernameField = driver.findElement(By.id("username"));
    WebElement passwordField = driver.findElement(By.id("password"));
    WebElement submitButton = driver.findElement(By.cssSelector("button[type='submit']"));

    wait.until(ExpectedConditions.urlContains("login")); // wait until URL contains "login"
    wait.until(d -> d.getTitle().contains("The Internet")); // wait until title contains "The Internet"
    wait.until(ExpectedConditions.visibilityOf(usernameField)); // wait until username field is visible
    wait.until(ExpectedConditions.textToBePresentInElement(submitButton, "Login")); // wait until submit button has text "Login"
    wait.until(ExpectedConditions.elementToBeClickable(submitButton)); // wait until submit button is clickable
    wait.until(d -> d.findElement(By.id("password")).isDisplayed()); // wait until password field is displayed using lambda

    usernameField.sendKeys("tomsmith");
    passwordField.sendKeys("SuperSecretPassword!");
    driver.findElement(By.xpath("//button[@type='submit']")).click();

    wait.until(ExpectedConditions.urlContains("/secure")); // wait until URL contains "/secure"
    WebElement flashMessage = driver.findElement(By.id("flash"));
    wait.until(ExpectedConditions.visibilityOf(flashMessage)); // wait until flash message is visible
    assertTrue(flashMessage.getText().contains("You logged into a secure area!"), "Flash message should indicate successful login");
  }

  @AfterTest
  void afterTest() {
    teardown();
  }
}
