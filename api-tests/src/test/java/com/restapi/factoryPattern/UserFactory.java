package com.restapi.factoryPattern;

import com.restapi.builderPattern.UserRequestBuilderTest;

public class UserFactory {

  /*
   * FACTORY PATTERN
   *
   * Factory ukrywa logikę tworzenia obiektów.
   *
   * Test nie musi wiedzieć
   * jak zbudować poprawnego użytkownika.
   *
   * Wystarczy:
   *
   * UserFactory.validUser();
   *
   * Dzięki temu dane testowe są
   * utrzymywane w jednym miejscu.
   */

  public static UserRequestBuilderTest validUser() {
    return UserRequestBuilderTest.builder()
        .firstName("John")
        .lastName("Smith")
        .email("john@test.com")
        .age(35)
        .active(true)
        .build();
  }

  public static UserRequestBuilderTest invalidEmailUser() {
    return UserRequestBuilderTest.builder()
        .firstName("John")
        .lastName("Smith")
        .email("not-an-email")
        .age(35)
        .active(true)
        .build();
  }

  public static UserRequestBuilderTest underageUser() {
    return UserRequestBuilderTest.builder()
        .firstName("John")
        .lastName("Smith")
        .email("john@test.com")
        .age(-5)
        .active(true)
        .build();
  }
}