package tableSelection;

/**
 * @version 1.04 2016-05-10
 * @author Cay Horstmann
 */

import java.awt.*;
import javax.swing.*;

/**
 * Program demonstrujący zaznaczanie, dodawanie i usuwanie wierszy oraz kolumn.
 */
public class TableSelectionTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {
            JFrame frame = new TableSelectionFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
      });
   }
}