package imageIO;

import java.awt.*;
import javax.swing.*;

/**
 * Ten program pozwala na zapis i odczyt plików obrazów w formatach obsługiwanych przez JDK. 
 * Obsługiwane są także formaty pozwalające na zapis wielu obrazów w jednym pliku.
 * @version 1.04 2016-05-10
 * @author Cay Horstmann
 */
public class ImageIOTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {
            JFrame frame = new ImageIOFrame();
            frame.setTitle("ImageIOTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         });
   }
}