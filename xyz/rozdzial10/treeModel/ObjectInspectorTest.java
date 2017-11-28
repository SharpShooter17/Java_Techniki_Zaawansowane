package treeModel;

import java.awt.*;
import javax.swing.*;

/**
 * Program demonstrujący stosowanie niestandardowych modeli drzew. Wyświetla pola obiektu.
 * @version 1.05 2016-05-10
 * @author Cay Horstmann
 */
public class ObjectInspectorTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {
            JFrame frame = new ObjectInspectorFrame();
            frame.setTitle("ObjectInspectorTest");               
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         });
   }
}



