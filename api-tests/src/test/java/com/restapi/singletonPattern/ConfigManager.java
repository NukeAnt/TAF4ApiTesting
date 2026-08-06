package com.restapi.singletonPattern;

import java.util.Properties;

public class ConfigManager {

  private static ConfigManager instance;

  private final Properties properties =
      new Properties();

  private ConfigManager() {

    /*
     * PRIVATE CONSTRUCTOR
     *
     * Nikt nie może zrobić:
     *
     * new ConfigManager()
     *
     * poza samą klasą.
     */

    System.out.println(
        "Loading configuration..."
    );

    properties.setProperty(
        "baseUrl",
        "https://reqres.in"
    );
  }

  /*
   * SINGLETON PATTERN
   *
   * Cel:
   * Jedna instancja klasy
   * dla całej aplikacji.
   *
   * Typowe użycie:
   * konfiguracja,
   * driver manager,
   * token manager,
   * cache.
   */

  public static ConfigManager getInstance() {

    if (instance == null) {
      instance =
          new ConfigManager();
    }

    return instance;
  }

  public String getBaseUrl() {
    return properties.getProperty(
        "baseUrl"
    );
  }
}