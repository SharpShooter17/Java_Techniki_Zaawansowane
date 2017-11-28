package tree;

import java.awt.*;
import javax.swing.*;

/**
 * Program wyświetlający proste drzewo.
 * @version 1.03 2016-05-10
 * @author Cay Horstmann
 */
public class SimpleTree
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {
            JFrame frame = new SimpleTreeFrame();
            frame.setTitle("SimpleTree");               
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         });
   }
}
