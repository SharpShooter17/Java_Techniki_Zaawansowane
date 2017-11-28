package internalFrame;

import java.awt.*;
import java.beans.*;

import javax.swing.*;

/**
 * Ta ramka zawiera panele edytorów prezentujące dokumenty HTML.
 */
public class DesktopFrame extends JFrame
{
   private static final int DEFAULT_WIDTH = 600;
   private static final int DEFAULT_HEIGHT = 400;
   private static final String[] planets = { "Merkury", "Wenus", "Ziemia", 
      "Mars", "Jowisz", "Saturn", "Uran", "Neptun", "Pluton", };

   private JDesktopPane desktop;
   private int nextFrameX;
   private int nextFrameY;
   private int frameDistance;
   private int counter;

   public DesktopFrame()
   {
      setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

      desktop = new JDesktopPane();
      add(desktop, BorderLayout.CENTER);

      // tworzy menu

      JMenuBar menuBar = new JMenuBar();
      setJMenuBar(menuBar);
      JMenu fileMenu = new JMenu("Plik");
      menuBar.add(fileMenu);
      JMenuItem openItem = new JMenuItem("Nowy");
      openItem.addActionListener(event ->
         {
            createInternalFrame(new JLabel(
                  new ImageIcon(getClass().getResource(planets[counter] + ".gif"))),
                  planets[counter]);
            counter = (counter + 1) % planets.length;
         });
      fileMenu.add(openItem);
      JMenuItem exitItem = new JMenuItem("Koniec");
      exitItem.addActionListener(event -> System.exit(0));
      fileMenu.add(exitItem);
      JMenu windowMenu = new JMenu("Okno");
      menuBar.add(windowMenu);
      JMenuItem nextItem = new JMenuItem("Następne");
      nextItem.addActionListener(event -> selectNextWindow());
      windowMenu.add(nextItem);
      JMenuItem cascadeItem = new JMenuItem("Kaskadowo");
      cascadeItem.addActionListener(event -> cascadeWindows());
      windowMenu.add(cascadeItem);
      JMenuItem tileItem = new JMenuItem("Sąsiadująco");
      tileItem.addActionListener(event -> tileWindows());
      windowMenu.add(tileItem);
      final JCheckBoxMenuItem dragOutlineItem = new JCheckBoxMenuItem("Przeciągnij zarys");
      dragOutlineItem.addActionListener(event ->
         desktop.setDragMode(dragOutlineItem.isSelected() ? JDesktopPane.OUTLINE_DRAG_MODE
            : JDesktopPane.LIVE_DRAG_MODE));         
      windowMenu.add(dragOutlineItem);
   }

   /**
    * Tworzy wewnętrzną ramkę pulpitu.
    * @param c komponent wewnątrz ramki wewnętrznej
    * @param t tytuł ramki wewnętrznej.
    */
   public void createInternalFrame(Component c, String t)
   {
      final JInternalFrame iframe = new JInternalFrame(t, true, // zmiana rozmiarów
            true,  // możliwość zamknięcia
            true,  // maksymalizacja
            true); // zwinięcie do ikony

      iframe.add(c, BorderLayout.CENTER);
      desktop.add(iframe);

      iframe.setFrameIcon(new ImageIcon(getClass().getResource("document.gif")));

      // dodaje obiekt nasłuchujący, aby potwierdzić zamknięcie ramki
      iframe.addVetoableChangeListener(event ->
         {
            String name = event.getPropertyName();
            Object value = event.getNewValue();

            // sprawdza tylko próby zamknięcia ramki
            if (name.equals("closed") && value.equals(true))
            {
               // prosi użytkownika o potwierdzenie zamknięcia 
               int result = JOptionPane.showInternalConfirmDialog(iframe, "Zamknąć?",
                     "Wybierz opcję", JOptionPane.YES_NO_OPTION);

               // jeśli użytkownik się nie zgodzi, to zgłasza weto
               if (result != JOptionPane.YES_OPTION) throw new PropertyVetoException(
                     "Zamknięcie anulowane przez użytkownika", event);
            }
         });

      // ustala pozycję ramki
      int width = desktop.getWidth() / 2;
      int height = desktop.getHeight() / 2;
      iframe.reshape(nextFrameX, nextFrameY, width, height);

      iframe.show();

      // wybór ramki — może zostać zawetowany
      try
      {
         iframe.setSelected(true);
      }
      catch (PropertyVetoException ex)
      {
      }

      frameDistance = iframe.getHeight() - iframe.getContentPane().getHeight();

      // oblicza odległość pomiędzy ramkami

      nextFrameX += frameDistance;
      nextFrameY += frameDistance;
      if (nextFrameX + width > desktop.getWidth()) nextFrameX = 0;
      if (nextFrameY + height > desktop.getHeight()) nextFrameY = 0;
   }

