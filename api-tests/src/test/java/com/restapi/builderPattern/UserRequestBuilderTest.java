package com.restapi.builderPattern;

public class UserRequestBuilderTest
{

  private String firstName;
  private String lastName;
  private String email;
  private int age;
  private boolean active;

  private UserRequestBuilderTest() {
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmail() {
    return email;
  }

  public int getAge() {
    return age;
  }

  public boolean isActive() {
    return active;
  }

  /*
   * BUILDER PATTERN
   *
   * Idea:
   * Tworzymy złożony obiekt krok po kroku.
   *
   * Zamiast:
   * new UserRequest("John","Smith","mail",35,true)
   *
   * mamy:
   * UserRequest.builder()
   *      .firstName("John")
   *      .lastName("Smith")
   *      .build();
   *
   * Dzięki temu kod jest czytelniejszy
   * i łatwiej utrzymać duże requesty API.
   */
  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private final UserRequestBuilderTest request =
        new UserRequestBuilderTest();

    public Builder firstName(String firstName) {
      request.firstName = firstName;
      return this;
    }

    public Builder lastName(String lastName) {
      request.lastName = lastName;
      return this;
    }

    public Builder email(String email) {
      request.email = email;
      return this;
    }

    public Builder age(int age) {
      request.age = age;
      return this;
    }

    public Builder active(boolean active) {
      request.active = active;
      return this;
    }

    public UserRequestBuilderTest build() {
      return request;
    }
  }
}