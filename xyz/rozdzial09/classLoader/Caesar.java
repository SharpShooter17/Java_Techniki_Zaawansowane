package classLoader;

import java.io.*;

/**
 * Szyfruje plik za pomocą szyfru Cezara.
 * @version 1.01 2012-06-10
 * @author Cay Horstmann
 */
public class Caesar
{
   public static void main(String[] args) throws Exception
   {
      if (args.length != 3)
      {
         System.out.println("Sposób użycia: java classLoader.Caesar wejście wyjście klucz");
         return;
      }

      try(FileInputStream in = new FileInputStream(args[0]);
         FileOutputStream out = new FileOutputStream(args[1]))
      {
         int key = Integer.parseInt(args[2]);
         int ch;
         while ((ch = in.read()) != -1)
         {
            byte c = (byte) (ch + key);
            out.write(c);
         }
      }
   }
}
