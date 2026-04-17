package com.selenium;

import com.pages.HerokuappDynamicControls;
import com.pages.HerokuappLogin;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

public class HerokuappTest extends BaseTest
{
  @Test
  void testLogin()
  {
    driver = setup();
    HerokuappLogin loginPage = new HerokuappLogin(driver);
    loginPage.open();
    loginPage.login("tomsmith", "SuperSecretPassword!");
    String flashMessage = loginPage.getFlashMessage();
    assertTrue(flashMessage.contains("You logged into a secure area!"));
  }

  @Test
  void testLoginWithInvalidCredentials()
  {
    driver = setup();
    HerokuappLogin loginPage = new HerokuappLogin(driver);
    loginPage.open();
    loginPage.login("invalidUser", "invalidPass");
    String flashMessage = loginPage.getFlashMessage();
    assertTrue(flashMessage.contains("Your username is invalid!"));
  }

  @Test
  void testDynamicControls()
  {
    driver = setup();
    HerokuappDynamicControls dynamicControlsPage = new HerokuappDynamicControls(driver);
    dynamicControlsPage.open();
    dynamicControlsPage.clickRemoveButton();
    dynamicControlsPage.waitForCheckboxToDisappear();
    dynamicControlsPage.waitForMessageToAppear();
    String messageAfterRemove = dynamicControlsPage.getMessageText();
    assertTrue(messageAfterRemove.contains("It's gone!"), "Message should indicate that the checkbox is gone");

    dynamicControlsPage.clickAddButton();
    dynamicControlsPage.waitForCheckboxToAppear();
    dynamicControlsPage.waitForMessageToAppear();
    String messageAfterAdd = dynamicControlsPage.getMessageText();
    assertTrue(messageAfterAdd.contains("It's back!"), "Message should indicate that the checkbox is back");
  }

  @AfterTest
  void tearDown() {
    teardown();
  }
}
