package book;

import java.awt.*;
import java.awt.print.*;

import javax.swing.*;

/**
 * Klasa implementująca uniwersalne okno dialogowe podglądu wydruku.
 */
public class PrintPreviewDialog extends JDialog
{
   private static final int DEFAULT_WIDTH = 300;
   private static final int DEFAULT_HEIGHT = 300;

   private PrintPreviewCanvas canvas;

   /**
    * Tworzy okno dialogowe podglądu wydruku.
    * @param p obiekt typu Printable
    * @param pf format strony 
    * @param pages liczba stron obiektu p
    */
   public PrintPreviewDialog(Printable p, PageFormat pf, int pages)
   {
      Book book = new Book();
      book.append(p, pf, pages);
      layoutUI(book);
   }

   /**
    * Tworzy okno podglądu wydruku.
    * @param b obiekt klasy Book
    */
   public PrintPreviewDialog(Book b)
   {
      layoutUI(b);
   }

   /**
    * Rozmieszcza komponenty okna dialogowego.
    * @param book obiekt klasy Book, którego podgląd będzie prezentowany
    */
   public void layoutUI(Book book)
   {
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

      canvas = new PrintPreviewCanvas(book);
      add(canvas, BorderLayout.CENTER);

      JPanel buttonPanel = new JPanel();

      JButton nextButton = new JButton("Następna");
      buttonPanel.add(nextButton);
      nextButton.addActionListener(event -> canvas.flipPage(1));

      JButton previousButton = new JButton("Poprzednia");
      buttonPanel.add(previousButton);
      previousButton.addActionListener(event -> canvas.flipPage(-1));

      JButton closeButton = new JButton("Zamknij");
      buttonPanel.add(closeButton);
      closeButton.addActionListener(event -> setVisible(false));

      add(buttonPanel, BorderLayout.SOUTH);
   }
}