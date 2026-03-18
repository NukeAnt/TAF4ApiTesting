package com.example;

import com.example.pojoClasses.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.jayway.jsonpath.JsonPath;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserApiTest {

  // set up base URI for all tests
  @BeforeEach
  void setup() {
    RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
  }

  // basic test to verify that we can get a user by ID
  @Test
  void shouldGetUser() {
    given()
        .when()
        .get("/users/1")
        .then()
        .statusCode(200)
        .body("id", equalTo(1))
        .body("name", notNullValue());
  }

  // test to extract values using Jayway JsonPath
  @Test
  void shouldGetUser_usingJaywayJsonPath() {

    String response =
        given()
            .when()
            .get("/users/1")
            .then()
            .statusCode(200)
            .extract()
            .asString();

    System.out.println("Got response: " + response);

    int id = JsonPath.parse(response).read("$.id");
    String name = JsonPath.parse(response).read("$.name");

    System.out.println("id: " + id + ", name: " + name);

    assertEquals(1, id);
    assertTrue(name != null && !name.isEmpty());
  }

  // test to extract values using Jackson ObjectMapper
  @Test
  void shouldGetUser_usingJackson() throws Exception {

    String response =
        given()
            .when()
            .get("/users/1")
            .then()
            .statusCode(200)
            .extract()
            .asString();

    ObjectMapper mapper = new ObjectMapper();
    JsonNode json = mapper.readTree(response);
    System.out.println("Got JSON: " + json);

    int id = json.get("id").asInt();
    String name = json.get("name").asText();

    assertEquals(1, id);
    assertTrue(name != null && !name.isEmpty());
  }

  @Test
  void shouldGetUser_usingPOJO() {

    User user = given()
        .when()
        .get("/users/1")
        .then()
        .statusCode(200)
        .extract()
        .as(User.class);

    System.out.println("Got user: id=" + user.getId() + ", name=" + user.getName());

    assertEquals(1, user.getId());
    assertTrue(user.getName() != null && !user.getName().isEmpty());
  }


  @Test
  void shouldGetAllUsers_usingJsonPath() throws Exception {

    Response response =
        given()
            .when()
            .get("/users")
            .then()
            .statusCode(200)
            .extract().response();

    response.prettyPrint();

    int id = JsonPath.parse(response.asString()).read("$.[8].id"); // get the id of the 9th user (index 8)
    String name = JsonPath.parse(response.asString()).read("$.[8].name"); // get the name of the 9th user

    System.out.println("id: " + id + ", name: " + name);

    assertEquals(9, id);
    assertTrue(name != null && !name.isEmpty());
    assertEquals("Glenna Reichert", name);
  }

  // basic test to verify 404 - not found for non-existent user
  @Test
  void shouldReturnNotFoundForNonExistentUser() {
    String response = given()
        .when()
        .get("/users/9999")
        .then()
        .statusCode(404)
        .extract()
        .asString();

    System.out.println("Got response for non-existent user: " + response);
    assertTrue(response.isEmpty() || response.equals("{}"), "Expected empty response for non-existent user");
  }
}