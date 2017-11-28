package socket;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Program łączący się z zegarem atomowym w Boulder, w stanie Colorado, 
 * i wyświetlający uzyskany czas.
 * 
 * @version 1.21 2016-04-27
 * @author Cay Horstmann
 */
public class SocketTest
{
   public static void main(String[] args) throws IOException
   {
      try (Socket s = new Socket("time-a.nist.gov", 13);
         Scanner in = new Scanner(s.getInputStream(), "UTF-8"))
      {
         while (in.hasNextLine())
         {
            String line = in.nextLine();
            System.out.println(line);
         }
      }
   }
}
