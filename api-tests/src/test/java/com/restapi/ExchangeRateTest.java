package com.restapi;

import com.example.pojoClasses.ExchangeRateRangeResponse;
import com.example.pojoClasses.ExchangeRateResponse;
import io.restassured.RestAssured;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.*;
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

  @Test
  void shouldTestCodeInside()
  {
    List<String> emails = Arrays.asList(
        "john@test.com",
        "kate@test.com",
        "john@test.com"
    );

    Set<String> uniqueEmails =
        new HashSet<>(emails);

    if (uniqueEmails.size() == emails.size())
    {
      System.out.println("No duplicates");
    }
    else
    {
      System.out.println("Duplicates found");
    }

    List<String> emailz = new ArrayList<>();
    emailz.add("john@test.com");
    emailz.add("kate@test.com");
    emailz.add("john2@test.com");

    // wywali ConcurrentModificationException, bo nie można usuwać elementów z listy podczas iteracji po niej
/*    for (String email : emailz)
    {
      if (email.startsWith("john"))
      {
        emailz.remove(email);
      }
    }

    System.out.println(emailz);*/

    List<String> emails2 = new ArrayList<>();

    emails2.add("john@test.com");
    emails2.add("kate@test.com");
    emails2.add("john2@test.com");

    List<String> filtered =
        emails2.stream()
            .filter(email ->
                !email.startsWith("john"))
            .toList();

    emails2.removeIf(
        email -> email.startsWith("john")
    );
    System.out.println(emails2);
    System.out.println(filtered);
  }
}
