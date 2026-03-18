package com.example;

import com.example.wireMockServer.WireMockBaseTest;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.extern.slf4j.Slf4j;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import com.jayway.jsonpath.JsonPath;
import java.util.LinkedHashMap;
import java.util.List;
import static org.testng.AssertJUnit.assertEquals;

@Slf4j
public class SoftAssertionJaywayJsonPath extends WireMockBaseTest
{
  @Test
  void shouldGetAllUsers()
  {
    RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    Response response =
        RestAssured.given()
            .when()
            .get("/users")
            .then()
            .statusCode(200)
            .extract()
            .response();

    // response.prettyPrint();

    int firstId = JsonPath.parse(response.asString()).read("$.[0].id"); // first entry
    String firstName = JsonPath.parse(response.asString()).read("$.[0].name");
    log.info("First entry id: " + firstId + ", name: " + firstName);

    List<String> names = JsonPath.parse(response.asString()).read("$.[*].name");
    log.info("All names: " + names);

    List<String> listOfUsers = JsonPath.parse(response.asString()).read("$.[*]");
    log.info("List of all users: " + listOfUsers);

    List<String> fourthUserNameById = JsonPath.parse(response.asString()).read("$.[?(@.id == 4)].name");
    log.info("Name of user with id 4: " + fourthUserNameById);

    // Using soft assertions
    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(firstId > 0, "First user ID should be greater than 0");
    softAssert.assertTrue(firstName != null && !firstName.isEmpty(), "First user name should not be null or empty");

    softAssert.assertTrue(!names.isEmpty(), "There should be at least one user name in the list");
    softAssert.assertTrue(names.stream().anyMatch(name -> name.contains("Chelsey Dietrich")), "There should be a user named Chelsey Dietrich");

    softAssert.assertTrue(JsonPath.parse(listOfUsers).jsonString().contains("Glenna Reichert"), "List of users should contain Leanne Graham");
    softAssert.assertTrue(JsonPath.parse(fourthUserNameById).jsonString().contains("Patricia Lebsack"), "User with id 4 should be Patricia Lebsack");

    softAssert.assertAll();

    log.info("All soft assertions complete!");

  }

  @BeforeTest
  public void setupSoftAssertJsonNestedArraysTest()
  {
    startWireMockServer();
  }

  @Test
  public void softAssertJsonNestedArraysTest()
  {
    WireMock.givenThat(WireMock.get(WireMock.urlMatching("/getNestedArrays"))
        .willReturn(WireMock.aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBodyFile("NestedArraysResponse.json")
        ));

    String url = getBaseUrl() + "/getNestedArrays";
    log.info("Testing GET request to: " + url);

    Response response = RestAssured
        .given()
        .when()
        .get(url);

    response.prettyPrint();
    assertEquals(200, response.getStatusCode());

    // Address holds a JSON array, we can get specific indexed elements using index
    List<String> addressType1 = JsonPath.parse(response.asString()).read("$.address[0]..type"); // gets all found
    log.info("Address type of first address from first array: " + addressType1);

    String addressType2 = JsonPath.parse(response.asString()).read("$.address[1].[2].type"); // gets specific indexed element
    log.info("Address type for third element from second array: " + addressType2);

    // Getting whole content of second address array
    List<Object> secondAddressArray = JsonPath.parse(response.asString()).read("$.address[1]..*");
    log.info("Content of second address array: " + secondAddressArray);

    // All address types as a list, from both arrays
    List<String> addressTypes = JsonPath.parse(response.asString()).read("$.address..type");
    log.info("All address types: " + addressTypes);

    // Get all address objects from nested arrays using [*][*]
    List<Object> allAddressObjects = JsonPath.parse(response.asString()).read("$.address[*][*]");
    log.info("All address objects: " + allAddressObjects);

    SoftAssert softAssert = new SoftAssert();
    softAssert.assertTrue(addressType1.stream().anyMatch(type -> type.equals("home")), "There should be an address of type 'home'");
    softAssert.assertTrue(addressTypes.stream().anyMatch(type -> type.equals("office")), "There should be an address of type 'office'");
    softAssert.assertTrue(addressType2.equals("old"), "The address type of the third element from second array should be 'old'");

    softAssert.assertTrue(secondAddressArray.stream().anyMatch(item -> item.toString().contains("456 Elm")), "The second address array should contain an address with '456 Elm'");
    softAssert.assertTrue(allAddressObjects.stream()
        .map(addr -> ((LinkedHashMap<String, Object>) addr).get("streetAddress").toString())
        .anyMatch(street -> street.contains("123 Main")), "There should be an address containing '123 Main'");
    softAssert.assertAll();
  }

  @AfterTest
  public void tearDownSoftAssertJsonNestedArraysTest()
  {
    stopWireMockServer();
  }
}
