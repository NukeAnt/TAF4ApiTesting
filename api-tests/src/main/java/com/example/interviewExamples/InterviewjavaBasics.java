package com.example.interviewExamples;

public class InterviewjavaBasics
{
  /*
NON-STATIC INNER CLASS

class House {
    private String address = "Warsaw";

    class Room {

        void print() {
            System.out.println(address); // OK
        }
    }
}

House house = new House();
House.Room room = house.new Room();

Room zna konkretny House.
*/


/*
STATIC INNER CLASS
class House {

    private String address = "Warsaw";
    static String CITY = "Warsaw";

    static class Room {
        void print() {
            System.out.println(CITY); // OK

            // System.out.println(address);
            // BŁĄD KOMPILACJI
        }
    }
}

House.Room room = new House.Room();

Nie potrzebuje obiektu House.
Ma dostęp tylko do elementów static.
*/


/*
TO JEST NIELEGALNE
public static class House {
}
Top-level class nie może być static.
*/

  public static final String API_URL =
      "https://api.company.com";

    /*
    ============================================================
    ACCESS MODIFIERS (KTO MOŻE UŻYWAĆ?)
    ============================================================

    private
        -> tylko ta sama klasa

    brak modyfikatora (package-private)
        -> tylko klasy w tym samym package

    protected
        -> ten sam package
        -> oraz klasy dziedziczące

    public
        -> dostęp z całej aplikacji
    */


    /*
    ============================================================
    PRIVATE
    ============================================================

    Jak zabawka schowana w szafce na klucz.
    Tylko właściciel może jej używać.
    */
  static final String VERSION = "1.0";


    /*
    ============================================================
    PACKAGE-PRIVATE
    ============================================================

    Brak słowa private/protected/public.

    Dostępne dla klas w tym samym package.

    Jak pokój dostępny dla wszystkich domowników.
    */
  static final String PACKAGE_CONSTANT =
      "PACKAGE";


    /*
    ============================================================
    PROTECTED
    ============================================================

    Dostępne:
    - w tym samym package
    - w klasach dziedziczących

    Jak rodzinny sekret przekazywany dzieciom :)
    */
  static int counter = 0;


    /*
    ============================================================
    PUBLIC
    ============================================================

    Dostępne wszędzie.

    Jak ogłoszenie na rynku.
    */
  final int AGE = 40;


    /*
    ============================================================
    STATIC
    ============================================================

    STATIC = należy do KLASY

    Nie trzeba tworzyć obiektu.
    Jest jedna wspólna wartość.
    */
  public int publicField = 999;

    /*
    Użycie:

    ModifiersCheatSheet.counter++;

    Nie trzeba:

    ModifiersCheatSheet obj = new ModifiersCheatSheet();
    obj.counter++;
    */


    /*
    ============================================================
    NON-STATIC
    ============================================================

    Należy do konkretnego obiektu.
    Każdy obiekt ma własną kopię.
    */
  protected int protectedField = 789;

    /*
    ModifiersCheatSheet a = new ModifiersCheatSheet();
    ModifiersCheatSheet b = new ModifiersCheatSheet();

    a.name = "John";
    b.name = "Kate";

    Każdy obiekt ma własne pole name.
    */


    /*
    ============================================================
    FINAL VARIABLE
    ============================================================

    FINAL = nie można ponownie przypisać.

    Raz ustawione.
    Koniec.
    */
  int packagePrivateField = 456;

    /*
    AGE = 41;

    BŁĄD KOMPILACJI
    */


    /*
    ============================================================
    FINAL PARAMETER
    ============================================================

    Parametru nie można zmienić wewnątrz metody.
    */
  String name;


    /*
    ============================================================
    FINAL METHOD
    ============================================================

    Nie można nadpisać w klasie potomnej.
    */
  private int privateField = 123;

    /*
    static class Child extends Parent {

        @Override
        public void save() {
            // BŁĄD KOMPILACJI
        }
    }
    */


