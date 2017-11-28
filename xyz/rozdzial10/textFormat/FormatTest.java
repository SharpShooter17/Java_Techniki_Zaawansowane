package textFormat;

import java.awt.*;
import javax.swing.*;

/**
 * Program do testowania pól tekstowych z formatowaniem.
 * @version 1.04 2016-05-10
 * @author Cay Horstmann
 */
public class FormatTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {
            JFrame frame = new FormatTestFrame();
            frame.setTitle("FormatTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         });
   }
}

