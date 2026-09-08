package com.example.interviewExamples;

import java.util.*;

public class InterviewCheatSheet {
  public static void main(String[] args) {

    /*
     * ====================================================
     * OPTIONAL
     * ====================================================
     * Optional.ofNullable(testData.get("firstName"))
        .ifPresent(firstName -> page.firstNameInput().setValue(testData.get("firstName")));
     */

    Optional<String> user = Optional.of("John");

    user.ifPresent(System.out::println);

    String nullableValue = null;

    Optional<String> safe =
        Optional.ofNullable(nullableValue);

    System.out.println(
        safe.orElse("Default User")
    );
/*

============================================================
OPTIONAL + STREAM PUŁAPKI REKRUTACYJNE
============================================================
A)
Optional<String> result =
        Stream.of("John", "Kate", "Bob")
              .filter(name -> name.startsWith("K"))
              .map(String::toUpperCase)
              .findFirst();

System.out.println(result);

WYNIK: Optional[KATE]
------------------------------------------------------------
B)
Optional<String> result =
        Stream.of("John", "Kate", "Bob")
              .filter(name -> name.startsWith("K"))
              .map(String::toUpperCase)
              .findFirst();

System.out.println(result.orElse(null));

WYNIK: KATE
------------------------------------------------------------

UWAGA
result to Optional

result.orElse(...)
to wartość znajdująca się wewnątrz Optionala lub wartość domyślna

============================================================
PUŁAPKA Z .get()
============================================================
String result =
        Stream.of("John", "Kate", "Bob")
              .filter(name -> name.startsWith("X"))
              .findFirst()
              .get();

WYNIK: NoSuchElementException
------------------------------------------------------------

Dlaczego?
findFirst() zwraca:
Optional.empty()
a .get() próbuje pobrać wartość, której nie ma.

============================================================
PUŁAPKA Z NULLEM W STREAMIE
============================================================
List<String> names =
        Arrays.asList("John", null, "Bob");

names.stream()
     .map(String::toUpperCase)
     .forEach(System.out::println);

WYNIK: NullPointerException

------------------------------------------------------------
Dlaczego?
Java próbuje wykonać:
null.toUpperCase()

============================================================
PUŁAPKA findFirst() + null
============================================================
Optional<String> result =
        Stream.of(null, "John", "Bob")
              .findFirst();

WYNIK: NullPointerException

------------------------------------------------------------
To jest bardzo podchwytliwe.

Wiele osób spodziewa się: Optional.empty()

ale Optional nie pozwala przechowywać null.
findFirst() wewnętrznie próbuje utworzyć:

Optional.of(null)
co kończy się: NullPointerException

============================================================
POPRAWNE PODEJŚCIE
============================================================
Optional<String> result =
        Stream.of(null, "John", "Bob")
              .filter(Objects::nonNull)
              albo .flatMap(Stream::ofNullable)
              .findFirst();

WYNIK:Optional[John]
------------------------------------------------------------
Zasada:

Jeżeli stream może zawierać nulle,
najpierw użyj:
.filter(Objects::nonNull)

a dopiero potem:
.map(...)
.findFirst()
.collect(...)
*/


    /*
     * ====================================================
     * STRING == VS EQUALS
     * ====================================================
     */

    String a = "John";
    String b = "John";

    System.out.println(a == b);       // true
    System.out.println(a.equals(b));  // true

    String c = new String("John");
    String d = new String("John");

    System.out.println(c == d);       // false
    System.out.println(c.equals(d));  // true


    /*
     * ====================================================
     * INTEGER CACHE
     * ====================================================
     */

    Integer x1 = 127;
    Integer x2 = 127;

    System.out.println(x1 == x2);     // true

    Integer y1 = 128;
    Integer y2 = 128;

    System.out.println(y1 == y2);     // false


    /*
     * ====================================================
     * AUTOBOXING / UNBOXING
     * ====================================================
     */

    Integer boxed = 10;       // autoboxing

    int primitive = boxed;    // unboxing

    System.out.println(primitive);

    Integer nullableInteger = null;

    // int value = nullableInteger;
    // NullPointerException


    /*
     * ====================================================
     * LIST VS SET
     * ====================================================
     */

    List<String> users = new ArrayList<>();

    users.add("John");
    users.add("John");

    System.out.println(users.size()); // 2

    Set<String> uniqueUsers =
        new HashSet<>();

    uniqueUsers.add("John");
    uniqueUsers.add("John");

    System.out.println(
        uniqueUsers.size()
    ); // 1


    /*
     * ====================================================
     * FINAL LIST
     * ====================================================
     */

    final List<String> names =
        new ArrayList<>();

    names.add("Kate");
    names.add("John");

    System.out.println(names);

    // names = new ArrayList<>();
    // Compile Error


    /*
     * ====================================================
     * IMMUTABLE STRING
     * ====================================================
     */

    String text = "Hello";

    text.concat(" World");

    System.out.println(text);
    // Hello


    /*
     * ====================================================
     * MUTABLE STRINGBUILDER
     * ====================================================
     */

    StringBuilder sb =
        new StringBuilder();

    sb.append("Hello");
    sb.append(" World");

    System.out.println(sb);
    // Hello World
  }