   /**
    * Rozmieszcza kaskadowo te ramki pulpitu, które nie są zwinięte do ikony.
    */
   public void cascadeWindows()
   {
      int x = 0;
      int y = 0;
      int width = desktop.getWidth() / 2;
      int height = desktop.getHeight() / 2;

      for (JInternalFrame frame : desktop.getAllFrames())
      {
         if (!frame.isIcon())
         {
            try
            {
               // próbuje przeprowadzić ramki do stanu pośredniego, co może zostać zawetowane
               frame.setMaximum(false);
               frame.reshape(x, y, width, height);
                  
               x += frameDistance;
               y += frameDistance;
               // zawija wokół brzegu pulpitu
               if (x + width > desktop.getWidth()) x = 0;
               if (y + height > desktop.getHeight()) y = 0;
            }
            catch (PropertyVetoException ex)
            {
            }
         }
      }
   }

   /**
    * Rozmieszcza sąsiadująco te ramki pulpitu, które nie są zwinięte do ikony.
    */
   public void tileWindows()
   {
      // zlicza ramki, które nie są zwinięte do ikony
      int frameCount = 0;
      for (JInternalFrame frame : desktop.getAllFrames())
         if (!frame.isIcon()) frameCount++;
      if (frameCount == 0) return;

      int rows = (int) Math.sqrt(frameCount);
      int cols = frameCount / rows;
      int extra = frameCount % rows;
      // liczba kolumn z dodatkowym wierszem

      int width = desktop.getWidth() / cols;
      int height = desktop.getHeight() / rows;
      int r = 0;
      int c = 0;
      for (JInternalFrame frame : desktop.getAllFrames())
      {
         if (!frame.isIcon())
         {
            try
            {
               frame.setMaximum(false);
               frame.reshape(c * width, r * height, width, height);
               r++;
               if (r == rows)
               {
                  r = 0;
                  c++;
                  if (c == cols - extra)
                  {
                     // dodatkowy wiersz
                     rows++;
                     height = desktop.getHeight() / rows;
                  }
               }
            }
            catch (PropertyVetoException ex)
            {
            }
         }
      }
   }

   /**
    * Wyświetla następną niezminimalizowaną ramkę ponad pozostałymi.
    */
   public void selectNextWindow()
   {
      JInternalFrame[] frames = desktop.getAllFrames();
      for (int i = 0; i < frames.length; i++)
      {
         if (frames[i].isSelected())
         {
            // znajduje ramkę, która nie jest zwinięta do ikony i może zostać wybrana
            int next = (i + 1) % frames.length;
            while (next != i)
            {
               if (!frames[next].isIcon())
               {
                  try
                  {
                     // pozostałe ramki są zwinięte do ikon lub zgłosiły weto do wyboru
                     frames[next].setSelected(true);
                     frames[next].toFront();
                     frames[i].toBack();
                     return;
                  }
                  catch (PropertyVetoException ex)
                  {
                  }
               }
               next = (next + 1) % frames.length;
            }
         }
      }
   }
}