    /*
    ============================================================
    FINAL CLASS
    ============================================================

    Nie można po niej dziedziczyć.
    */

  public static void main(String[] args)
  {
    System.out.println("Java Modifiers Cheat Sheet");
  }

    /*
    class Child extends FinalClass {
    }
    BŁĄD KOMPILACJI
    */


    /*
    ============================================================
    STATIC FINAL
    ============================================================
    Najczęściej używane do stałych.

    STATIC
        -> jedna wspólna wartość
    FINAL
        -> nie można zmienić
    */

  public void printName(final String name)
  {
    // name = "Kate";
    // BŁĄD KOMPILACJI

    System.out.println(name);
  }

    /*
    VERSION = "2.0";

    BŁĄD KOMPILACJI
    */


    /*
    ============================================================
    PUBLIC STATIC FINAL
    ============================================================
    Stała dostępna wszędzie.
    */

  static class Parent
  {
    public final void save()
    {
      System.out.println("Saving...");
    }
  }

    /*
    ============================================================
    STATIC FINAL
    ============================================================

    Stała dostępna tylko w package.
    Jedyna różnica względem public static final
    to brak słowa public.
    */

  final static class FinalClass
  {
  }

    /*
    ============================================================
    PUBLIC FINAL CLASS
    ============================================================

    Dostępna wszędzie.
    Nie można dziedziczyć.
    */

  public final static class User
  {
  }

    /*
    ============================================================
    PRIVATE CLASS?
    ============================================================
    TOP LEVEL CLASS NIE MOŻE BYĆ PRIVATE.
    TO NIE DZIAŁA:
    ------------------------------------------------------------
    private class User {
    }

    BŁĄD KOMPILACJI

    Top-level class może być tylko:
    public class User

    albo
    class User
    */


    /*
    ============================================================
    PRIVATE FINAL INNER CLASS
    ============================================================
    To już działa.
    */

  static class Builder
  {
  }

    /*
    PRIVATE
        -> widoczna tylko w tej klasie
    FINAL
        -> nie można dziedziczyć
    */


    /*
    ============================================================
    STATIC INNER CLASS
    ============================================================
    Nie jest związana z konkretnym obiektem.
    Można ją stworzyć bez tworzenia obiektu klasy zewnętrznej.
    */

  private final class SecretHelper
  {
  }

    /*
    ModifiersCheatSheet.Builder builder =
            new ModifiersCheatSheet.Builder();
    OK
    */


    /*
    ============================================================
    CZY TOP LEVEL CLASS MOŻE BYĆ STATIC?
    ============================================================
    NIE.
    TO NIE DZIAŁA:

    public static class House {
    }

    BŁĄD KOMPILACJI
    static można stosować tylko do:
    - pól
    - metod
    - bloków static
    - klas zagnieżdżonych
    */


    /*
    ============================================================
    REKRUTACYJNE DEFINICJE
    ============================================================
    PRIVATE
        Dostęp tylko z tej samej klasy.
    PROTECTED
        Dostęp z package i klas dziedziczących.
    PUBLIC
        Dostęp z całej aplikacji.
    STATIC
        Element należy do klasy, a nie do instancji.
    FINAL VARIABLE
        Nie można ponownie przypisać wartości.
    FINAL METHOD
        Nie można nadpisać.
    FINAL CLASS
        Nie można dziedziczyć.
    STATIC FINAL
        Stała wspólna dla całej klasy.
    */

