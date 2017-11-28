package localdates;  

import java.time.*;
import java.time.temporal.*;

public class LocalDates
{
   public static void main(String[] args)
   {
      LocalDate today = LocalDate.now(); // dzisiejsza data
      System.out.println("today: " + today);

      LocalDate alonzosBirthday = LocalDate.of(1903, 6, 14);
      alonzosBirthday = LocalDate.of(1903, Month.JUNE, 14);
      // użycie typu wyliczeniowego Month 
      System.out.println("Urodziny Alonza Churcha: " + alonzosBirthday);

      LocalDate programmersDay = LocalDate.of(2018, 1, 1).plusDays(255);
      // 13 września, lecz w roku przestępnym będzie to 12 września
      System.out.println("Dzień Programisty: " + programmersDay);

      LocalDate independenceDay = LocalDate.of(2018, Month.JULY, 4);
      LocalDate christmas = LocalDate.of(2018, Month.DECEMBER, 25);

      System.out.println("Pozostało do Świąt Bożego Narodzenia: " 
                          + independenceDay.until(christmas));
      System.out.println("Pozostało dni do Świąt Bożego Narodzenia: "
            + independenceDay.until(christmas, ChronoUnit.DAYS));

      System.out.println(LocalDate.of(2016, 1, 31).plusMonths(1));
      System.out.println(LocalDate.of(2016, 3, 31).minusMonths(1));

      DayOfWeek startOfLastMillennium = LocalDate.of(1900, 1, 1).getDayOfWeek();
      System.out.println("Początek poprzedniego wieku: " + startOfLastMillennium);
      System.out.println(startOfLastMillennium.getValue());
      System.out.println(DayOfWeek.SATURDAY.plus(3));
   }
}
