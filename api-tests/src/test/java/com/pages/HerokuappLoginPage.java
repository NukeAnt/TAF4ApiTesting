package com.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HerokuappLoginPage
{
  private WebDriver driver;

  // locators
  private By username = By.id("username");
  private By password = By.id("password");
  private By loginButton = By.xpath("//button[@type='submit']");
  private By flashMessage = By.id("flash");

  public HerokuappLoginPage(WebDriver driver) {
    this.driver = driver;
  }

  public void open() {
    driver.get("https://the-internet.herokuapp.com/login");
  }

  public void login(String user, String pass) {
    driver.findElement(username).sendKeys(user);
    driver.findElement(password).sendKeys(pass);
    driver.findElement(loginButton).click();
  }

  public String getFlashMessage() {
    return driver.findElement(flashMessage).getText();
  }
}
