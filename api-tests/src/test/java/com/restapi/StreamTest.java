package com.restapi;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;

public class StreamTest
{

  @Test
  void streamTest()
  {
     // Mapa userów zawierająca imię i wiek
    List<User> users = List.of(new User("John", 30), new User("Anna", 25), new User("Michael", 35));
    Map<String, Integer> userAgeMap = users.stream().collect(Collectors.toMap(User::getName, User::getAge));
    System.out.println("Mapa użytkowników: " + userAgeMap);

    User olderThan30 = users.stream()
        .filter(user -> user.getAge() > 30)
        .findFirst()
        .orElse(null);
    System.out.println("Użytkownik starszy niż 30 lat: " + olderThan30.getName()); // Wynik: Michael
    if(olderThan30 == null) {
      System.out.println("Nie znaleziono użytkownika starszego niż 30 lat");
    }

    // Optional is better than null because it forces us to handle the case when there is no value, instead of risking a NullPointerException. It provides methods like isPresent(), orElse(), orElseThrow() to deal with the absence of a value in a more controlled way.
    Optional<User> user = users.stream()
        .filter(u -> u.getAge() > 18)
        .findFirst();
    System.out.println("Użytkownik starszy niż 18 lat: " + user.orElseThrow().getName()); // Wynik: John


    List<Integer> numbers = List.of(1, 2, 3, 4, 5);
    // 1. Znajdź sumę (suma wszystkich elementów)
    int sum = numbers
        .stream()
        .mapToInt(Integer::intValue)
        .sum();
    System.out.println("Suma: " + sum); // Wynik: 15

    // 2. Znajdź liczby parzyste
    List<Integer> evenNumbers = numbers
        .stream()
        .filter(n -> n % 2 == 0)
        .toList();  // Dawniej: .collect(Collectors.toList());
    System.out.println("Liczby parzyste: " + evenNumbers); // Wynik: [2, 4]

    // liczby nieparzyste
    List<Integer> oddNumbers = numbers
        .stream()
        .filter(n -> n % 2 != 0)
        .toList();
    System.out.println("Liczby nieparzyste: " + oddNumbers); // Wynik: [1, 3, 5]

    List<Integer> numberTwo = numbers
        .stream()
        .filter(n -> n == 2)
        .toList();
    System.out.println("Liczba 2: " + numberTwo); // Wynik

    Integer firstNumberTwo = numbers
        .stream()
        .filter(n -> n == 2)
        .findFirst()
        .orElse(null); // lub .orElseThrow() jeśli chcemy wyjątek
    System.out.println("Pierwsza liczba 2: " + firstNumberTwo); // Wynik: 2

    Integer firstNumberGreaterThanThree = numbers
        .stream()
        .filter(n -> n > 3)
        .findFirst()
        .orElse(null);
    System.out.println("Pierwsza liczba większa niż 3: " + firstNumberGreaterThanThree); // Wynik: 4

    // 3. Sprawdź czy lista zawiera 3
    boolean containsThree = numbers.contains(3);
    System.out.println("Lista zawiera 3: " + containsThree); // Wynik: true

    // 4. Mapowanie do double i suma
    double sumAsDouble = numbers.stream()
        .mapToDouble(Integer::doubleValue)
        .sum();
    System.out.println("Suma jako double: " + sumAsDouble); // Wynik: 15.0

    // 5. Lista double'ów
    List<Double> doubleList = numbers.stream()
        .map(Integer::doubleValue)
        .toList();
    System.out.println("Lista double'ów: " + doubleList);

    // 6. Znajdź największą liczbę
    int max = numbers.stream()
        .mapToInt(Integer::intValue)
        .max()
        .orElseThrow(); // lub .orElse(0) jeśli chcemy domyślną wartość
    System.out.println("Największa liczba: " + max); // Wynik: 5

    // znajdź sumę liczb parzystych
    int sumEven = numbers.stream()
        .filter(n -> n % 2 == 0)
        .mapToInt(Integer::intValue)
        .sum();
    System.out.println("Suma liczb parzystych: " + sumEven); // Wynik: 6

    // sprawdź, czy wszystkie są większe od 0
    boolean allGreaterThanZero = numbers.stream()
        .allMatch(n -> n > 0);
    System.out.println("Wszystkie liczby są większe od 0: " + allGreaterThanZero); // Wynik: true

        List<String> names = List.of("john", "anna", "john", "michael");
    // zamień wszystkie imiona na wielkie litery
    List<String> upperCaseNames = names.stream()
        .map(String::toUpperCase)
        .toList();
    System.out.println("Imiona wielkimi literami: " + upperCaseNames); // Wynik: [JOHN, ANNA]

    // zamień pierwsze litery na wielkie
    List<String> capitalizedNames = names.stream()
        .map(name -> name.substring(0, 1).toUpperCase() + name.substring(1))
        .toList();
    System.out.println("Imiona z wielką pierwszą literą: " + capitalizedNames); // Wynik: [John, Anna]

    // znajdź duplikaty
    List<String> duplicates = names.stream()
        .filter(name -> names.stream().filter(n -> n.equals(name)).count() > 1)
        .distinct()
        .toList();
    System.out.println("Duplikaty: " + duplicates); // Wynik: [john]

    // znajdź unikalne imiona
    List<String> uniqueNames = names.stream()
        .filter(name -> names.stream().filter(n -> n.equals(name)).count() == 1)
        .toList();
    System.out.println("Unikalne imiona: " + uniqueNames); // Wynik: [anna, michael]
  }

  private class User
  {
    private String name;
    private int age;

    public User(String name, int age) {
      this.name = name;
      this.age = age;
    }

    public String getName() {
      return name;
    }

    public int getAge() {
      return age;
    }
  }
}
