package com.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import java.time.Duration;
import static org.testng.Assert.*;

public class GoogleBaseTest extends BaseTest
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
    By username = By.id("username");
    By password = By.id("password");
    By submitBtn = By.xpath("//button[@type='submit']");
    By flashMsg = By.id("flash");

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

  @Test
  void shouldCheckDynamicElements()
  {
    driver = setup();
    driver.get("https://the-internet.herokuapp.com/dynamic_controls");
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    By checkbox = By.xpath("//div[@id='checkbox']");
    By removeButton = By.xpath("//button[text()='Remove']");
    By addButton = By.xpath("//button[text()='Add']");
    By message = By.id("message");

    wait.until(ExpectedConditions.visibilityOfElementLocated((checkbox))); // wait until checkbox is visible
    driver.findElement(removeButton).click();
    wait.until(ExpectedConditions.invisibilityOfElementLocated(checkbox)); // wait until checkbox is invisible
    wait.until(ExpectedConditions.visibilityOfElementLocated(message)); // wait until message is visible
    assertEquals(driver.findElement(message).getText(), "It's gone!", "Message should indicate that checkbox is gone");

    driver.findElement(addButton).click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(checkbox)); // wait until checkbox is visible again
    wait.until(ExpectedConditions.textToBePresentInElementLocated(message, "It's back!"));
    wait.until(ExpectedConditions.visibilityOfElementLocated(message)); // wait until message is visible
    assertEquals(driver.findElement(message).getText(), "It's back!", "Message should indicate that checkbox is back");
  }

  @Test
  void shouldLoginTest() {
    driver = setup();
    driver.get("https://the-internet.herokuapp.com/login");

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    By username = By.id("username");
    By password = By.id("password");
    By loginButton = By.xpath("//button[@type='submit']");
    By message = By.id("flash");

    // zawsze czekamy, zanim zaczniemy wpisywacc do pola, bo może się zdarzyć, że strona będzie się jeszcze ładować i elementy nie będą od razu dostępne
    wait.until(ExpectedConditions.visibilityOfElementLocated(username));
    driver.findElement(username).sendKeys("tomsmith");
    driver.findElement(password).sendKeys("SuperSecretPassword!");
    driver.findElement(loginButton).click();

    // wait.until(ExpectedConditions.visibilityOf(driver.findElement(message))); <- źle, bo elementu może jeszcze nie być dla findElement, więc lepiej użyć ExpectedConditions.visibilityOfElementLocated
    wait.until(ExpectedConditions.visibilityOfElementLocated(message)); // czekamy aż element będzie widoczny, a nie że już jest znaleziony, bo findElement może rzucić wyjątek jeśli elementu jeszcze nie ma w DOM

    String messageText = driver.findElement(message).getText();
    System.out.println("Flash message: " + messageText);
    assertTrue(messageText.contains("You logged into a secure area!"), "Flash message should indicate successful login");
  }

  @AfterTest
  void afterTest() {
    teardown();
  }
}
