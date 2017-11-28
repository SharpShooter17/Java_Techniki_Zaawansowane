package jaas;

import java.awt.*;
import javax.swing.*;

/**
 * Program uwierzytelnia użytkownika za pomocą własnego modułu logowania,
 * a następnie wykonuje SysPropAction, korzystając z pozwoleń użytkownika.
 * @version 1.02 2016-05-10
 * @author Cay Horstmann
 */
public class JAASTest
{
   public static void main(final String[] args)
   {
      System.setSecurityManager(new SecurityManager());
      EventQueue.invokeLater(() ->
         {
            JFrame frame = new JAASFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setTitle("JAASTest");
            frame.setVisible(true);
         });
   }
}
