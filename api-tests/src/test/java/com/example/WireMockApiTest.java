package com.example;

import com.example.wireMockServer.WireMockBaseTest;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WireMockApiTest extends WireMockBaseTest
{
  @BeforeEach
  void setUp()
  {
    startWireMockServer();
  }

  @AfterEach
  void tearDown()
  {
    stopWireMockServer();
  }

  Response response;

  @Test
  @Order(1)
  public void getInitialWireMockResponse()
  {
    String url = getBaseUrl() + "/getInitialResponse";
    System.out.println("Testing GET request to: " + url);

    response = RestAssured.given()
        .when()
        .get(url);

    assertEquals(200, response.getStatusCode());
    response.prettyPrint();
  }

  @Test
  @Order(2)
  public void postUpdateToWireMock()
  {
    WireMock.givenThat(WireMock.post(WireMock.urlMatching("/post"))
        .willReturn(WireMock.aResponse()
            .withHeader("Content-Type", "application/json")
            .withBodyFile("PostUpdateDataSetResponse.json")
            .withStatus(201)
    ));

    String url = getBaseUrl() + "/post";
    System.out.println("Testing POST request to: " + url);

    response = RestAssured.given()
        .contentType(ContentType.JSON)
        .body("    {\n" +
            "      \"id\": 4,\n" +
            "      \"name\": \"POST method test\",\n" +
            "      \"description\": \"element updated with POST method\",\n" +
            "      \"price\": \"$28.88\",\n" +
            "      \"createdAt\": \"2024-06-02T12:00:00Z\"\n" +
            "    }")
        .when()
        .post(url);

    response.prettyPrint();
    assertEquals(201, response.getStatusCode());
  }

  @Test
  @Order(3)
  public void getUpdatedDataTest()
  {
    WireMock.givenThat(WireMock.get(WireMock.urlMatching("/getUpdated"))
        .willReturn(WireMock.aResponse()
            .withHeader("Content-Type", "application/json")
            .withBodyFile("GetUpdatedDataSetResponse.json")
            .withStatus(200)
    ));

    String url = getBaseUrl() + "/getUpdated";
    System.out.println("Testing GET request to: " + url);

    response = RestAssured.given()
        .when()
        .get(url);

    response.prettyPrint();
    assertEquals(200, response.getStatusCode());
    assertTrue(response.getBody().prettyPrint().contains("element updated with POST method"));
  }

  @Test
  @Order(4)
  public void putNewDataTest()
  {
    WireMock.givenThat(WireMock.put(WireMock.urlMatching("/put"))
        .willReturn(WireMock.aResponse()
            .withHeader("Content-Type", "application/json")
            .withBodyFile("PutAddToDataSetResponse.json")
            .withStatus(201)
    ));

    String url = getBaseUrl() + "/put";
    System.out.println("Testing PUT request to: " + url);

    response = RestAssured.given()
        .contentType(ContentType.JSON)
        .body("    {\n" +
            "      \"id\": 5,\n" +
            "      \"name\": \"PUT method test\",\n" +
            "      \"description\": \"element created with PUT method\",\n" +
            "      \"price\": \"$35.99\",\n" +
            "      \"createdAt\": \"2024-06-02T12:30:00Z\"\n" +
            "    }")
        .when()
        .put(url);

    response.prettyPrint();
    assertEquals(201, response.getStatusCode());
  }

  @Test
  @Order(5)
  public void getNewDataTest()
  {
    WireMock.givenThat(WireMock.get(WireMock.urlMatching("/getAdded"))
        .willReturn(WireMock.aResponse()
            .withHeader("Content-Type", "application/json")
            .withBodyFile("GetAddedDataSetResponse.json")
            .withStatus(200)
    ));

    String url = getBaseUrl() + "/getAdded";
    System.out.println("Testing GET request to: " + url);

    response = RestAssured.given()
        .when()
        .get(url);

    response.prettyPrint();
    assertEquals(200, response.getStatusCode());
    assertTrue(response.getBody().prettyPrint().contains("element created with PUT method"));
  }

  @Test
  @Order(6)
  public void deleteUpdatedDataTest()
  {
    WireMock.givenThat(WireMock.delete(WireMock.urlMatching("/delete"))
        .willReturn(WireMock.aResponse()
            .withHeader("Content-Type", "application/json")
            .withBodyFile("DeleteFromDataSetResponse.json")
            .withStatus(200))
    );

    String url = getBaseUrl() + "/delete";
    System.out.println("Testing DELETE request to: " + url);

    response = RestAssured
        .given()
        .when()
        .contentType(ContentType.JSON)
        .body("    {\n" +
            "      \"id\": 4,\n" +
            "    }")
        .delete(url);

    response.prettyPrint();
    assertEquals(200, response.getStatusCode());
  }

  @Test
  @Order(7)
  public void getDeletedDataTest()
  {
    WireMock.givenThat(WireMock.get(WireMock.urlMatching("/getDeleted"))
        .willReturn(WireMock.aResponse()
            .withHeader("Content-Type", "application/json")
            .withBodyFile("GetDeletedFromDataSetResponse.json")
            .withStatus(200))
    );

    String url = getBaseUrl() + "/getDeleted";
    System.out.println("Testing GET request to: " + url);

    response = RestAssured
        .given()
        .when()
        .get(url);

    response.prettyPrint();
    assertEquals(200, response.getStatusCode());
    assertFalse(response.getBody().prettyPrint().contains("second element"));
  }
}
