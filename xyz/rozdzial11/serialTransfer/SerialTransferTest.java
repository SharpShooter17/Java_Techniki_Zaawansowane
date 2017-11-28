package serialTransfer;

import java.awt.*;
import javax.swing.*;

/**
 * Program demonstrujący transfer serializowanych obiektów pomiędzy maszynami wirtualnymi.
 * @version 1.03 2016-05-10
 * @author Cay Horstmann
 */
public class SerialTransferTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {
            JFrame frame = new SerialTransferFrame();
            frame.setTitle("SerialTransferTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         });
   }
}