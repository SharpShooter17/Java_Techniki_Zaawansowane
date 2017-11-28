package urlConnection;

import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.*;

/**
 * Program łączący się z lokalizacją określoną przez URL i wyświetlający 
 * dane nagłówka odpowiedzi oraz pierwszych 10 wierszy danych żądanego zasobu.
 *
 * Parametrem programu jest adres URL oraz, opcjonalnie, nazwa i hasło 
 * służące do uwierzytelnienia użytkownika w protokole HTTP.
 * @version 1.11 2007-06-26
 * @author Cay Horstmann
 */
public class URLConnectionTest
{
   public static void main(String[] args)
   {
      try
      {
         String urlName;
         if (args.length > 0) urlName = args[0];
         else urlName = "http://horstmann.com";

         URL url = new URL(urlName);
         URLConnection connection = url.openConnection();

         // wykorzystuje nazwę użytkownika i hasło, jeśli zostały podane w wierszu poleceń

         if (args.length > 2)
         {
            String username = args[1];
            String password = args[2];
            String input = username + ":" + password;
            Base64.Encoder encoder = Base64.getEncoder();
            String encoding = encoder.encodeToString(input.getBytes(StandardCharsets.UTF_8));
            connection.setRequestProperty("Authorization", "Basic " + encoding);
         }

         connection.connect();

         // wyświetla pola nagłówka

         Map<String, List<String>> headers = connection.getHeaderFields();
         for (Map.Entry<String, List<String>> entry : headers.entrySet())
         {
            String key = entry.getKey();
            for (String value : entry.getValue())
               System.out.println(key + ": " + value);
         }

         // wyświetla rezultaty działania metod dostępu do wartości 

         System.out.println("----------");
         System.out.println("getContentType: " + connection.getContentType());
         System.out.println("getContentLength: " + connection.getContentLength());
         System.out.println("getContentEncoding: " + connection.getContentEncoding());
         System.out.println("getDate: " + connection.getDate());
         System.out.println("getExpiration: " + connection.getExpiration());
         System.out.println("getLastModifed: " + connection.getLastModified());
         System.out.println("----------");

         String encoding = connection.getContentEncoding();
         if (encoding == null) encoding = "UTF-8";
         try (Scanner in = new Scanner(connection.getInputStream(), encoding))
         {   
            // wyświetla pierwszych 10 wierszy danych zasobu
   
            for (int n = 1; in.hasNextLine() && n <= 10; n++)
               System.out.println(in.nextLine());
            if (in.hasNextLine()) System.out.println(". . .");
         }
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }   
}
