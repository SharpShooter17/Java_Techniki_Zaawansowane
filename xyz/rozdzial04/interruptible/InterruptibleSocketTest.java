package interruptible;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.net.*;
import java.io.*;
import java.nio.channels.*;
import javax.swing.*;

/**
 * Program prezentujący sposób przerywania działania kanału gniazda.
 * @author Cay Horstmann
 * @version 1.04 2016-04-27
 */
public class InterruptibleSocketTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {
            JFrame frame = new InterruptibleSocketFrame();
            frame.setTitle("InterruptibleSocketTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         });
   }
}

class InterruptibleSocketFrame extends JFrame
{
   private Scanner in;
   private JButton interruptibleButton;
   private JButton blockingButton;
   private JButton cancelButton;
   private JTextArea messages;
   private TestServer server;
   private Thread connectThread;

   public InterruptibleSocketFrame()
   {
      JPanel northPanel = new JPanel();
      add(northPanel, BorderLayout.NORTH);

      final int TEXT_ROWS = 20;
      final int TEXT_COLUMNS = 60;
      messages = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
      add(new JScrollPane(messages));

      interruptibleButton = new JButton("Przerywalna");
      blockingButton = new JButton("Blokująca");

      northPanel.add(interruptibleButton);
      northPanel.add(blockingButton);

      interruptibleButton.addActionListener(event ->
         {
            interruptibleButton.setEnabled(false);
            blockingButton.setEnabled(false);
            cancelButton.setEnabled(true);
            connectThread = new Thread(() ->
               {
                  try
                  {
                     connectInterruptibly();
                  }
                  catch (IOException e)
                  {
                     messages.append("\nInterruptibleSocketTest.connectInterruptibly: " + e);
                  }
               });
            connectThread.start();
         });

      blockingButton.addActionListener(event ->
         {
            interruptibleButton.setEnabled(false);
            blockingButton.setEnabled(false);
            cancelButton.setEnabled(true);
            connectThread = new Thread(() ->
               {
                  try
                  {
                     connectBlocking();
                  }
                  catch (IOException e)
                  {
                     messages.append("\nInterruptibleSocketTest.connectBlocking: " + e);
                  }
               });
            connectThread.start();
         });

      cancelButton = new JButton("Anuluj");
      cancelButton.setEnabled(false);
      northPanel.add(cancelButton);
      cancelButton.addActionListener(event -> 
         {
            connectThread.interrupt();
            cancelButton.setEnabled(false);
         });
      server = new TestServer();
      new Thread(server).start();
      pack();
   }

   /**
    * Lączy się z serwerem testowym, używając przerywalnych operacji wejścia-wyjścia.
    */
   public void connectInterruptibly() throws IOException
   {
      messages.append("Interruptible:\n");
      try (SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8189)))
      {
         in = new Scanner(channel, "UTF-8");
         while (!Thread.currentThread().isInterrupted())
         {
            messages.append("Wczytywanie ");
            if (in.hasNextLine())
            {
               String line = in.nextLine();
               messages.append(line);
               messages.append("\n");
            }
         }
      }
      finally
      {
         EventQueue.invokeLater(() ->
            {
               messages.append("Kanał zamknięty\n");
               interruptibleButton.setEnabled(true);
               blockingButton.setEnabled(true);
            });
      }
   }

   /**
    * Łączy się z serwerem, używając blokujących operacji wejścia-wyjścia.
    */
   public void connectBlocking() throws IOException
   {
      messages.append("Z blokowaniem:\n");
      try (Socket sock = new Socket("localhost", 8189))
      {
         in = new Scanner(sock.getInputStream(), "UTF-8");
         while (!Thread.currentThread().isInterrupted())
         {
            messages.append("Wczytywanie ");
            if (in.hasNextLine())
            {
               String line = in.nextLine();
               messages.append(line);
               messages.append("\n");
            }
         }
      }
      finally
      {
         EventQueue.invokeLater(() ->
            {
               messages.append("Gniazdo zamknięte\n");
               interruptibleButton.setEnabled(true);
               blockingButton.setEnabled(true);
            });      
      }
   }

   /**
    * Serwer wielowątkowy nasłuchujący na porcie 8189 i wysyłający klientom 
    * wartości liczbowe. Po 10 liczbach symuluje "zawieszenie" działania.
    */
   class TestServer implements Runnable
   {
      public void run()
      {
         try (ServerSocket s = new ServerSocket(8189))
         {
            while (true)
            {
               Socket incoming = s.accept();
               Runnable r = new TestServerHandler(incoming);
               Thread t = new Thread(r);
               t.start();
            }
         }
         catch (IOException e)
         {
            messages.append("\nTestServer.run: " + e);
         }
      }
   }

   /**
    * Klasa obsługująca połączenie z pojedynczym klientem.
    */
   class TestServerHandler implements Runnable
   {
      private Socket incoming;
      private int counter;

      /**
       * Tworzy obiekt obsługi.
       * @param i gniazdko połączenia
       */
      public TestServerHandler(Socket i)
      {
         incoming = i;
      }

      public void run()
      {
         try 
         {
            try
            {
               OutputStream outStream = incoming.getOutputStream();
               PrintWriter out = new PrintWriter(
                  new OutputStreamWriter(outStream, "UTF-8"),
                  true /* autoFlush */);
               while (counter < 100)
               {
                  counter++;
                  if (counter <= 10) out.println(counter);
                  Thread.sleep(100);
               }
            }
            finally
            {
               incoming.close();
               messages.append("Zamykanie serwera\n");
            }
         }
         catch (Exception e)
         {
            messages.append("\nTestServerHandler.run: " + e);
         }
      }
   }
}
