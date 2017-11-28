package book;

import java.awt.*;
import javax.swing.*;

/**
 * Program demonstrujący drukowanie wielostronicowej książki. Drukuje ona komunikat
 * dobierając czcionkę tak by znaki zajęły całą wysokość strony. Program zawier także ogólne
 * okienko podglądu wydruku.
 * @version 1.13 2016-05-10
 * @author Cay Horstmann
 */
public class BookTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {
            JFrame frame = new BookTestFrame();
            frame.setTitle("BookTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         });
   }
}