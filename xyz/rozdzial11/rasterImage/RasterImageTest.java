package rasterImage;

import java.awt.*;
import javax.swing.*;

/**
 * Program demonstrujący proces tworzenia obrazu z pojedynczych pikseli.
 * @version 1.14 2016-05-10
 * @author Cay Horstmann
 */
public class RasterImageTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {
            JFrame frame = new RasterImageFrame();
            frame.setTitle("RasterImageTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         });
   }
}