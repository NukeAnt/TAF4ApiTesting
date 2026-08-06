package com.example.factoryClasses;

import com.example.pojoClasses.UserPojoBuilder;

public class UserFactory {

  /*
   * FACTORY PATTERN
   *
   * Ukrywa logikę tworzenia danych testowych.
   *
   * Test nie musi wiedzieć
   * jak wygląda poprawny user.
   */

  public static UserPojoBuilder validUser() {

    return UserPojoBuilder.builder()
        .name("John Doe")
        .username("johndoe")
        .email("john@test.com")
        .build();
  }

  public static UserPojoBuilder invalidEmailUser() {

    return UserPojoBuilder.builder()
        .name("John Doe")
        .username("johndoe")
        .email("invalid-email")
        .build();
  }
}