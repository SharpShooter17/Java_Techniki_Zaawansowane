package renderQuality;

import java.awt.*;
import javax.swing.*;

/**
 * Program demonstrujący efekty różnych wskazówek operacji graficznych.
 * @version 1.11 2016-05-10
 * @author Cay Horstmann
 */
public class RenderQualityTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {
            JFrame frame = new RenderQualityTestFrame();
            frame.setTitle("RenderQualityTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         });
   }
}