  /*
============================================================
ABSTRACT CLASS
============================================================
Klasy abstrakcyjnej nie można utworzyć przez new.
Służy jako baza dla innych klas.

Przykład:
------------------------------------------------------------

abstract class Animal {
    abstract void makeSound();
}

Animal animal = new Animal();

BŁĄD KOMPILACJI
------------------------------------------------------------
Musimy stworzyć klasę potomną:

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Woof");
    }
}

Animal dog = new Dog();

OK
*/


/*
============================================================
ABSTRACT METHOD
============================================================
Metoda bez implementacji.
Mówi:
"Każda klasa potomna ma to zaimplementować"

------------------------------------------------------------

abstract class Animal {
    abstract void makeSound();
}
------------------------------------------------------------
To NIE DZIAŁA:
abstract void makeSound() {
    System.out.println("Woof");
}

BŁĄD KOMPILACJI
Metoda abstract nie może mieć implementacji.
*/

/*
============================================================
ABSTRACT CLASS + NORMAL METHOD
============================================================
Klasa abstrakcyjna może mieć zarówno:

- metody abstract
- zwykłe metody
------------------------------------------------------------
abstract class Animal {

    abstract void makeSound();

    void sleep() {
        System.out.println("Sleeping");
    }
}

To jest poprawne.
*/

/*
============================================================
ABSTRACT + FINAL CLASS
============================================================
TO NIE DZIAŁA
------------------------------------------------------------
abstract final class Animal {
}
BŁĄD KOMPILACJI
Dlaczego?

abstract = trzeba dziedziczyć
final = nie wolno dziedziczyć

Java mówi:
"Zdecyduj się" :)
*/

/*
============================================================
ABSTRACT + FINAL METHOD
============================================================
TO NIE DZIAŁA
------------------------------------------------------------
final abstract void makeSound();
BŁĄD KOMPILACJI
Dlaczego?

abstract = nadpisz mnie
final = nie nadpisuj mnie

Sprzeczność.
*/

/*
============================================================
PUBLIC ABSTRACT CLASS
============================================================
Bardzo popularna kombinacja.
------------------------------------------------------------
public abstract class Animal {
}

Klasa widoczna wszędzie.
Nie można zrobić:

new Animal();
*/

/*
============================================================
PRIVATE ABSTRACT CLASS
============================================================
TOP LEVEL CLASS
TO NIE DZIAŁA
------------------------------------------------------------
private abstract class Animal {
}

BŁĄD KOMPILACJI
Top-level class nie może być private.
*/

/*
============================================================
PRIVATE ABSTRACT INNER CLASS
============================================================
TO DZIAŁA
------------------------------------------------------------
class Zoo {
    private abstract class Animal {
    }
}

private = tylko w Zoo

abstract = trzeba dziedziczyć
*/

/*
============================================================
STATIC ABSTRACT CLASS
============================================================
TOP LEVEL
TO NIE DZIAŁA
------------------------------------------------------------
static abstract class Animal {
}

BŁĄD KOMPILACJI
Top-level class nie może być static.
*/

/*
============================================================
STATIC ABSTRACT INNER CLASS
============================================================
TO DZIAŁA
------------------------------------------------------------

class Zoo {
    static abstract class Animal {
    }
}

static = nie jest związana z instancją Zoo

abstract = nie można utworzyć przez new
*/

/*
============================================================
SEALED CLASS
============================================================
Nowość w nowszych Javach.
Pozwala kontrolować kto może dziedziczyć.
------------------------------------------------------------
public sealed class Animal
    permits Dog, Cat {

}
Tylko Dog i Cat mogą dziedziczyć.
*/

/*
============================================================
NON-SEALED CLASS
============================================================
Pozwala ponownie otworzyć dziedziczenie.
------------------------------------------------------------
public non-sealed class Dog
        extends Animal {

}
Od tego miejsca można znowu dziedziczyć dowolnie.
*/

/*
============================================================
REKRUTACYJNE DEFINICJE
============================================================

ABSTRACT CLASS
Klasa bazowa, której nie można utworzyć
przez new. Może zawierać metody abstract
oraz zwykłe metody.

ABSTRACT METHOD
Metoda bez implementacji.
Klasa potomna musi ją zaimplementować.


FINAL CLASS
Nie można po niej dziedziczyć.

FINAL METHOD
Nie można jej nadpisać.

STATIC CLASS
Istnieje tylko jako klasa zagnieżdżona.
Nie jest związana z instancją klasy zewnętrznej.


SEALED CLASS
Pozwala ograniczyć listę klas,
które mogą dziedziczyć.
*/

