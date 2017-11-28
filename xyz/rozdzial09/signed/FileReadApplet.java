package signed;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.file.*;
import javax.swing.*;

/**
 * Aplet ten może zostać uruchomiony poza "piaskownicą" (ang. sandbox)
 * i czytać lokalne pliki, jeśli udzielimy mu odpowiedniego pozwolenia.
 * @version 1.13 2016-05-10
 * @author Cay Horstmann
 */
public class FileReadApplet extends JApplet
{
   private JTextField fileNameField;
   private JTextArea fileText;

   public void init()
   {
      EventQueue.invokeLater(() ->
         {
            fileNameField = new JTextField(20);
            JPanel panel = new JPanel();
            panel.add(new JLabel("Nazwa pliku:"));
            panel.add(fileNameField);
            JButton openButton = new JButton("Otwórz");
            panel.add(openButton);
            ActionListener listener = event -> loadFile(fileNameField.getText());
            fileNameField.addActionListener(listener);
            openButton.addActionListener(listener);
            add(panel, "North");
            fileText = new JTextArea();
            add(new JScrollPane(fileText), "Center");
         });
   }

   /**
    * Wczytuje zawartość pliku do obszaru tekstowego.
    * @param filename nazwa pliku
    */
   public void loadFile(String filename)
   {
      fileText.setText("");
      try 
      {
         fileText.append(new String(Files.readAllBytes(Paths.get(filename))));            
      }
      catch (IOException ex)
      {
         fileText.append(ex + "\n");
      }
      catch (SecurityException ex)
      {
         fileText.append("Przykro mi, ale nie mogę tego zrobić.\n");
         fileText.append(ex + "\n");
         ex.printStackTrace();
      }
   }
}
