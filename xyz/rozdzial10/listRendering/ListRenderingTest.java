package listRendering;

import java.awt.*;
import javax.swing.*;

/**
 * Program demonstruje zastosowanie mechanizmów wyświetlania w polu listy.
 * @version 1.25 2016-05-10
 * @author Cay Horstmann
 */
public class ListRenderingTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {
            JFrame frame = new ListRenderingFrame();
            frame.setTitle("ListRenderingTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         });
   }
}