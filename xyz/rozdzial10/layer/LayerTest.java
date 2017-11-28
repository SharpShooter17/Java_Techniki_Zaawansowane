package layer;

import java.awt.*;
import javax.swing.*;

/**
 * Program pokazujący w jaki sposób ramka może dekorowć komponent Swing.
 * @version 1.01 2016-05-10
 * @author Cay Horstmann
 */
public class LayerTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {
            JFrame frame = new ColorFrame();
            frame.setTitle("LayerTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         });
   }
}