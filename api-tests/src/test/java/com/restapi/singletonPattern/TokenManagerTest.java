package com.restapi.singletonPattern;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class TokenManagerTest
{

    @Test
    void shouldGetUsersWithValidToken()
    {
      given()
          .header(
              "Authorization",
              "Bearer "
                  + TokenManager
                  .getInstance()
                  .getToken()
          )
          .when()
          .get("/users")
          .then()
          .statusCode(200);
    }
}
