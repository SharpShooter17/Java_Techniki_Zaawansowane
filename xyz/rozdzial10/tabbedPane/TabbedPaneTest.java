package tabbedPane;

import java.awt.*;
import javax.swing.*;

/**
 * Ten program demonstruje stosowanie paneli z kartami.
 * @version 1.04 2016-05-10
 * @author Cay Horstmann
 */
public class TabbedPaneTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {
            JFrame frame = new TabbedPaneFrame();
            frame.setTitle("TabbedPaneTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         });
   }
}

