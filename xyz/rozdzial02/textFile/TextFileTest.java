package textFile;

import java.io.*;
import java.time.*;
import java.util.*;

/**
 * @version 1.14 2016-07-11
 * @author Cay Horstmann
 */
public class TextFileTest
{
   public static void main(String[] args) throws IOException
   {
      Employee[] staff = new Employee[3];

      staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
      staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
      staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

      // zapisuje wszystkie rekordy pracowników w pliku employee.dat
      try (PrintWriter out = new PrintWriter("employee.dat", "UTF-8"))
      {         
         writeData(staff, out);
      }
      
      // wczytuje wszystkie rekordy do nowej tablicy
      try (Scanner in = new Scanner(
            new FileInputStream("employee.dat"), "UTF-8"))
      {
         Employee[] newStaff = readData(in);

         // wyświetla wszystkie wczytane rekordy
         for (Employee e : newStaff)
            System.out.println(e);
      }
   }

   /**
    * Zapisuje dane wszystkich obiektów klasy Employee umieszczonych 
    * w tablicy do obiektu klasy PrintWriter
    * @param employees tablica obiektów klasy Employee
    * @param out obiekt klasy PrintWriter
    */
   private static void writeData(Employee[] employees, PrintWriter out) throws IOException
   {
      // zapisuje liczbę obiektów
      out.println(employees.length);

      for (Employee e : employees)
         writeEmployee(out, e);
   }

   /**
    * Wczytuje tablicę obiektów klasy Employee
    * @param in obiekt klasy Scanner
    * @return tablica obiektów klasy Employee
    */
   private static Employee[] readData(Scanner in)
   {
      // pobiera rozmiar tablicy
      int n = in.nextInt();
      in.nextLine(); // pobiera znak nowego wiersza

      Employee[] employees = new Employee[n];
      for (int i = 0; i < n; i++)
      {
         employees[i] = readEmployee(in);
      }
      return employees;
   }
   
   /**
    * Zapisuje dane obiektu klasy Employee do obiektu klasy PrintWriter
    * @param out obiekt klasy PrintWriter
    */
   public static void writeEmployee(PrintWriter out, Employee e)
   {
      out.println(e.getName() + "|" + e.getSalary() + "|" + e.getHireDay());
   }

   /**
    * Wczytuje dane obiektu klasy Employee 
    * @param in obiekt klasy Scanner
    */
   public static Employee readEmployee(Scanner in)
   {
      String line = in.nextLine();
      String[] tokens = line.split("\\|");
      String name = tokens[0];
      double salary = Double.parseDouble(tokens[1]);
      LocalDate hireDate = LocalDate.parse(tokens[2]);
      int year = hireDate.getYear();
      int month = hireDate.getMonthValue();
      int day = hireDate.getDayOfMonth();
      return new Employee(name, salary, year, month, day);
   }   
}
