package printService;

import java.io.*;
import java.nio.file.*;
import javax.print.*;

/**
 * Program demonstrujący wykorzystanie usług drukowania. Drukuje obrazek 
 * w formacie GIF za pomocą jednej z usług dostępnych dla tego rodzaju 
 * dokumentu (wybranej przez użytkownika).
 * @version 1.10 2007-08-16
 * @author Cay Horstmann
 */
public class PrintServiceTest
{
   public static void main(String[] args)
   {
      DocFlavor flavor = DocFlavor.URL.GIF;
      PrintService[] services = PrintServiceLookup.lookupPrintServices(flavor, null);
      if (args.length == 0)
      {
         if (services.length == 0) 
            System.out.println("Brak usługi drukowania dla dokumentu typu: " + flavor);
         else
         {
            System.out.println("Proszę wybrać dokument typu " + flavor
                  + "\noraz opcjonalnie numer preferowanej drukarki.");
            for (int i = 0; i < services.length; i++)
               System.out.println((i + 1) + ": " + services[i].getName());
         }
         System.exit(0);
      }
      String fileName = args[0];
      int p = 1;
      if (args.length > 1) p = Integer.parseInt(args[1]);
      if (fileName == null) return;
      try (InputStream in = Files.newInputStream(Paths.get(fileName)))
      {
         Doc doc = new SimpleDoc(in, flavor, null);
         DocPrintJob job = services[p - 1].createPrintJob();
         job.print(doc, null);
      }
      catch (Exception ex)
      {
         ex.printStackTrace();
      }
   }
}