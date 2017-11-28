package threaded;

import java.io.*;
import java.net.*;
import java.util.*;

/**
 * Program implementujący wielowątkowy serwer nasłuchujący na porcie 8189
 * i wysyłający echo informacji otrzymanej od klientów.
 * @author Cay Horstmann
 * @version 1.22 2016-04-27
 */
public class ThreadedEchoServer
{  
   public static void main(String[] args )
   {  
      try (ServerSocket s = new ServerSocket(8189))
      {  
         int i = 1;

         while (true)
         {  
            Socket incoming = s.accept();
            System.out.println("Spawning " + i);
            Runnable r = new ThreadedEchoHandler(incoming);
            Thread t = new Thread(r);
            t.start();
            i++;
         }
      }
      catch (IOException e)
      {  
         e.printStackTrace();
      }
   }
}

/**
 * Klasa obsługująca komunikację z pojedynczym klientem.
 */
class ThreadedEchoHandler implements Runnable
{ 
   private Socket incoming;

   /**
    * Tworzy obiekt obsługi.
    * @param i gniazdo wejściowe
   */
   public ThreadedEchoHandler(Socket incomingSocket)
   { 
      incoming = incomingSocket; 
   }

   public void run()
   {  
      try (InputStream inStream = incoming.getInputStream();
         OutputStream outStream = incoming.getOutputStream())
      {            
            Scanner in = new Scanner(inStream, "UTF-8");         
            PrintWriter out = new PrintWriter(
               new OutputStreamWriter(outStream, "UTF-8"),
               true /* autoFlush */);
            
            out.println("Witaj! Wpisz BYE, by zakończyć.");
            
            // wyświetla echo
            boolean done = false;
            while (!done && in.hasNextLine())
            {  
               String line = in.nextLine();            
               out.println("Echo: " + line);            
               if (line.trim().equals("BYE"))
                  done = true;
            }
      }
      catch (IOException e)
      {  
         e.printStackTrace();
      }
   }
}
