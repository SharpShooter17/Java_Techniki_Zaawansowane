package serialClone;

/**
 * @version 1.21 13 Jul 2016
 * @author Cay Horstmann
 */

import java.io.*;
import java.util.*;
import java.time.*;

public class SerialCloneTest
{  
   public static void main(String[] args) throws CloneNotSupportedException
   {  
      Employee harry = new Employee("Harry Hacker", 35000, 1989, 10, 1);
      // klonuje obiekt harry
      Employee harry2 = (Employee) harry.clone();

      // modyfikuje obiekt harry
      harry.raiseSalary(10);

      // teraz obiekt harry i jego klon różnią się
      System.out.println(harry);
      System.out.println(harry2);
   }
}

/**
 * Klasa, której metoda clone wykorzystuje serializację.
 */
class SerialCloneable implements Cloneable, Serializable
{  
   public Object clone() throws CloneNotSupportedException
   {
      try {
         // zapisuje obiekt w tablicy bajtów
         ByteArrayOutputStream bout = new ByteArrayOutputStream();
         try (ObjectOutputStream out = new ObjectOutputStream(bout))
         {
            out.writeObject(this);
         }

         // wczytuje klon obiektu z tablicy bajtów
         try (InputStream bin = new ByteArrayInputStream(bout.toByteArray()))
         {
            ObjectInputStream in = new ObjectInputStream(bin);
            return in.readObject();
         }
      }
      catch (IOException | ClassNotFoundException e)
      {  
         CloneNotSupportedException e2 = new CloneNotSupportedException();
         e2.initCause(e);
         throw e2;
      }
   }
}

/**
 * Znana już klasa Employee,
 * tym razem jako pochodna klasy SerialCloneable. 
 */
class Employee extends SerialCloneable
{  
   private String name;
   private double salary;
   private LocalDate hireDay;

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
