package com.example;

public class ConfigManager {

  private static ConfigManager instance;

  private final String baseUrl;

  private ConfigManager() {

    baseUrl = "https://jsonplaceholder.typicode.com";
  }

  /*
   * SINGLETON PATTERN
   *
   * Jedna instancja konfiguracji
   * dla całego frameworka.
   */
  public static ConfigManager getInstance() {

    if(instance == null) {
      instance =
          new ConfigManager();
    }

    return instance;
  }

  public String getBaseUrl() {
    return baseUrl;
  }
}