  /*
   * ====================================================
   * FINAL PARAMETER
   * ====================================================
   *
   * final blokuje ponowne przypisanie parametru
   */

  public void process(final String value) {

    // value = "new";
    // Compile Error

    System.out.println(value);
  }


  /*
   * ====================================================
   * OPTIONAL + OR ELSE THROW
   * ====================================================
   */

  public String findUser() {

    Optional<String> user =
        Optional.empty();

    return user.orElseThrow(
        () -> new RuntimeException(
            "User not found"
        )
    );
  }

  /*
   * ====================================================
   * OPTIONAL - orElse() vs orElseGet()
   * ====================================================
   *
   * Obie metody zwracają wartość domyślną,
   * gdy Optional jest pusty.
   *
   * Różnica:
   *
   * orElse(...)
   * - argument jest wykonywany ZAWSZE
   *
   * orElseGet(...)
   * - lambda wykona się TYLKO gdy Optional jest pusty
   */
  public static void optionalOrElseVsOrElseGet() {

    Optional<String> existingUser =
        Optional.of("Kate");

    System.out.println("=== orElse ===");

    String result1 =
        existingUser.orElse(
            generateDefaultUser()
        );

    System.out.println("Result: " + result1);

    System.out.println("\n=== orElseGet ===");
    String result2 =
        existingUser.orElseGet(
            () -> generateDefaultUser()
        );

    System.out.println("Result: " + result2);
  }

  /*
   * Wynik:
   *
   * === orElse ===
   * Generating default user...
   * Result: Kate
   *
   * === orElseGet ===
   * Result: Kate
   *
   * Widać, że generateDefaultUser()
   * wykonało się dla orElse(),
   * mimo że Optional zawierał wartość.
   */

  private static String generateDefaultUser() {

    System.out.println(
        "Generating default user..."
    );

    return "John";
  }

  /*
   * ====================================================
   * Optional.of() vs Optional.ofNullable()
   * ====================================================
   *
   * Optional.of()
   * - nie pozwala na null
   * - rzuca NullPointerException
   *
   * Optional.ofNullable()
   * - pozwala na null
   * - zwraca Optional.empty()
   */

  public static void optionalCreationExamples() {

    String value = null;
    // NullPointerException
    // Optional<String> a =
    //         Optional.of(value);


    Optional<String> b =
        Optional.ofNullable(value);

    System.out.println(b);

    System.out.println(
        b.orElse("Default User")
    );
  }
  /*
   * Wynik:
   *
   * Optional.empty
   * Default User
   */

  /*
   * ====================================================
   * Optional.get() - pułapka
   * ====================================================
   *
   * get() działa tylko wtedy,
   * gdy Optional zawiera wartość.
   */

  public static void optionalGetExample() {

    Optional<String> user = Optional.empty();

    // NoSuchElementException
    // System.out.println(
    //         user.get()
    // );

    System.out.println(user.orElse("User not found")
    );
  }

  /*
   * ====================================================
   * Stream + Optional + findFirst()
   * ====================================================
   */

  public static void streamOptionalExample() {

    List<String> names =
        List.of(
            "John",
            "Kate",
            "Mike"
        );

    Optional<String> result =
        names.stream()
            .filter(name ->
                name.startsWith("K"))
            .findFirst();

    result.ifPresent(System.out::println);

    String value = result.orElse("NOT FOUND");
    System.out.println(value);
  }

  /*
   * Wynik:
   *
   * Kate
   * Kate
   */



}
