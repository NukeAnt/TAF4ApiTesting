package com.restapi.builderPattern;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class exampleBuilderTest
{
  @Test
  void shouldCreateUser() {

    UserRequestBuilderTest request =
        UserRequestBuilderTest.builder()
            .firstName("John")
            .lastName("Smith")
            .email("john@test.com")
            .age(35)
            .active(true)
            .build();

    given()
        .contentType(ContentType.JSON)
        .body(request)
        .when()
        .post("/users")
        .then()
        .statusCode(201);
  }
}
