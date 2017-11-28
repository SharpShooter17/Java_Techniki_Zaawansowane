package collecting;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class CollectingResults
{
   public static Stream<String> noVowels() throws IOException
   {
      String contents = new String(Files.readAllBytes(
            Paths.get("../gutenberg/alice30.txt")),
            StandardCharsets.UTF_8);
      List<String> wordList = Arrays.asList(contents.split("\\PL+"));
      Stream<String> words = wordList.stream();
      return words.map(s -> s.replaceAll("[aeiouAEIOU]", ""));
   }

   public static <T> void show(String label, Set<T> set)
   {
      System.out.print(label + ": " + set.getClass().getName());
      System.out.println("["
            + set.stream().limit(10).map(Object::toString)
                  .collect(Collectors.joining(", ")) + "]");
   }

   public static void main(String[] args) throws IOException
   {
      Iterator<Integer> iter = Stream.iterate(0, n -> n + 1).limit(10)
            .iterator();
      while (iter.hasNext())
         System.out.println(iter.next());

      Object[] numbers = Stream.iterate(0, n -> n + 1).limit(10).toArray();
      System.out.println("Tablica obiektów:" + numbers); // To jest tablica typu Object[]

      try
      {
         Integer number = (Integer) numbers[0]; // OK
         System.out.println("liczba: " + number);
         System.out.println("Kolejna instrukcja wyrzuci wyjątek:");
         Integer[] numbers2 = (Integer[]) numbers; // wyrzuca wyjątek
      }
      catch (ClassCastException ex)
      {
         System.out.println(ex);
      }

      Integer[] numbers3 = Stream.iterate(0, n -> n + 1).limit(10)
         .toArray(Integer[]::new);
      System.out.println("Tablica liczb całkowitych: " + numbers3); // To jest tablica typu Integer[]

      Set<String> noVowelSet = noVowels()
            .collect(Collectors.toSet());
      show("noVowelSet", noVowelSet);

      TreeSet<String> noVowelTreeSet = noVowels().collect(
            Collectors.toCollection(TreeSet::new));
      show("noVowelTreeSet", noVowelTreeSet);

      String result = noVowels().limit(10).collect(
            Collectors.joining());
      System.out.println("Konkatenacja: " + result);
      result = noVowels().limit(10)
            .collect(Collectors.joining(", "));
      System.out.println("Konkatenacja z użyciem przecinków: " + result);

      IntSummaryStatistics summary = noVowels().collect(
            Collectors.summarizingInt(String::length));
      double averageWordLength = summary.getAverage();
      double maxWordLength = summary.getMax();
      System.out.println("Średnia długość słów: " + averageWordLength);
      System.out.println("Maksymalna długość słowa: " + maxWordLength);
      System.out.println("Metoda forEach:");
      noVowels().limit(10).forEach(System.out::println);
   }
}
