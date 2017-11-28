package book;

import java.awt.*;
import java.awt.geom.*;
import java.awt.print.*;
import javax.swing.*;

/**
 * Komponent prezentacji podglądu wydruku.
 */
class PrintPreviewCanvas extends JComponent
{
   private Book book;
   private int currentPage;

   /**
    * Tworzy obiekt prezentacji podglądu wydruku.
    * @param b obiekt klasy Book, którego podgląd będzie prezentowany
    */
   public PrintPreviewCanvas(Book b)
   {
      book = b;
      currentPage = 0;
   }

   public void paintComponent(Graphics g)
   {
      Graphics2D g2 = (Graphics2D) g;
      PageFormat pageFormat = book.getPageFormat(currentPage);

      double xoff; // przesunięcie wzdłuż osi x początku strony w oknie
      double yoff; // przesunięcie wzdłuż osi y początku strony w oknie
      double scale; // współczynnik przeskalowania strony w oknie
      double px = pageFormat.getWidth();
      double py = pageFormat.getHeight();
      double sx = getWidth() - 1;
      double sy = getHeight() - 1;
      if (px / py < sx / sy) // wyśrodkowanie w poziomie
      {
         scale = sy / py;
         xoff = 0.5 * (sx - scale * px);
         yoff = 0;
      }
      else
      // wyśrodkowanie w pionie
      {
         scale = sx / px;
         xoff = 0;
         yoff = 0.5 * (sy - scale * py);
      }
      g2.translate((float) xoff, (float) yoff);
      g2.scale((float) scale, (float) scale);

      // rysuje obraz strony (ignorując marginesy)
      Rectangle2D page = new Rectangle2D.Double(0, 0, px, py);
      g2.setPaint(Color.white);
      g2.fill(page);
      g2.setPaint(Color.black);
      g2.draw(page);

      Printable printable = book.getPrintable(currentPage);
      try
      {
         printable.print(g2, pageFormat, currentPage);
      }
      catch (PrinterException e)
      {
         g2.draw(new Line2D.Double(0, 0, px, py));
         g2.draw(new Line2D.Double(px, 0, 0, py));
      }
   }

   /**
    * Przesuwa się o zadaną liczbę stron.
    * @param liczba stron. Wartość ujemna oznacza kierunek wstecz.
    */
   public void flipPage(int by)
   {
      int newPage = currentPage + by;
      if (0 <= newPage && newPage < book.getNumberOfPages())
      {
         currentPage = newPage;
         repaint();
      }
   }
}
