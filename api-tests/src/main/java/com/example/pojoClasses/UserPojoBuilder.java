package com.example.pojoClasses;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPojoBuilder {

  private int id;
  private String name;
  private String username;
  private String email;

  private UserPojoBuilder() {
  }

  public static Builder builder() {
    return new Builder();
  }

  // getters for the fields
  public int getId()
  {
    return id;
  }

  public String getName()
  {
    return name;
  }

  public String getUsername()
  {
    return username;
  }

  public String getEmail()
  {
    return email;
  }

  /*
   * BUILDER PATTERN
   *
   * Pozwala tworzyć obiekt krok po kroku.
   *
   * Zamiast:
   * new User(...)
   *
   * mamy:
   * User.builder()
   *      .name("John")
   *      .email("john@test.com")
   *      .build();
   */
  public static class Builder
  {

    private final UserPojoBuilder user =
        new UserPojoBuilder();

    public Builder id(int id)
    {
      user.id = id;
      return this;
    }

    public Builder name(String name)
    {
      user.name = name;
      return this;
    }

    public Builder username(String username)
    {
      user.username = username;
      return this;
    }

    public Builder email(String email)
    {
      user.email = email;
      return this;
    }

    public UserPojoBuilder build()
    {
      return user;
    }
  }
}