  /*
============================================================
MODIFIERS IN TEST AUTOMATION FRAMEWORKS
============================================================
Teoria jest ważna.

Ale na rozmowie warto też umieć powiedzieć
gdzie używa się tych rzeczy w praktyce.

============================================================
PUBLIC
============================================================
Najczęściej:
- klasy testowe
- Page Objecty
- ApiClient
- Factory
- Helpery używane w wielu miejscach

Przykłady:
LoginPage
DashboardPage
UserApiClient
UserFactory

Dlaczego?
Bo muszą być dostępne z innych pakietów.

------------------------------------------------------------
Definicja rekrutacyjna:
public = dostępne z całej aplikacji.

============================================================
PRIVATE
============================================================
Najczęściej:
- pola klas
- pomocnicze metody

Przykład:
ApiClient przechowuje:

- token
- logger
- konfigurację

Nikt z zewnątrz nie powinien ich zmieniać.
------------------------------------------------------------
Przykłady metod:

buildPayload()
validateResponse()
saveUserId()

Są tylko częścią większego procesu.
Nie powinny być wywoływane bezpośrednio.

------------------------------------------------------------
Definicja rekrutacyjna:
private = dostęp tylko z tej samej klasy.

============================================================
PROTECTED
============================================================
Bardzo często używane w klasach bazowych.

Przykład:
BaseTest

udostępnia:
protected ApiClient apiClient;
protected Logger logger;
protected Config config;

Każda klasa dziedzicząca po BaseTest
ma do nich dostęp.

------------------------------------------------------------
Definicja rekrutacyjna:

protected = package + klasy dziedziczące.

============================================================
PACKAGE-PRIVATE
============================================================
Brak modyfikatora.

Najczęściej używane gdy:
"Chcę używać tego tylko wewnątrz frameworka"
Nie chcę wystawiać klasy publicznie.

------------------------------------------------------------
Definicja rekrutacyjna:
package-private = dostęp tylko z tego samego package.

============================================================
STATIC
============================================================
Jedna wspólna wartość dla całej klasy.

Najczęstsze zastosowania:
- stałe
- utility classes
- factory methods

------------------------------------------------------------
Przykłady:

BASE_URL
DEFAULT_TIMEOUT
ADMIN_USER

------------------------------------------------------------
DateUtils.formatDate(...)
JsonUtils.parse(...)
StringUtils.isBlank(...)

Nie trzeba tworzyć obiektu.
------------------------------------------------------------

UserFactory.createAdmin()
UserFactory.createCustomer()

Factory methods często są static.

------------------------------------------------------------
Definicja rekrutacyjna:
static = należy do klasy, nie do obiektu.

============================================================
FINAL
============================================================
Najczęściej:
- stałe
- immutable obiekty
- utility classes

------------------------------------------------------------
Przykład:
public static final String BASE_URL

------------------------------------------------------------
Modele odpowiedzi API:

UserResponse
PaymentResponse
AccountResponse

Po zmapowaniu JSON-a nie chcemy
przypadkowo zmieniać danych.
------------------------------------------------------------

Utility classes:
DateUtils
JsonUtils

Często:
final class DateUtils

bo nie ma sensu po nich dziedziczyć.
------------------------------------------------------------

Definicja rekrutacyjna:
final = blokuje zmianę.

zmienna -> nie można przypisać ponownie
metoda -> nie można nadpisać
klasa -> nie można dziedziczyć

============================================================
ABSTRACT
============================================================
Bardzo popularne w frameworkach.
------------------------------------------------------------
BaseTest
abstract class BaseTest

Dlaczego?
Bo nie jest gotowym testem.
Jest tylko bazą dla innych testów.

------------------------------------------------------------
BasePage
abstract class BasePage
Dziedziczą po niej:

LoginPage
DashboardPage
UserPage

------------------------------------------------------------
BaseApiClient
Wspólna logika dla klientów API.
------------------------------------------------------------
Definicja rekrutacyjna:
abstract class = nie można stworzyć przez new.
Służy jako baza dla innych klas.


============================================================
STATIC INNER CLASS
============================================================
Najczęściej:

Builder
Przykłady:
UserRequest.Builder
PaymentRequest.Builder
AccountRequest.Builder

------------------------------------------------------------
Builder można tworzyć bez tworzenia
obiektu klasy zewnętrznej.

Dlatego zwykle jest static.

============================================================
SEALED
============================================================
Rzadko spotykane w automatyzacji.

Pozwala ograniczyć listę klas,
które mogą dziedziczyć.

Bardziej spotykane w nowoczesnym
kodzie aplikacyjnym niż w frameworkach testowych.

============================================================
PYTANIA REKRUTACYJNE
============================================================
Dlaczego BaseTest jest abstract?

Ponieważ nie reprezentuje gotowego testu.
Zawiera wspólną konfigurację i logikę dla
klas testowych, dlatego nie powinien być
tworzony bezpośrednio.

------------------------------------------------------------
Dlaczego BASE_URL jest static final?

static = jedna wspólna wartość
final = nie można zmienić

------------------------------------------------------------
Dlaczego helper methods są private?

Ponieważ są szczegółem implementacji
i nie powinny być wywoływane z zewnątrz.

------------------------------------------------------------
Dlaczego ApiClient jest public?

Ponieważ korzystają z niego inne klasy
i pakiety w projekcie.
*/

