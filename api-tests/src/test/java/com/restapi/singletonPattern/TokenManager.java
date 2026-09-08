package com.restapi.singletonPattern;

public class TokenManager {

  private static TokenManager instance;

  private String token;

  private TokenManager() {
  }

  public static TokenManager getInstance() {

    if (instance == null) {
      instance =
          new TokenManager();
    }

    return instance;
  }

  public String getToken() {

    if (token == null) {

      token = null;
       //   AuthService.loginAndGetToken();
    }

    return token;
  }
}
