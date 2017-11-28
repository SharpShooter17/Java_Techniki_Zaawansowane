package progressBar;

import java.awt.*;
import javax.swing.*;

/**
 * Ten program prezentuje zastosowanie paska postępu do monitorowania postępów 
 * w rezliacji wątku.
 * @version 1.05 2012-05-10
 * @author Cay Horstmann
 */
public class ProgressBarTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {
            JFrame frame = new ProgressBarFrame();
            frame.setTitle("ProgressBarTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         });
   }
}
