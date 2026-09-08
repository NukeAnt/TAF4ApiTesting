package com.restapi.patternTests;

import com.example.ConfigManager;
import com.example.factoryClasses.UserFactory;
import com.example.pojoClasses.UserPojoBuilder;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.Arrays;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

public class patternTest
{
  @Test
  void shouldCreateUserUsingPatterns() {

    RestAssured.baseURI =
        ConfigManager
            .getInstance()
            .getBaseUrl();

    UserPojoBuilder request =
        UserFactory.validUser();

    Response response = given()
        .contentType(ContentType.JSON)
        .body(request)
        .when()
        .post("/users")
        .then()
        .statusCode(201)
        .body("name",
            equalTo(request.getName()))
        .body("email",
            equalTo(request.getEmail()))
        .extract()
        .response();

    response.prettyPrint();

        System.out.println("=== CREATED USER ===");
    System.out.println("ID: " + response.jsonPath().getInt("id"));
    System.out.println("Name: " + response.jsonPath().getString("name"));
    System.out.println("Email: " + response.jsonPath().getString("email"));

    // TestNG asserts
    assertEquals(
        request.getName(),
        response.jsonPath().getString("name")
    );

    assertEquals(
        request.getEmail(),
        response.jsonPath().getString("email")
    );
  }

  @Test
  void shouldDeserializeUser() {

    UserPojoBuilder user =
        given()
            .when()
            .get("/users/1")
            .then()
            .statusCode(200)
            .extract()
            .as(UserPojoBuilder.class);

    assertEquals("Leanne Graham", user.getName());

    // Extra logs for fun and debugging
    System.out.println("=== USER DETAILS ===");
    System.out.println("Name: " + user.getName());
    System.out.println("Username: " + user.getUsername());
    System.out.println("Email: " + user.getEmail());

    assertTrue(
        user.getEmail().contains("@")
    );
  }

  @Test
  void shouldDeserializeUsersArray() {

    UserPojoBuilder[] users =
        given()
            .when()
            .get("/users")
            .then()
            .statusCode(200)
            .extract()
            .as(UserPojoBuilder[].class);

    assertTrue(users.length > 0);

    System.out.println(
        users[0].getName()
    );


    System.out.println("=== USERS ===");
    for (UserPojoBuilder user : users) {
      System.out.println(
          user.getId() + " | " + user.getName() + " | " + user.getEmail()
      );
    }

    System.out.println("=== USERS STREAM ===");
    Arrays.stream(users)
        .forEach(user ->
            System.out.println(
                user.getId() + " | " + user.getName() + " | " + user.getEmail()
            )
        );

    assertEquals(10, users.length);

    long usersWithEmail =
        Arrays.stream(users).filter(user -> user.getEmail() != null).count();
    assertEquals(users.length, usersWithEmail);
  }

  @Test
  void shouldValidateSchema() {

    Response response = given()
        .when()
        .get("/users/1")
        .then()
        .statusCode(200)
        .body(matchesJsonSchemaInClasspath(
                "schemas/user-schema.json"
            )
        ).extract().response();

    response.prettyPrint();
    System.out.println("Schema validation PASSED");
  }

  @Test
  void shouldFindUserByEmail() {

    UserPojoBuilder[] users =
        given()
            .when()
            .get("/users")
            .then()
            .statusCode(200)
            .extract()
            .as(UserPojoBuilder[].class);

    UserPojoBuilder foundUser =
        Arrays.stream(users)
            .filter(user ->
                user.getEmail().equals("Rey.Padberg@karina.biz"))
            .findFirst()
            .orElseThrow(() -> new AssertionError("User with expected email not found"));

    System.out.println("Expected email found for user: " + foundUser.getName());
  }

  @Test
  void shouldFindUserByUserNameWithoutStream()
  {
    UserPojoBuilder[] users =
        given()
            .when()
            .get("/users")
            .then()
            .statusCode(200)
            .extract()
            .as(UserPojoBuilder[].class);
    UserPojoBuilder foundUser = null;

    for (UserPojoBuilder user : users) {

      if ("Clementina DuBuque".equals(user.getName())) {
        foundUser = user;
        break;
      }
    }

    assertTrue(foundUser != null);

    System.out.println(
        "Found user: " + foundUser.getName() + ", email: " + foundUser.getEmail() + " and username: " + foundUser.getUsername()
    );
  }
}
