package longList;

import java.awt.*;
import javax.swing.*;

/**
 * Ten program pokazuje listę, której elementy są określane dynamicznie.
 * @version 1.24 2016-05-10
 * @author Cay Horstmann
 */
public class LongListTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {
            JFrame frame = new LongListFrame();
            frame.setTitle("LongListTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         });
   }
}


