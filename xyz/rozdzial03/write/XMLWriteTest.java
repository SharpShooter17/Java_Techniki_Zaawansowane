package write;

import java.awt.*;
import javax.swing.*;

/**
 * Program demonstrujący sposób zapisu dokumentu XML. Zapisuje grafikę w formacie SVG.
 * Zapisuje grafikę w pliku formatu SVG
 * @version 1.12 2016-04-27
 * @author Cay Horstmann
 */
public class XMLWriteTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {
            JFrame frame = new XMLWriteFrame();
            frame.setTitle("XMLWriteTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         });
   }
}