package progressMonitorInputStream;

import java.io.*;
import java.nio.file.*;
import java.util.*;

import javax.swing.*;

/**
 * Ramka wyposażona w menu umożliwiające załadowanie pliku tekstowego 
 * i obszar tekstowy pokazujący jego zawartość. Obszar tekstowy jest tworzony 
 * i inicjowany po zakończeniu wczytywania pliku, aby można było uniknąć migania.
 */
public class TextFrame extends JFrame
{
   public static final int TEXT_ROWS = 10;
   public static final int TEXT_COLUMNS = 40;

   private JMenuItem openItem;
   private JMenuItem exitItem;
   private JTextArea textArea;
   private JFileChooser chooser;

   public TextFrame()
   {
      textArea = new JTextArea(TEXT_ROWS, TEXT_COLUMNS);
      add(new JScrollPane(textArea));

      chooser = new JFileChooser();
      chooser.setCurrentDirectory(new File("."));

      JMenuBar menuBar = new JMenuBar();
      setJMenuBar(menuBar);
      JMenu fileMenu = new JMenu("Plik");
      menuBar.add(fileMenu);
      openItem = new JMenuItem("Otwórz");
      openItem.addActionListener(event ->
         {
            try
            {
               openFile();
            }
            catch (IOException exception)
            {
               exception.printStackTrace();
            }
         });

      fileMenu.add(openItem);
      exitItem = new JMenuItem("Koniec");
      exitItem.addActionListener(event -> System.exit(0));
      fileMenu.add(exitItem);
      pack();
   }

   /**
    * Umożliwia użytkownikowi wybranie pliku, którego zawartość
    * zostanie pokazana w obszarze tekstowym.
    */
   public void openFile() throws IOException
   {
      int r = chooser.showOpenDialog(this);
      if (r != JFileChooser.APPROVE_OPTION) return;
      final File f = chooser.getSelectedFile();

      // tworzy strumień i sekwencję filtrów odczytu
      
      InputStream fileIn = Files.newInputStream(f.toPath());
      final ProgressMonitorInputStream progressIn = new ProgressMonitorInputStream(
         this, "Wczytywanie pliku " + f.getName(), fileIn);      

      textArea.setText("");

      SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>()
         {
            protected Void doInBackground() throws Exception
            {
               try (Scanner in = new Scanner(progressIn, "UTF-8"))
               {
                  while (in.hasNextLine())
                  {
                     String line = in.nextLine();
                     textArea.append(line);
                     textArea.append("\n");
                  }
               }
               return null;
            }
         };
      worker.execute();
   }
}
