package tableCellRender;

import java.awt.*;
import javax.swing.*;

/**
 * Program demonstrujący wyświetlanie i edycję komórek tabel.
 * @version 1.04 2016-05-10
 * @author Cay Horstmann
 */
public class TableCellRenderTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {

            JFrame frame = new TableCellRenderFrame();
            frame.setTitle("TableCellRenderTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         });
   }
}