  /*

============================================================
STATIC VS NON-STATIC (NAJWAŻNIEJSZE ZASADY)
============================================================
Najprostszy model:

STATIC    należy do klasy

NON-STATIC    należy do konkretnego obiektu

------------------------------------------------------------
Przykład:

class User {
    static String APP_NAME = "TestApp";
    String name = "John";
}

APP_NAME    istnieje raz dla całej klasy

name    istnieje osobno dla każdego obiektu

============================================================
ZASADA #1
============================================================
STATIC może używać STATIC.

------------------------------------------------------------
class User {
    static String APP_NAME = "TestApp";

    static void print() {
        System.out.println(APP_NAME);
    }
}

To działa.

============================================================
ZASADA #2
============================================================
STATIC nie może bezpośrednio używać NON-STATIC.

------------------------------------------------------------
class User {
    String name = "John";

    static void print() {
        System.out.println(name);
    }
}

BŁĄD KOMPILACJI

Dlaczego?
Bo metoda static nie wie, którego obiektu User ma użyć.

Może istnieć:
new User();
new User();
new User();

Którego name miałaby użyć?

============================================================
ZASADA #3
============================================================
NON-STATIC może używać STATIC.
------------------------------------------------------------
class User {
    static String APP_NAME = "TestApp";
    void print() {
        System.out.println(APP_NAME);
    }
}
To działa.

============================================================
ZASADA #4
============================================================
NON-STATIC może używać NON-STATIC.

------------------------------------------------------------
class User {
    String name = "John";
    void print() {
        System.out.println(name);
    }
}

To działa. Obiekt zna samego siebie.

============================================================
STATIC METHOD -> STATIC METHOD
============================================================
------------------------------------------------------------

static void methodA() {
}

static void methodB() {
    methodA();
}
OK

============================================================
NON-STATIC METHOD -> STATIC METHOD
============================================================
------------------------------------------------------------
static void helper() {
}

void test() {
    helper();
}
OK

============================================================
STATIC METHOD -> NON-STATIC METHOD
============================================================
------------------------------------------------------------

void helper() {
}

static void test() {
    helper();

}
BŁĄD KOMPILACJI

------------------------------------------------------------
Poprawnie:

static void test() {
    User user = new User();
    user.helper();
}
OK

============================================================
STATIC FIELD + NON-STATIC METHOD
============================================================
------------------------------------------------------------

static String APP_NAME = "TestApp";
void print() {
    System.out.println(APP_NAME);

}
OK

============================================================
NON-STATIC FIELD + STATIC METHOD
============================================================
------------------------------------------------------------

String name = "John";

static void print() {
    System.out.println(name);
}

BŁĄD KOMPILACJI

============================================================
PRZEKAZANIE WYNIKU STATIC METHOD
DO NON-STATIC METHOD
============================================================
------------------------------------------------------------

static String getName() {
    return "John";
}

void printName(String name) {
    System.out.println(name);
}

------------------------------------------------------------

User user = new User();
user.printName(getName());

OK
Metoda static może zwrócić wartość,
którą przekazujemy do zwykłej metody.


============================================================
STATIC INNER CLASS
============================================================
------------------------------------------------------------
class User {
    static class Builder {
    }
}
OK

------------------------------------------------------------
Nie potrzebujemy obiektu User,
aby stworzyć Builder.

User.Builder builder =
    new User.Builder();

OK

============================================================
CZY STATIC INNER CLASS MUSI MIEĆ
TYLKO STATIC METODY?
============================================================
NIE.

------------------------------------------------------------
class User {

    static class Builder {

        private String name;

        public Builder name(String name) {

            this.name = name;

            return this;
        }
    }
}
To działa.

------------------------------------------------------------
static class oznacza:
"nie potrzebuję obiektu klasy zewnętrznej"

a NIE:
"wszystko w środku musi być static"


============================================================
TOP LEVEL STATIC CLASS
============================================================

TO NIE DZIAŁA
------------------------------------------------------------

public static class User {
}
BŁĄD KOMPILACJI
------------------------------------------------------------
Top-level class nie może być static.

static można użyć tylko dla:
- pól
- metod
- bloków static
- klas zagnieżdżonych


============================================================
REKRUTACYJNE DEFINICJE
============================================================
Dlaczego metoda static nie może używać
pola non-static?

Ponieważ metoda statyczna należy do klasy,
a pole non-static do konkretnej instancji.

Metoda statyczna nie wie,
z którego obiektu miałaby pobrać wartość.

------------------------------------------------------------
Kiedy używać static?

- utility methods
- helper methods
- factory methods
- stałe
- elementy niezależne od stanu obiektu

------------------------------------------------------------
Kiedy NIE używać static?

Gdy logika zależy od konkretnego obiektu
i jego pól.

------------------------------------------------------------

Najprostsza reguła:

STATIC
    należy do klasy

NON-STATIC
    należy do obiektu
*/


