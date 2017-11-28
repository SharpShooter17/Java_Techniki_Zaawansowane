package desktopApp;

import java.awt.*;
import javax.swing.*;

/**
 * Program demonstrujący API aplikacji na komputery biurkowe.
 * @version 1.01 2016-05-10
 * @author Cay Horstmann
 */
public class DesktopAppTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {
            JFrame frame = new DesktopAppFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         });
   }
}