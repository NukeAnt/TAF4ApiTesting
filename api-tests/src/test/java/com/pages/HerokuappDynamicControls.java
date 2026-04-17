package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class HerokuappDynamicControls
{
  private WebDriver driver;
  public HerokuappDynamicControls(WebDriver driver) {
    this.driver = driver;
  }

  By checkbox = By.xpath("//*[@id='checkbox']");
  By removeButton = By.xpath("//button[text()='Remove']");
  By addButton = By.xpath("//button[text()='Add']");
  By message = By.id("message");

  public void open()
  {
    driver.get("https://the-internet.herokuapp.com/dynamic_controls");
  }

  public void clickRemoveButton()
  {
    driver.findElement(removeButton).click();
  }

  public void clickAddButton()
  {
    driver.findElement(addButton).click();
  }

  public String getMessageText()
  {
    return driver.findElement(message).getText();
  }

  public boolean isCheckboxVisible()
  {
    return driver.findElements(checkbox).size() > 0;
  }

  public void waitForCheckboxToDisappear()
  {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    wait.until(invisibilityOfElementLocated(checkbox));
  }

  public void waitForCheckboxToAppear()
  {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    wait.until(visibilityOfElementLocated(checkbox));
  }

  public void waitForMessageToAppear()
  {
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    wait.until(visibilityOfElementLocated(message));
  }

}
