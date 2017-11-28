package imageTransfer;

import java.awt.*;
import javax.swing.*;

/**
 * Program demonstrujący transfer obrazów pomiędzy aplikacją Javy a schowkiem systemowym.
 * @version 1.23 2016-05-10
 * @author Cay Horstmann
 */
public class ImageTransferTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {
            JFrame frame = new ImageTransferFrame();
            frame.setTitle("ImageTransferTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         });
   }
}

