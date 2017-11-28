import java.util.*;

/**
 * @version 1.02 2007-10-26
 * @author Cay Horstmann
*/
public class Win32RegKeyTest
{  
   public static void main(String[] args)
   {  
      Win32RegKey key = new Win32RegKey(
         Win32RegKey.HKEY_CURRENT_USER, "Software\\JavaSoft\\Java Runtime Environment");

      key.setValue("Domyślny użytkownik", "Henio Hacker");
      key.setValue("Szczęśliwa liczba", new Integer(13));
      key.setValue("Małe liczby pierwsze", new byte[] { 2, 3, 5, 7, 11 });

      Enumeration<String> e = key.names();

      while (e.hasMoreElements())
      {  
         String name = e.nextElement();
         System.out.print(name + "=");

         Object value = key.getValue(name);

         if (value instanceof byte[])
            for (byte b : (byte[]) value) System.out.print((b & 0xFF) + " ");
         else 
            System.out.print(value);

         System.out.println();
      }
   }
}
