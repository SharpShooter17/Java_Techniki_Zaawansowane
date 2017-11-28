package regex;

import java.util.*;
import java.util.regex.*;

/**
 * Program testujący zgodność z wyrażeniem regularnym. Wprowadź wzorzec 
 * i dopasowywany łańcuch.
 * Jeśli wzorzec zawiera grupy, to po dopasowaniu program wyświetli ich granice.
 * @version 1.02 2012-06-02
 * @author Cay Horstmann
 */
public class RegexTest
{
   public static void main(String[] args) throws PatternSyntaxException
   {
      Scanner in = new Scanner(System.in);
      System.out.println("Wpisz wyrażenie regularne: ");
      String patternString = in.nextLine();

      Pattern pattern = Pattern.compile(patternString);
      
      while (true)
      {
         System.out.println("Wpisz łańcuch znaków: ");
         String input = in.nextLine();        
         if (input == null || input.equals("")) return;
         Matcher matcher = pattern.matcher(input);
         if (matcher.matches())
         {
            System.out.println("Dopasowano");
            int g = matcher.groupCount();
            if (g > 0)
            {
               for (int i = 0; i < input.length(); i++)
               {
                  // Wyświetla puste grupy
                  for (int j = 1; j <= g; j++)
                     if (i == matcher.start(j) && i == matcher.end(j)) 
                        System.out.print("()");        
                  // Wyświetla ( dla niepustych grup, które tu się zaczynają
                  for (int j = 1; j <= g; j++)
                     if (i == matcher.start(j) && i != matcher.end(j)) 
                        System.out.print('(');
                  System.out.print(input.charAt(i));
                  // Wyświetla ) dla grup kończących się tutaj
                  for (int j = 1; j <= g; j++)
                     if (i + 1 != matcher.start(j) && i + 1 == matcher.end(j)) 
                        System.out.print(')');
               }
               System.out.println();
            }
         }
         else
            System.out.println("Brak dopasowania");
      }
   }
}


