package com.example;

import com.example.pojoClasses.User;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.jayway.jsonpath.JsonPath;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
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
          .body(not(emptyString()))
          .body("id", equalTo(1))
          .body("name", notNullValue())
          .body("name", equalTo("Leanne Graham"));
  }

  @Test
  void shouldGetUser_andVerifyFields() {
    Response response = RestAssured
        .given()
        .when()
        .get("/users/1")
        .then()
          .statusCode(200)
          .body("name", equalTo("Leanne Graham"))
        .extract().response();

    response.prettyPrint();

    assertTrue(response.getBody().asString().contains("id"), "Response should contain 'id' field");
    assertTrue(response.getBody().asString().contains("name"), "Response should contain 'name' field");
  }

  // POST test to create a new user (note: this API doesn't actually create a user, it just simulates the response)
  @Test
  void shouldCreateUser()
  {
    String requestBody = "{\n" +
        "  \"name\": \"John Doe\",\n" +
        "  \"username\": \"johndoe\",\n" +
        "  \"email\": \"no@mail.com\"\n" +
        "}";

    Response response =
        given()
          .contentType("application/json")
          .body(requestBody)
        .when()
          .post("/users")
        .then()
          .statusCode(201)
          .body("$", not(emptyString()))
          .body("id", notNullValue())
          .body("name", equalTo("John Doe"))
          .body("username", equalTo("johndoe"))
          .body("email", equalTo("no@mail.com"))
        .extract().response();

    response.prettyPrint();
  }

  // test to update existing user using PUT
  @Test
  void shouldUpdateUser()
  {
    String requestBody = "{\n" +
        "  \"id\": 1,\n" +
        "  \"name\": \"John Doe Updated\",\n" +
        "  \"username\": \"johndoeupdated\",\n" +
        "  \"email\": \"no@mail.com\"\n" +
        "}";

    Response response =
        given()
          .contentType("application/json")
          .body(requestBody)
        .when()
          .put("/users/1")
        .then()
          .statusCode(200)
          .body("id", equalTo(1))
          .body("name", equalTo("John Doe Updated"))
          .body("username", equalTo("johndoeupdated"))
          .body("email", equalTo("no@mail.com"))
        .extract().response();

    response.prettyPrint();
  }

  // test to patch existing user using PATCH
  @Test
  void shouldPatchUser()
  {
    String requestBody = """
        {
          "name": "John Doe Patched"
        }""";

    Response response =
        given()
          .contentType("application/json")
          .body(requestBody)
        .when()
          .patch("/users/2")
        .then()
          .statusCode(200)
          .body("id", equalTo(2))
          .body("name", equalTo("John Doe Patched"))
        .extract().response();

    response.prettyPrint();
  }

  // test to delete a user
  @Test
  void shouldDeleteUser()
  {
    Response response = given()
        .when()
        .delete("/users/3")
        .then()
        .statusCode(200)
        .extract().response();

    response.prettyPrint();
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

    log.info("Got response: " + response);

    int id = JsonPath.parse(response).read("$.id");
    String name = JsonPath.parse(response).read("$.name");

    log.info("id: " + id + ", name: " + name);

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
    log.info("Got JSON: " + json);

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

    log.info("Got user: id=" + user.getId() + ", name=" + user.getName());

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

    log.info("id: " + id + ", name: " + name);

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

    log.info("Got response for non-existent user: " + response);
    assertTrue(response.isEmpty() || response.equals("{}"), "Expected empty response for non-existent user");
  }

  @Test
  void shouldGetPostsForUserId()
  {
    RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

    Response response = given()
        .when()
        .get("/posts?userId=1")// query parameter to filter posts by userId /post?userId1&&id=2
        .then()
          .statusCode(200)
          .body("$", not(empty())) // is body not empty
          .body("userId", everyItem(equalTo(1))) // verify that all posts have userId 1
        .extract().response();

    response.prettyPrint();

    List<Integer> userIds = JsonPath.parse(response.asString()).read("$.[*].userId");
    assertTrue(userIds.stream().allMatch(id -> id ==1), "All posts should have userId 1");

    // Znajdź post o id = 5 i sprawdź, że jego title nie jest pusty
    String postTitle = JsonPath.parse(response.asString()).read("$.[?(@.id == 5)].title").toString();
    log.info("Title of post with id 5: " + postTitle);
    assertTrue(postTitle != null && !postTitle.isEmpty(), "Title of post with id 5 should not be null or empty");

    // $.[?(@.id == 5)]  -- From root → take only direct children where id == 5
    // .title -- take value from field title of the found post

  }
}