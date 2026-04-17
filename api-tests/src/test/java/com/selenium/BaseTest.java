package com.selenium;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import java.nio.file.Paths;

class BaseTest
{
  WebDriver driver;
  private static String getDriverPath() {
    return Paths.get(
        System.getProperty("user.dir"),
        "src", "test", "resources", "edgeDriver", "msedgedriver.exe"
    ).toString();
  }

  public static WebDriver setup() {
    System.setProperty("webdriver.edge.driver", getDriverPath());

    EdgeOptions options = new EdgeOptions();
    options.addArguments("--start-maximized");

    return new EdgeDriver(options);
  }

  public static WebDriver setupHeadless() {
    System.setProperty("webdriver.edge.driver", getDriverPath());

    EdgeOptions options = new EdgeOptions();
    options.addArguments("--headless=new");
    options.addArguments("--window-size=1920,1080");

    return new EdgeDriver(options);
  }

  void teardown() {
    if (driver != null) {
      driver.quit();
    }
  }
}