  /*
============================================================
INTERFACES
============================================================

Najprostsza definicja:
Interface definiuje kontrakt.

Mówi:
"Jeżeli chcesz być moim typem,
musisz posiadać te metody."

Nie mówi JAK.
Mówi CO.
------------------------------------------------------------

interface Animal {
    void makeSound();
}

class Dog implements Animal {
    @Override
    public void makeSound() {
        System.out.println("Woof");
    }
}

------------------------------------------------------------
Definicja rekrutacyjna:

Interface definiuje kontrakt, który muszą spełnić klasy implementujące.

============================================================
DLACZEGO INTERFACE UKRYWA IMPLEMENTACJĘ?
============================================================
Przykład:
interface PaymentService {
    void pay();
}

------------------------------------------------------------
class VisaPaymentService
    implements PaymentService

class BlikPaymentService
    implements PaymentService

------------------------------------------------------------
Kod korzystający z interfejsu:

PaymentService service;
service.pay();

------------------------------------------------------------
Kod zna tylko kontrakt.

Nie wie:
- Visa
- Blik
- PayPal
- Mock

To właśnie oznacza
ukrycie implementacji.

============================================================
INTERFACE VS ABSTRACT CLASS
============================================================
ABSTRACT CLASS
IS-A
------------------------------------------------------------
LoginTest IS-A BaseTest

UserPage IS-A BasePage
------------------------------------------------------------
Używamy gdy:
- istnieje wspólny rodzic
- istnieją wspólne pola
- istnieje wspólna logika

============================================================
INTERFACE
============================================================
CAN-DO
------------------------------------------------------------
KafkaPublisher CAN publish
RabbitPublisher CAN publish
MockPublisher CAN publish

------------------------------------------------------------
Używamy gdy:
- definiujemy zachowanie
- klasy mogą być niezwiązane
- chcemy podmieniać implementacje

============================================================
WIELODZIEDZICZENIE
============================================================
To NIE działa:
class Dog extends Animal, Pet
BŁĄD

------------------------------------------------------------
To działa:
class Dog implements Animal, Pet, Trainable

OK
------------------------------------------------------------
Klasa może implementować wiele interfejsów.


============================================================
METODY W INTERFEJSIE
============================================================
Java 7:
tylko abstract methods
------------------------------------------------------------
interface UserService {
    void save();
}

------------------------------------------------------------
Kompilator traktuje to jako:
public abstract void save();

============================================================
DEFAULT METHODS
============================================================
Od Java 8.
------------------------------------------------------------
interface Logger {
    default void log() {
        System.out.println("log");
    }
}

------------------------------------------------------------
Metoda posiada implementację.

------------------------------------------------------------
Powód dodania:
możliwość rozbudowy interfejsów bez psucia istniejącego kodu.

============================================================
STATIC METHODS
============================================================
Od Java 8.
------------------------------------------------------------
interface Utils {
    static String version() {
        return "1.0";
    }
}

------------------------------------------------------------
Wywołanie:
Utils.version();

============================================================
PRIVATE METHODS
============================================================
Od Java 9.

------------------------------------------------------------
interface Logger {
    default void info() {
        format();
    }
    private void format() {
    }
}

------------------------------------------------------------
Służą do współdzielenia logiki pomiędzy metodami default.

============================================================
POLA W INTERFEJSIE
============================================================
------------------------------------------------------------
interface Config {
    String URL = "abc";
}
------------------------------------------------------------
Kompilator zrobi z tego:
public static final String URL = "abc";
automatycznie.

============================================================
PODCHWYTLIWE PYTANIE
============================================================
interface A {
    default void hello() {
        System.out.println("A");
    }
}

interface B {
    default void hello() {
        System.out.println("B");
    }
}

class Test implements A, B {
}

------------------------------------------------------------
BŁĄD KOMPILACJI
------------------------------------------------------------
Dlaczego?
Java nie wie, której implementacji użyć.

============================================================
ROZWIĄZANIE KONFLIKTU DEFAULT
============================================================
class Test implements A, B {
    @Override
    public void hello() {
        A.super.hello();
    }
}

------------------------------------------------------------
lub
B.super.hello();
------------------------------------------------------------
lub własna implementacja.

============================================================
CZY INTERFACE JEST ABSTRACT?
============================================================
Tak.
------------------------------------------------------------
interface User {
}

------------------------------------------------------------
jest traktowane jak:
abstract interface User {
}
------------------------------------------------------------
Słowo abstract jest zbędne.

============================================================
CZY INTERFACE MOŻE BYĆ FINAL?
============================================================
NIE
------------------------------------------------------------
final interface User {
}

------------------------------------------------------------
BŁĄD

-------
Interface ma być implementowany.

final zabrania dziedziczenia
i implementacji.

============================================================
CZY INTERFACE MOŻE BYĆ STATIC?
============================================================
Top-level:
public static interface User {
}

BŁĄD

------------------------------------------------------------
Nested:
class Outer {
    public static interface User {
    }
}
OK

============================================================
REKRUTACYJNA ODPOWIEDŹ
============================================================

Kiedy abstract class?

Gdy istnieje wspólny stan,
wspólna logika i relacja IS-A.

------------------------------------------------------------

Kiedy interface?
Gdy definiujemy kontrakt,
zachowanie oraz możliwość
podmieniania implementacji.

------------------------------------------------------------
Najkrócej:

Abstract class    = czym obiekt jest

Interface    = co obiekt potrafi zrobić
*/
}
