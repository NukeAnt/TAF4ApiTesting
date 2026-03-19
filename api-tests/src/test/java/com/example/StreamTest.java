package com.example;

import org.junit.jupiter.api.Test;
import java.util.List;

public class StreamTest
{

  @Test
  void streamTest()
  {
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
  }
}
