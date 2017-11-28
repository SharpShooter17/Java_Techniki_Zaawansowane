package tableRowColumn;

import java.awt.*;
import javax.swing.*;

/**
 * Program demonstrujący jak procować z wierszami i kolumnami w tabeli.
 * @version 1.22 2016-05-10
 * @author Cay Horstmann
 */
public class TableRowColumnTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {
            JFrame frame = new PlanetTableFrame();
            frame.setTitle("TableRowColumnTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         });
   }
}