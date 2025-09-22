package com.webapp.utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Java8Utils {

  public static void main(String[] args) {
    list_8_examples();
  }

  public static void list_8_examples() {

    // 1. Create a List
    List<String> names = Arrays.asList("Ram", "Shyam", "Mohan", "Sita", "Geeta");
    System.out.println("Original List: " + names);

    // 2. Iterate over List
    System.out.println("\nIterating over List:");
    names.forEach(System.out::println);

    // 3. Filter List (names starting with 'S')
    List<String> filtered =
        names.stream().filter(name -> name.startsWith("S")).collect(Collectors.toList());
    System.out.println("\nFiltered List (Starts with 'S'): " + filtered);

    // 4. Map List (convert to uppercase)
    List<String> upperNames = names.stream().map(String::toUpperCase).collect(Collectors.toList());
    System.out.println("\nUppercase List: " + upperNames);

    // 5. Sort List
    List<String> sorted = names.stream().sorted().collect(Collectors.toList());
    System.out.println("\nSorted List: " + sorted);

    // 6. Find First Element
    System.out.print("\nFirst Element: ");
    names.stream().findFirst().ifPresent(System.out::println);

    // 7. List of numbers → square each
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
    List<Integer> squares = numbers.stream().map(n -> n * n).collect(Collectors.toList());
    System.out.println("\nSquares of Numbers: " + squares);

    // 8. Filter even numbers
    List<Integer> evens = numbers.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
    System.out.println("\nEven Numbers: " + evens);

    // 9. flatMap Example (flatten list of lists)
    List<List<String>> nestedList =
        Arrays.asList(
            Arrays.asList("Apple", "Banana"),
            Arrays.asList("Orange", "Mango"),
            Arrays.asList("Grapes", "Pineapple"));

    List<String> flatList = nestedList.stream().flatMap(List::stream).collect(Collectors.toList());
    System.out.println("\nFlattened List using flatMap: " + flatList);

    // 10. flatMap with Transformation (split words into characters)
    List<String> words = Arrays.asList("Java", "Stream");

    List<String> characters =
        words.stream()
            .flatMap(word -> word.chars().mapToObj(c -> String.valueOf((char) c)))
            .collect(Collectors.toList());

    System.out.println("\nCharacters from Words using flatMap: " + characters);
  }
}
/*
Original List: [Ram, Shyam, Mohan, Sita, Geeta]

Iterating over List:
Ram
Shyam
Mohan
Sita
Geeta

Filtered List (Starts with 'S'): [Shyam, Sita]
Uppercase List: [RAM, SHYAM, MOHAN, SITA, GEETA]
Sorted List: [Geeta, Mohan, Ram, Shyam, Sita]

First Element: Ram
Squares of Numbers: [1, 4, 9, 16, 25]
Even Numbers: [2, 4]
Flattened List using flatMap: [Apple, Banana, Orange, Mango, Grapes, Pineapple]
Characters from Words using flatMap: [J, a, v, a, S, t, r, e, a, m]

 */
