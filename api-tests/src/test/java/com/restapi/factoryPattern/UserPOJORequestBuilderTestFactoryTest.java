package com.restapi.factoryPattern;

import com.restapi.builderPattern.UserRequestBuilderTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class UserPOJORequestBuilderTestFactoryTest
{
  @Test
  void shouldRejectInvalidEmail() {

    UserRequestBuilderTest request =
        UserFactory.invalidEmailUser();

    given()
        .body(request)
        .when()
        .post("/users")
        .then()
        .statusCode(400);
  }
}
