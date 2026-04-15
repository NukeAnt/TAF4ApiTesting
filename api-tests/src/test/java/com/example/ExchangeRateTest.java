package com.example;

import com.example.pojoClasses.ExchangeRateRangeResponse;
import com.example.pojoClasses.ExchangeRateResponse;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Map;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertNotNull;
import static org.testng.AssertJUnit.assertTrue;

@Slf4j
public class ExchangeRateTest
{
  @BeforeEach
  void setup() {
    RestAssured.baseURI = "https://api.frankfurter.dev/v1";
  }

  @Test
  void shouldGetLatestGbpToUsdRate() {
    ExchangeRateResponse response =
        given()
            .queryParam("base", "GBP")
            .queryParam("symbols", "USD")
            .when()
            .get("/latest")
            .then()
            .statusCode(200)
            .extract()
            .as(ExchangeRateResponse.class);

    log.info(response.toString());
    Double usdRate = response.getRates().get("USD");

    assertNotNull(usdRate);
    assertTrue(usdRate > 0);

    System.out.println("GBP -> USD: " + usdRate);
  }

  @Test
  void shouldGetHistoricalRate() {
    ExchangeRateResponse response =
        given()
            .queryParam("base", "GBP")
            .queryParam("symbols", "USD")
            .when()
            .get("/2025-12-20")
            .then()
            .statusCode(200)
            .extract()
            .as(ExchangeRateResponse.class);

    // response.toString();
    Double usdRate = response.getRates().get("USD");

    assertNotNull(usdRate);
    System.out.println("Rate on 2025-12-20: " + usdRate);
  }

  @Test
  void shouldGetMonthlyAverageRate() {
    ExchangeRateRangeResponse response =
        given()
            .queryParam("base", "GBP")
            .queryParam("symbols", "USD")
            .when()
            .get("/2025-12-01..2025-12-31")
            .then()
            .statusCode(200)
            .extract()
            .as(ExchangeRateRangeResponse.class);

    Double usdRate = response.getRates().values().stream()
        .map(day -> day.get("USD"))
        .filter(rate -> rate != null)
        .mapToDouble(Double::doubleValue)
        .average()
        .orElse(0.0);

    assertNotNull(usdRate);
    System.out.println("Monthly average rate for December 2025: " + usdRate);
  }

  @Test
  void shouldGetHighestRateInDateRange()
  {
    ExchangeRateRangeResponse response =
        given()
            .queryParam("base", "GBP")
            .queryParam("symbols", "USD")
            .when()
            .get("/2024-12-01..2025-12-31")
            .then()
            .statusCode(200)
            .extract()
            .as(ExchangeRateRangeResponse.class);

    Map<String, Map<String, Double>> rates = response.getRates();

    Map.Entry<String, Double> bestRateEntry = rates.entrySet().stream()
        .map(entry -> Map.entry(entry.getKey(), entry.getValue().get("USD")))
        .filter(entry -> entry.getValue() != null)
        .max(Map.Entry.comparingByValue())
        .orElseThrow();

    String bestDate = bestRateEntry.getKey();
    Double bestRate = bestRateEntry.getValue();

    System.out.println("Best GBP -> USD rate: " + bestRate + " on " + bestDate);
  }
}
