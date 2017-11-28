/**
 * @version 1.10 1999-11-13
 * @author Cay Horstmann
 */

public class EmployeeTest
{
   public static void main(String[] args)
   {
      Employee[] staff = new Employee[3];

      staff[0] = new Employee("Henio Hacker", 35000);
      staff[1] = new Employee("Karol Cracker", 75000);
      staff[2] = new Employee("Tomek Tester", 38000);

      for (Employee e : staff)
         e.raiseSalary(5);
      for (Employee e : staff)
         e.print();
   }
}
