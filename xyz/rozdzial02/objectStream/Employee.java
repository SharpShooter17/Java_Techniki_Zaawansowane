package objectStream;

import java.io.*;
import java.time.*;

public class Employee implements Serializable
{
   private String name;
   private double salary;
   private LocalDate hireDay;

   public Employee() {}

   public Employee(String n, double s, int year, int month, int day)
   {  
      name = n;
      salary = s;
      hireDay = LocalDate.of(year, month, day);
   }

   public String getName()
   {
      return name;
   }

   public double getSalary()
   {
      return salary;
   }

   public LocalDate getHireDay()
   {
      return hireDay;
   }

   /**
    * Metoda zwiększająca wynagrodzenie tego pracownika.
    * @byPercent procentowa wartość wzrostu wynagrodzenia
    */
   public void raiseSalary(double byPercent)
   {  
      double raise = salary * byPercent / 100;
      salary += raise;
   }

   public String toString()
   {  
      return getClass().getName()
         + "[name=" + name
         + ",salary=" + salary
         + ",hireDay=" + hireDay
         + "]";
   }
}
